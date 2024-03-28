package com.santanna.olympicgames.domain.dto;

import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Sport;

public record CountryAndSportsDTO(Country country, Sport sport) {

    public CountryAndSportsDTO(Athlete athlete){
        this(athlete.getCountry(),athlete.getSport());
    }
}
