package ru.alexsumin.healthtracker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.healthtracker.core.domain.entity.User;
import ru.alexsumin.healthtracker.core.repository.UserRepository;
import ru.alexsumin.healthtracker.core.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id)
                .orElse(repository.save(User.builder().id(id).build()));
    }

}
