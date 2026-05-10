package com.carrepair.userservice.service;

import com.carrepair.userservice.dto.UserRegistrationRequest;
import com.carrepair.userservice.integration.KeycloakUserProvisioningClient;
import com.carrepair.userservice.model.UserEntity;
import com.carrepair.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private KeycloakUserProvisioningClient keycloakClient;

    @InjectMocks
    private UserService userService;

    @Test
    void registerShouldProvisionInKeycloakAndPersistUser() {
        UserRegistrationRequest request = new UserRegistrationRequest("max", "max@example.com", "secret");
        when(keycloakClient.createUser(request)).thenReturn("kc-123");

        String result = userService.register(request);

        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(captor.capture());
        UserEntity persisted = captor.getValue();

        assertThat(persisted.getUsername()).isEqualTo("max");
        assertThat(persisted.getEmail()).isEqualTo("max@example.com");
        assertThat(persisted.getExternalAuthId()).isEqualTo("kc-123");
        assertThat(result).contains("max").contains("kc-123");
    }

    @Test
    void loginUrlShouldPointToRealmAuthEndpoint() {
        assertThat(userService.loginUrl())
                .isEqualTo("http://localhost:8180/realms/car-repair/protocol/openid-connect/auth");
    }
}
