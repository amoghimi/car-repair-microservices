package com.carrepair.userservice.integration;

import com.carrepair.userservice.dto.UserRegistrationRequest;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KeycloakUserProvisioningClient {

    private final Keycloak keycloak;
    private final String targetRealm;

    public KeycloakUserProvisioningClient(Keycloak keycloak, @Value("${keycloak.target-realm}") String targetRealm) {
        this.keycloak = keycloak;
        this.targetRealm = targetRealm;
    }

    public String createUser(UserRegistrationRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setEnabled(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.password());
        credential.setTemporary(false);
        user.setCredentials(List.of(credential));

        Response response = keycloak.realm(targetRealm).users().create(user);
        try {
            if (response.getStatus() >= 200 && response.getStatus() < 300) {
                String location = response.getHeaderString("Location");
                return location == null ? "created" : location.substring(location.lastIndexOf('/') + 1);
            }
            throw new IllegalStateException("Keycloak user creation failed with status " + response.getStatus());
        } finally {
            response.close();
        }
    }
}
