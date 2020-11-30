package ru.alexsumin.healthtracker.core.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.alexsumin.healthtracker.core.domain.entity.User;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer()
			.withUsername("duke")
			.withPassword("password")
			.withDatabaseName("test");


	@Autowired
	private AccountRepository repository;

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

		repository.save(user);
	}

}
