package com.santanna.olympicgames.domain.dto;

import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Sport;

public record GenderCountDTO(Country country, Sport sport, long maleCount, long femaleCount) {

}
