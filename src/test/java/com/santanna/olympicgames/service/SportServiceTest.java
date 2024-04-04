package com.santanna.olympicgames.service;

import com.santanna.olympicgames.domain.dto.AthleteRequestDTO;
import com.santanna.olympicgames.domain.dto.CountryAndSportsDTO;
import com.santanna.olympicgames.domain.dto.GenderAthleteDTO;
import com.santanna.olympicgames.domain.dto.SportsDTO;
import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Continent;
import com.santanna.olympicgames.domain.enums.Country;
import com.santanna.olympicgames.domain.enums.Gender;
import com.santanna.olympicgames.domain.enums.Sport;
import com.santanna.olympicgames.repository.AthleteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class SportServiceTest {
    @InjectMocks
    SportService sportService;

    @Mock
    AthleteRepository athleteRepository;

    @Test
    @DisplayName("Must find athlete by sport")
    public void mustFindAthletesBySport() {
        String sport = "football";
        Athlete athlete1 = new Athlete(new AthleteRequestDTO(1L, "John Doe", 75, 180.5, 25, Continent.EUROPE, Country.UNITED_STATES, Gender.MALE, Sport.FOOTBALL));
        Athlete athlete2 = new Athlete(new AthleteRequestDTO(2L, "Jane Smith", 60, 170.0, 30, Continent.NORTH_AMERICA, Country.CANADA, Gender.FEMALE, Sport.FOOTBALL));

        List<SportsDTO> expectedAthletes = List.of(new SportsDTO(athlete1), new SportsDTO(athlete2));

        given(athleteRepository.findBySport(sport.toUpperCase())).willReturn(expectedAthletes);

        List<SportsDTO> foundAthletes = sportService.findAthletesBySport(sport);

        assertThat(foundAthletes).isNotNull();
        assertThat(foundAthletes.size()).isEqualTo(2);
        assertThat(foundAthletes).isEqualTo(expectedAthletes);
    }

    @Test
    @DisplayName("Must find sports by country")
    public void mustFindSportsByCountry() {
        String countryName = "brazil";
        String country = countryName.toUpperCase();
        List<Sport> expectedSports = Arrays.asList(Sport.FOOTBALL, Sport.SOCCER);
        given(athleteRepository.findSportsByCountry(country)).willReturn(Optional.of(expectedSports));

        List<Sport> actualSports = sportService.sportsByCountry(country);

        assertThat(actualSports).isNotEmpty();
        assertThat(actualSports).containsExactlyElementsOf(expectedSports);

    }

    @Test
    @DisplayName("Must find sports by gender")
    public void mustFindSportByGender() {
        String sport = "football";
        String gender = "male";
        List<GenderAthleteDTO> expectedAthletes = Arrays.asList(new GenderAthleteDTO("John Doe", 25, Country.UNITED_STATES, Gender.MALE, Sport.FOOTBALL), new GenderAthleteDTO("Jane Smith", 27, Country.CANADA, Gender.MALE, Sport.FOOTBALL));

        given(athleteRepository.findBySportAndGender(any(Sport.class), any(Gender.class))).willReturn(expectedAthletes);

        List<GenderAthleteDTO> result = sportService.findGenderAthletesBySport(sport.toUpperCase(), gender.toUpperCase());

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(expectedAthletes);
    }

    @Test
    @DisplayName("Must find Athletes by gender Sport and Country")
    public void mustFindAthletesByGenderSportAndCountry(){
        String sport = "FOOTBALL";
        String gender ="MALE";
        String country ="CANADA";

        List<GenderAthleteDTO> expectedAthletes = Arrays.asList
                (new GenderAthleteDTO("John Doe", 25, Country.CANADA, Gender.MALE, Sport.FOOTBALL),
                        new GenderAthleteDTO("Jane Smith", 27, Country.CANADA, Gender.MALE, Sport.FOOTBALL));

        given(athleteRepository.findByGenderAndCoutryAndSport(any(Sport.class), any(Gender.class), any(Country.class))).willReturn(expectedAthletes);
        List<GenderAthleteDTO> result = sportService.findByGenderAndCoutryAndSport(sport.toUpperCase(), gender.toUpperCase(),country.toUpperCase());

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(expectedAthletes);

        assertThat(result.get(0).name()).isEqualTo("John Doe");
        assertThat(result.get(0).age()).isEqualTo(25);
        assertThat(result.get(0).country()).isEqualTo(Country.CANADA);
        assertThat(result.get(0).gender()).isEqualTo(Gender.MALE);
        assertThat(result.get(0).sport()).isEqualTo(Sport.FOOTBALL);

        assertThat(result.get(1).name()).isEqualTo("Jane Smith");
        assertThat(result.get(1).age()).isEqualTo(27);
        assertThat(result.get(1).country()).isEqualTo(Country.CANADA);
        assertThat(result.get(1).gender()).isEqualTo(Gender.MALE);
        assertThat(result.get(1).sport()).isEqualTo(Sport.FOOTBALL);

    }
}