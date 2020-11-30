package ru.alexsumin.healthtracker.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alexsumin.healthtracker.core.api.MeasurementDTO;
import ru.alexsumin.healthtracker.core.config.IgnoreUnmappedMapperConfig;
import ru.alexsumin.healthtracker.core.domain.entity.Measurement;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface MeasurementMapper {
    @Mapping(source = "value", target = "data")
    Measurement toMeasurement(MeasurementDTO measurementDTO);
}
