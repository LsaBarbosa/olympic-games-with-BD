package com.santanna.olympicgames.domain.dto;

import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Gender;
import com.santanna.olympicgames.domain.enums.Sport;

public record AthleteRequestDTO(Long id,String name, Integer weight, double height, int age,   Country country, Gender gender, Sport sport) {
    public AthleteRequestDTO(Athlete athlete) {
        this(athlete.getId(),athlete.getName(),athlete.getWeight(),athlete.getHeight(),athlete.getAge(), athlete.getCountry(),athlete.getGender(),athlete.getSport());
    }

}
