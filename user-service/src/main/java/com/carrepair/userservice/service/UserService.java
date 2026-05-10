package com.carrepair.userservice.service;

import com.carrepair.userservice.dto.UserRegistrationRequest;
import com.carrepair.userservice.integration.KeycloakUserProvisioningClient;
import com.carrepair.userservice.model.UserEntity;
import com.carrepair.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final KeycloakUserProvisioningClient keycloakClient;

    public UserService(UserRepository userRepository, KeycloakUserProvisioningClient keycloakClient) {
        this.userRepository = userRepository;
        this.keycloakClient = keycloakClient;
    }

    public String register(UserRegistrationRequest request) {
        String externalId = keycloakClient.createUser(request);
        UserEntity entity = new UserEntity();
        entity.setUsername(request.username());
        entity.setEmail(request.email());
        entity.setExternalAuthId(externalId);
        userRepository.save(entity);
        return "User " + request.username() + " registered and synced with Keycloak id=" + externalId;
    }

    public String loginUrl() {
        return "http://localhost:8180/realms/car-repair/protocol/openid-connect/auth";
    }
}
