package ru.alexsumin.healthtracker.core.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.alexsumin.healthtracker.core.api.MeasurementType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "measurements")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "data")
    BigDecimal data;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "data_type")
    MeasurementType type;

    @ManyToOne
    User user;
}
