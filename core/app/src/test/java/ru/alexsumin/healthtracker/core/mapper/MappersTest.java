package ru.alexsumin.healthtracker.core.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.alexsumin.healthtracker.core.api.MeasurementDTO;
import ru.alexsumin.healthtracker.core.api.UserDTO;
import ru.alexsumin.healthtracker.core.domain.entity.Measurement;
import ru.alexsumin.healthtracker.core.domain.entity.User;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = MappersTest.MapperTestConfig.class)
public class MappersTest {

    @Configuration
    @ComponentScan("ru.alexsumin.healthtracker.core.mapper")
    static class MapperTestConfig {
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MeasurementMapper measurementMapper;

    @Test
    public void toUserTest() {
        assertNotNull(userMapper);

        final Long id = 42L;
        UserDTO source = UserDTO.builder()
                .id(id)
                .build();
        User result = userMapper.toUser(source);

        Assertions.assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("measurements")
                .isEqualTo(source);
    }

    @Test
    public void toMeasurementTest() {
        assertNotNull(measurementMapper);

        final BigDecimal value = new BigDecimal("42");
        var source = MeasurementDTO.builder()
                .value(value)
                .build();

        Measurement result = measurementMapper.toMeasurement(source);

        assertEquals(source.getValue(), result.getData());
    }
}
