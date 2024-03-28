package com.santanna.olympicgames.service;

import com.santanna.olympicgames.domain.dto.AthleteRequestDTO;
import com.santanna.olympicgames.domain.dto.AthleteResponseDTO;
import com.santanna.olympicgames.domain.dto.SportsDTO;
import com.santanna.olympicgames.domain.dto.UpdateAthleteDTO;
import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.domain.enums.Sport;
import com.santanna.olympicgames.exceptions.ValidationException;
import com.santanna.olympicgames.repository.AthleteRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public Page<AthleteRequestDTO> getAllAthletes(Pageable page) {
        return athleteRepository.findAll(page).map(AthleteRequestDTO::new);
    }

    public AthleteRequestDTO getAthleteById(Long id) {
        var athlete = athleteRepository.findById(id).orElseThrow(() -> new ValidationException("Athlete not found"));
        return new AthleteRequestDTO(athlete);
    }

    public AthleteResponseDTO createAthlete(AthleteRequestDTO athleteDTO) {
        try {
            var athlete = new Athlete(athleteDTO);
            athleteRepository.save(athlete);
            return new AthleteResponseDTO(athlete);
        } catch (ConstraintViolationException ex) {
            throw new ValidationException("Error creating athlete: Invalid fields" + ex.getMessage());
        }
    }

    public AthleteResponseDTO updateAthlete(UpdateAthleteDTO athleteDTO) {
        try {
            var athlete = athleteRepository.findById(athleteDTO.id()).orElseThrow(() -> new ValidationException("Athlete not found"));
            athlete.updateAthleteData(athleteDTO);
            athleteRepository.save(athlete);
            return new AthleteResponseDTO(athlete);
        } catch (ConstraintViolationException ex) {
            throw new ValidationException("Error update athlete: Invalid fields" + ex.getMessage());
        }
    }

    public void deleteAthlete(Long id) {
        boolean athleteExist = athleteRepository.findById(id).isPresent();
        if (athleteExist) {
            athleteRepository.deleteById(id);
        } else {
            throw new ValidationException("Athlete not found");
        }

    }

    public List<SportsDTO> findAthletesBySport(Sport sport) {
        try {
            String sportUpcase = sport.name().toUpperCase();
            return athleteRepository.findBySport(sportUpcase);
        } catch (MethodArgumentTypeMismatchException exception) {
            throw new ValidationException("The ''sport'' must have capital letters" + exception.getMessage());
        }
    }
}

