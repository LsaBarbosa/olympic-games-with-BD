package com.santanna.olympicgames.repository;

import com.santanna.olympicgames.domain.dto.CountryAndSportsDTO;
import com.santanna.olympicgames.domain.dto.SportsDTO;
import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    @Query("SELECT a FROM Athlete a WHERE UPPER(a.sport) = UPPER(:sport)")
    List<SportsDTO> findBySport(@Param("sport") String sport);

    @Query("SELECT DISTINCT a.sport FROM Athlete a WHERE UPPER(a.country) = UPPER(:country)")
    Optional<List<Sport>> findSportsByCountry(@Param("country") String country);

    @Query("SELECT DISTINCT a.sport FROM Athlete a WHERE UPPER(a.country) = UPPER(:country)")
    Optional<List<Athlete>> findAtlheteByCountry(@Param("country") String country);

}

