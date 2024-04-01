package com.santanna.olympicgames.service;

import com.santanna.olympicgames.domain.dto.GenderAthleteDTO;
import com.santanna.olympicgames.domain.dto.SportsDTO;
import com.santanna.olympicgames.domain.enums.Country;
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

    public List<GenderAthleteDTO> findGenderAthletesBySport(String sport, String gender) {
        String genderUperCase = gender.toUpperCase();
        String sportUperCase = sport.toUpperCase();

        if (Objects.equals(genderUperCase, "MALE")) {
            return athleteRepository.findBySportAndGender(Sport.valueOf(sportUperCase), Gender.MALE);
        } else if (Objects.equals(genderUperCase, "FEMALE")) {
            return athleteRepository.findBySportAndGender(Sport.valueOf(sportUperCase), Gender.FEMALE);
        } else {
            throw new ValidationException(HttpStatusCode.valueOf(400), " Sports or Gender does not exist");
        }
    }

    public List<GenderAthleteDTO> findByGenderAndCoutryAndSport(String sport, String gender, String country) {
        String sportUperCase = sport.toUpperCase();
        String countryUperCase = country.toUpperCase();
        String genderUperCase = gender.toUpperCase();

        if (Objects.equals(genderUperCase, "MALE")) {
            return athleteRepository.findByGenderAndCoutryAndSport(Sport.valueOf(sportUperCase), Gender.MALE, Country.valueOf(countryUperCase));
        } else if (Objects.equals(genderUperCase, "FEMALE")) {
            return athleteRepository.findByGenderAndCoutryAndSport(Sport.valueOf(sportUperCase), Gender.FEMALE, Country.valueOf(countryUperCase));
        } else {
            throw new ValidationException(HttpStatusCode.valueOf(400), " Sports or Gender does not exist");
        }
    }

}