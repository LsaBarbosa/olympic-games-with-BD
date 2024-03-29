package com.santanna.olympicgames.service;

import com.santanna.olympicgames.domain.dto.*;
import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Sport;
import com.santanna.olympicgames.exceptions.ValidationException;
import com.santanna.olympicgames.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public Page<AthleteRequestDTO> getAllAthletes(Pageable page) {
        return athleteRepository.findAll(page).map(AthleteRequestDTO::new);
    }

    public AthleteRequestDTO getAthleteById(Long id) {
        var athlete = athleteRepository.findById(id).orElseThrow(() -> new ValidationException(HttpStatusCode.valueOf(404), "Athlete not found"));
        return new AthleteRequestDTO(athlete);
    }

    public AthleteResponseDTO createAthlete(AthleteRequestDTO athleteDTO) {
        try {
            var athlete = new Athlete(athleteDTO);
            athleteRepository.save(athlete);
            return new AthleteResponseDTO(athlete);
        } catch (Exception ex) {
            throw new ValidationException(HttpStatusCode.valueOf(400), "Error creating athlete: Invalid fields" + ex.getMessage());
        }
    }

    public AthleteResponseDTO updateAthlete(UpdateAthleteDTO athleteDTO) {
        try {
            var athlete = athleteRepository.findById(athleteDTO.id()).orElseThrow(() -> new ValidationException(HttpStatusCode.valueOf(404), "Athlete not found"));
            athlete.updateAthleteData(athleteDTO);
            athleteRepository.save(athlete);
            return new AthleteResponseDTO(athlete);
        } catch (Exception ex) {
            throw new ValidationException(HttpStatusCode.valueOf(400), "Error update athlete: Invalid fields");
        }
    }

    public void deleteAthlete(Long id) {
        boolean athleteExist = athleteRepository.findById(id).isPresent();
        if (athleteExist) {
            athleteRepository.deleteById(id);
        } else {
            throw new ValidationException(HttpStatusCode.valueOf(404), "Athlete not found");
        }

    }

    public List<SportsDTO> findAthletesBySport(String sport) {
        try {
            String sportUpcase = sport.toUpperCase();
            return athleteRepository.findBySport(sportUpcase);
        } catch (Exception exception) {
            throw new ValidationException(HttpStatusCode.valueOf(404), "Sport not found");
        }
    }

    public List<Sport> sportsByCountry(String country) {
        String countryUpcase = country.toUpperCase();
        return athleteRepository.findSportsByCountry(countryUpcase).orElseThrow(() -> new ValidationException(HttpStatusCode.valueOf(404), "Country not Found"));
    }


}

