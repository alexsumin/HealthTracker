package ru.alexsumin.healthtracker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.healthtracker.core.domain.entity.Measurement;
import ru.alexsumin.healthtracker.core.repository.MeasurementRepository;
import ru.alexsumin.healthtracker.core.service.MeasurementService;
import ru.alexsumin.healthtracker.core.service.UserService;
import ru.alexsumin.healthtracker.core.util.MeasurementCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository repository;
    private final UserService userService;
    private final MeasurementCalculator calc;

    @Transactional
    @Override
    public Optional<BigDecimal> addNewAndReturnDifference(Measurement measurement, Long id) {
        var usersLastMeasurement = getUsersLastMeasurement(id);

        measurement.setCreationDate(LocalDateTime.now());
        measurement.setUser(userService.findById(id));
        var saved = repository.save(measurement);

        if (usersLastMeasurement != null) {
            return Optional.of(calc.calcDifference(usersLastMeasurement.getData(), saved.getData()));
        }
        return Optional.empty();
    }

    @Override
    public Measurement getUsersLastMeasurement(Long accountId) {
        return repository.findFirstByUser_IdOrderByCreationDateDesc(accountId);
    }

    @Transactional
    @Override
    public void removeUsersLastMeasurement(Long id) {
        repository.removeLast(id);
    }

    @Transactional(readOnly = true)
    @Override
    public BigDecimal stat(Long id) {
        //todo: one query? stored procedure?
        BigDecimal first = repository.findFirst(id);
        BigDecimal last = repository.findLast(id);

        return first.subtract(last);
    }

}
