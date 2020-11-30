package ru.alexsumin.healthtracker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.alexsumin.healthtracker.core.domain.entity.Measurement;

import java.math.BigDecimal;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {


    //todo: native query
    Measurement findFirstByUser_IdOrderByCreationDateDesc(Long userId);
    List<Measurement> findAllByUser_Id(Long userId);


    @Modifying
    @Query(value = "DELETE FROM measurements " +
            "WHERE id = (SELECT id " +
            "            FROM   measurements " +
            "            WHERE user_id=:id AND data_type=:type " +
            "            ORDER  BY id DESC " +
            "            LIMIT  1);", nativeQuery = true)
    void removeLast(@Param("id") Long id, @Param("type")String type);

    @Query(value = "SELECT data " +
            "            FROM  measurements " +
            "            WHERE user_id=:id AND data_type=:type " +
            "            ORDER  BY id ASC " +
            "            LIMIT  1;", nativeQuery = true)
    BigDecimal findFirst(@Param("id") Long id, @Param("type")String type);

    @Query(value = "SELECT data " +
            "            FROM  measurements " +
            "            WHERE user_id=:id AND data_type=:type " +
            "            ORDER  BY id DESC " +
            "            LIMIT  1;", nativeQuery = true)
    BigDecimal findLast(@Param("id") Long id, @Param("type")String type);

}
