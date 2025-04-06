package de.rjst.cs;

import io.github.microcks.testcontainers.MicrocksContainer;
import io.github.microcks.testcontainers.MicrocksContainersEnsemble;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    private static final Network network = Network.newNetwork();

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
    }

    @Bean
    public MicrocksContainersEnsemble microcksEnsemble() {
        final var nativeImage = DockerImageName.parse("quay.io/microcks/microcks-uber:1.10.0-native")
                .asCompatibleSubstituteFor("quay.io/microcks/microcks-uber:1.10.0");
        final var ensemble = new MicrocksContainersEnsemble(network, nativeImage)
                .withMainArtifacts("customer-service-openapi.yaml");
        final var microcksContainer = ensemble.getMicrocksContainer();
        microcksContainer.setPortBindings(List.of("8585:8080"));
        return ensemble;
    }

    @Bean
    public DynamicPropertyRegistrar dynamicPropertyRegistrar(final MicrocksContainersEnsemble microcksEnsemble) {
        final var microcksContainer = microcksEnsemble.getMicrocksContainer();
        final var customerService = microcksContainer.getRestMockEndpoint("Customer+Service+API", "v1");
        return (registry) -> {
            registry.add("spring.cloud.openfeign.client.config.customer.url", () -> customerService);
        };

    }

}
