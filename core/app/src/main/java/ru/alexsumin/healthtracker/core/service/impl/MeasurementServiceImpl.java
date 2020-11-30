package ru.alexsumin.healthtracker.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.healthtracker.core.api.MeasurementType;
import ru.alexsumin.healthtracker.core.domain.entity.Measurement;
import ru.alexsumin.healthtracker.core.repository.MeasurementRepository;
import ru.alexsumin.healthtracker.core.service.MeasurementService;
import ru.alexsumin.healthtracker.core.util.ProjectUtil;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository repository;

    @Transactional
    @Override
    public BigDecimal addNewAndReturnDifference(Measurement measurement, Long id) {
        var usersLastMeasurement = getUsersLastMeasurement(id);
        var saved = repository.save(measurement);
        return ProjectUtil.calcDifference(usersLastMeasurement.getData(), saved.getData());
    }

    @Override
    public Measurement getUsersLastMeasurement(Long accountId) {
        return repository.findFirstByUser_IdOrderByCreationDateDesc(accountId);
    }

    @Transactional
    @Override
    public void removeUsersLastMeasurement(Long id, MeasurementType type) {
        repository.removeLast(id, type.toString());
    }

    @Transactional(readOnly = true)
    @Override
    public BigDecimal stat(Long id, MeasurementType type) {
        //todo: one query? stored procedure?
        BigDecimal first = repository.findFirst(id, type.toString());
        BigDecimal last = repository.findLast(id, type.toString());

        return first.subtract(last);
    }

}
