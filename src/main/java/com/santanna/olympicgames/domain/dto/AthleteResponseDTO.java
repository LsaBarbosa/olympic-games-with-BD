package com.santanna.olympicgames.domain.dto;

import com.santanna.olympicgames.domain.enums.Continent;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Gender;
import com.santanna.olympicgames.domain.enums.Sport;

public record AthleteResponseDTO(String name, int weight, double height, int age, Continent continent, Country country,
                                 Gender gender, Sport sport) {
}
