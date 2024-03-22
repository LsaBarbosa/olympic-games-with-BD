package com.santanna.olympicgames.service;

import com.santanna.olympicgames.domain.dto.AthleteRequestDTO;
import com.santanna.olympicgames.domain.dto.UpdateAthleteDTO;
import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public Page<AthleteRequestDTO> getAllAthletes(Pageable page) {
        return athleteRepository.findAll(page).map(AthleteRequestDTO::new);
    }

    public AthleteRequestDTO getAthleteById(Long id){
        var athlete = athleteRepository.findById(id).orElseThrow(()-> new RuntimeException("Athlete not found"));
        return new AthleteRequestDTO(athlete);
    }

    public AthleteRequestDTO createAthlete(AthleteRequestDTO athleteDTO){
        var athlete = new Athlete(athleteDTO);
        athleteRepository.save(athlete);
        return new AthleteRequestDTO(athlete);
    }

    public AthleteRequestDTO updateAthlete(UpdateAthleteDTO athleteDTO){
        var athlete = athleteRepository.findById(athleteDTO.id())
                .orElseThrow(()-> new RuntimeException("Athlete not found"));
        athlete.updateAthleteData(athleteDTO);
        athleteRepository.save(athlete);
        return new AthleteRequestDTO(athlete);
    }

    public void deleteAthlete(Long id){
        athleteRepository.deleteById(id);
    }

}
