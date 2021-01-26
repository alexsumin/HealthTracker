package ru.alexsumin.healthtracker.core.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.alexsumin.healthtracker.core.domain.entity.Measurement;
import ru.alexsumin.healthtracker.core.domain.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MeasurementRepositoryTest {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer()
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    void contextLoads() {
        container.start();
        User user = new User();
        user.setId(1L);

        userRepository.save(user);


        Measurement measurement1 = Measurement.builder()
                .user(user)
                .data(new BigDecimal("15"))
                .creationDate(LocalDateTime.now().minusDays(5L))
                .build();

        Measurement measurement2 = Measurement.builder()
                .user(user)
                .data(new BigDecimal("20"))
                .creationDate(LocalDateTime.now().minusDays(3L))
                .build();

        Measurement measurement3 = Measurement.builder()
                .user(user)
                .data(new BigDecimal("25"))
                .creationDate(LocalDateTime.now())
                .build();

        measurementRepository.save(measurement1);
        measurementRepository.save(measurement2);
        measurementRepository.save(measurement3);


        Measurement fromDb = measurementRepository.findFirstByUser_IdOrderByCreationDateDesc(1L);
        Assertions.assertEquals(measurement3.getData(), fromDb.getData());
    }
}
