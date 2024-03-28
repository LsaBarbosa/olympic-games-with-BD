package com.santanna.olympicgames.domain.dto;

import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Sport;

import java.util.List;

public record CountryAndSportsDTO(Country country,  List<Sport> sport) {

    public CountryAndSportsDTO(Country country, Sport sport) {
        this(country, List.of(sport));
    }
}
