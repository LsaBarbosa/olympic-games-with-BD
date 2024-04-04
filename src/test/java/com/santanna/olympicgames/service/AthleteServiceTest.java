package com.santanna.olympicgames.service;

import com.santanna.olympicgames.domain.dto.AthleteRequestDTO;
import com.santanna.olympicgames.domain.dto.AthleteResponseDTO;
import com.santanna.olympicgames.domain.dto.UpdateAthleteDTO;
import com.santanna.olympicgames.domain.entity.Athlete;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AthleteServiceTest {
    @Mock
    private AthleteRepository athleteRepository;

    @InjectMocks
    private AthleteService athleteService;

    @Test
    @DisplayName("Must Create Athlete")
    public void mustCreateAthlete() {
        AthleteRequestDTO athleteDTO = new AthleteRequestDTO(null, "John Doe", 75, 180.5, 25,   Country.UNITED_STATES, Gender.MALE, Sport.FOOTBALL);
        AthleteResponseDTO expectedAthlete = new AthleteResponseDTO("John Doe", 75, 180.5, 25,   Country.UNITED_STATES, Gender.MALE, Sport.FOOTBALL);

        given(athleteRepository.save(any(Athlete.class))).willReturn(new Athlete(athleteDTO));

        AthleteResponseDTO createdAthlete = athleteService.createAthlete(athleteDTO);

        assertThat(createdAthlete).isEqualTo(expectedAthlete);
    }

    @Test
    @DisplayName("Must Update Athlete")
    public void mustUpdateAthlete() {
        UpdateAthleteDTO updateDTO = new UpdateAthleteDTO(1L, "John Doe", 26, 181.0, 76,   Country.UNITED_STATES, Sport.FOOTBALL);
        Athlete originalAthlete = new Athlete(new AthleteRequestDTO(1L, "John Doe", 25, 180.5, 75,   Country.UNITED_STATES, Gender.MALE, Sport.FOOTBALL));

        given(athleteRepository.findById(updateDTO.id())).willReturn(Optional.of(originalAthlete));

        athleteService.updateAthlete(updateDTO);

        assertThat(originalAthlete.getName()).isEqualTo("John Doe");
        assertThat(originalAthlete.getAge()).isEqualTo(26);
        assertThat(originalAthlete.getHeight()).isEqualTo(181.0);
        assertThat(originalAthlete.getWeight()).isEqualTo(76);

        assertThat(originalAthlete.getCountry()).isEqualTo(Country.UNITED_STATES);
        assertThat(originalAthlete.getSport()).isEqualTo(Sport.FOOTBALL);
    }

    @Test
    @DisplayName("Must Get Athlete By Id")
    public void mustGetAthleteById() {
        Long athleteId = 1L;
        Athlete athlete = new Athlete(new AthleteRequestDTO(athleteId, "John Doe", 75, 180.5, 25,  Country.UNITED_STATES, Gender.MALE, Sport.FOOTBALL));

        given(athleteRepository.findById(athleteId)).willReturn(Optional.of(athlete));

        AthleteRequestDTO result = athleteService.getAthleteById(athleteId);
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(athleteId);
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.weight()).isEqualTo(75);
        assertThat(result.height()).isEqualTo(180.5);
        assertThat(result.age()).isEqualTo(25);

        assertThat(result.country()).isEqualTo(Country.UNITED_STATES);
        assertThat(result.gender()).isEqualTo(Gender.MALE);
        assertThat(result.sport()).isEqualTo(Sport.FOOTBALL);

    }

    @Test
    @DisplayName("Must Get All Athlete")
    public void mustGetAllAthletes() {
        List<Athlete> athletes = new ArrayList<>();
        athletes.add(new Athlete(new AthleteRequestDTO(1L, "John Doe", 75, 180.5, 25,   Country.UNITED_STATES, Gender.MALE, Sport.FOOTBALL)));
        athletes.add(new Athlete(new AthleteRequestDTO(2L, "Jane Smith", 60, 170.0, 30,  Country.CANADA, Gender.FEMALE, Sport.BASKETBALL)));

        Page<Athlete> pagedAthletes = new PageImpl<>(athletes);

        Pageable pageable = PageRequest.of(0, 10);

        given(athleteRepository.findAll(pageable)).willReturn(pagedAthletes);

        Page<AthleteRequestDTO> result = athleteService.getAllAthletes(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).name()).isEqualTo("John Doe");
        assertThat(result.getContent().get(1).name()).isEqualTo("Jane Smith");
    }

    @Test
    @DisplayName("Must Delete Existing Athlete")
    public void mustDeleteExistingAthlete() {
        Long athleteId = 1L;
        Athlete athlete = new Athlete();
        athlete.setId(athleteId);

        given(athleteRepository.findById(athleteId)).willReturn(Optional.of(athlete));
        doNothing().when(athleteRepository).deleteById(athleteId);

        athleteService.deleteAthlete(athleteId);

        verify(athleteRepository, times(1)).deleteById(athleteId);
    }


}