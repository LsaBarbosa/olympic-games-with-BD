package com.santanna.olympicgames.domain.dto;

import com.santanna.olympicgames.domain.enums.Continent;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Sport;

public record UpdateAthleteDTO (
        Long id, String name, Integer age, double height, Integer weight, Continent continent, Country country, Sport sport
) {
}
