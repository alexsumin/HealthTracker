package ru.alexsumin.healthtracker.core.service;

import ru.alexsumin.healthtracker.core.domain.entity.User;

public interface UserService {
    User create(User user);
    User findById(Long id);
}
