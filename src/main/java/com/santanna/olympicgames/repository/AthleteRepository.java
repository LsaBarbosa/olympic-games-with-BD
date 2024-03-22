package com.santanna.olympicgames.repository;

import com.santanna.olympicgames.domain.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete,Long> {
}
