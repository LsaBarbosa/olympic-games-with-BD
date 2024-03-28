package com.santanna.olympicgames.repository;

import com.santanna.olympicgames.domain.dto.SportsDTO;
import com.santanna.olympicgames.domain.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete,Long> {
    @Query("SELECT a FROM Athlete a WHERE UPPER(a.sport) = UPPER(:sport)")
    List<SportsDTO> findBySport(@Param("sport") String sport);

}
