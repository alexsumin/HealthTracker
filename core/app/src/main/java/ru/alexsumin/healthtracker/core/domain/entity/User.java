package ru.alexsumin.healthtracker.core.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString(exclude = "measurements")
@EqualsAndHashCode(exclude = "measurements")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @Column(name = "id")
    Long id;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Measurement.class,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id", updatable = false)
    List<Measurement> measurements = new ArrayList<>();

}
