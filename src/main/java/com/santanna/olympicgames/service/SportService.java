package com.santanna.olympicgames.service;

import com.santanna.olympicgames.domain.dto.GenderAthleteDTO;
import com.santanna.olympicgames.domain.dto.SportsDTO;
import com.santanna.olympicgames.domain.enums.Gender;
import com.santanna.olympicgames.domain.enums.Sport;
import com.santanna.olympicgames.exceptions.ValidationException;
import com.santanna.olympicgames.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SportService {

    @Autowired
    private AthleteRepository athleteRepository;

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

    public List<GenderAthleteDTO> findGenderAthletesBySport(Sport sport, String gender) {

        if (Objects.equals(gender, "MALE")) {
            return athleteRepository.findBySportAndGender(sport, Gender.MALE);
        } else if (Objects.equals(gender, "FEMALE")) {
            return athleteRepository.findBySportAndGender(sport, Gender.FEMALE);
        } else {
            throw new ValidationException(HttpStatusCode.valueOf(400), " Sports or Gender does not exist");
        }
    }

}