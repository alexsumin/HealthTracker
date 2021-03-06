package ru.alexsumin.healthtracker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsumin.healthtracker.core.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
