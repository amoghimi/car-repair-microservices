package com.carrepair.userservice.service;

import com.carrepair.userservice.dto.UserRegistrationRequest;
import com.carrepair.userservice.model.UserEntity;
import com.carrepair.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(UserRegistrationRequest request) {
        UserEntity entity = new UserEntity();
        entity.setUsername(request.username());
        entity.setEmail(request.email());
        entity.setExternalAuthId("keycloak-sync-pending");
        userRepository.save(entity);
        return "User " + request.username() + " registered locally. Sync with Keycloak Admin API in production.";
    }

    public String loginUrl() {
        return "http://localhost:8180/realms/car-repair/protocol/openid-connect/auth";
    }
}
