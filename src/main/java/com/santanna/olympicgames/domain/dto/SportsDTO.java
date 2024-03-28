package com.santanna.olympicgames.domain.dto;

import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Continent;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Gender;
import com.santanna.olympicgames.domain.enums.Sport;

import java.util.List;

public record SportsDTO(String name, int age, Country country,
                        Gender gender, Sport sport) {


    public SportsDTO(Athlete athlete) {
        this(athlete.getName(), athlete.getAge(), athlete.getCountry(), athlete.getGender(), athlete.getSport());
    }

}
