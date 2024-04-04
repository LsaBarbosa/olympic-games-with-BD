package com.santanna.olympicgames.domain.dto;

import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Gender;
import com.santanna.olympicgames.domain.enums.Sport;

public record AthleteResponseDTO(String name, Integer weight, double height, int age,
                                 Country country, Gender gender, Sport sport) {

    public AthleteResponseDTO(Athlete athlete) {
        this(athlete.getName(), athlete.getWeight(), athlete.getHeight(), athlete.getAge(),   athlete.getCountry(), athlete.getGender(), athlete.getSport());
    }

}
