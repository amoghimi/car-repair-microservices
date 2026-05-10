package com.carrepair.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "eureka.client.enabled=false",
        "spring.cloud.discovery.enabled=false",
        "spring.cloud.gateway.discovery.locator.enabled=false",
        "spring.cloud.config.enabled=false",
        "spring.main.web-application-type=reactive"
})
class GatewaySecurityIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void publicLoginEndpointShouldNotReturnUnauthorized() {
        webTestClient.get()
                .uri("/users/login")
                .exchange()
                .expectStatus()
                .value(status -> org.assertj.core.api.Assertions.assertThat(status).isNotEqualTo(401));
    }

    @Test
    void secureEndpointShouldRequireJwt() {
        webTestClient.get()
                .uri("/parts/1")
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }
}
