package com.santanna.olympicgames.controller;

import com.santanna.olympicgames.domain.dto.CountryAndSportsDTO;
import com.santanna.olympicgames.domain.dto.GenderAthleteDTO;
import com.santanna.olympicgames.domain.dto.SportsDTO;
import com.santanna.olympicgames.domain.enums.Sport;
import com.santanna.olympicgames.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sport")
public class SportController {
    @Autowired
    private SportService sportService;

    @GetMapping("/athlete/all")
    public ResponseEntity<List<SportsDTO>> getAthletesBySport(@RequestParam String sport) {

        List<SportsDTO> athletes = sportService.findAthletesBySport(sport);
        return ResponseEntity.ok(athletes);

    }

    @GetMapping("/country")
    public ResponseEntity<CountryAndSportsDTO> sportByCountry(@RequestParam String country) {

        List<Sport> sportsByCountry = sportService.sportsByCountry(country);
        return ResponseEntity.ok(new CountryAndSportsDTO(country.toUpperCase(), sportsByCountry));

    }

    @GetMapping("/gender")
    public ResponseEntity<List<GenderAthleteDTO>> getAthletesByGenderAndSport(@RequestParam String sport, String gender) {

        List<GenderAthleteDTO> athletes = sportService.findGenderAthletesBySport(sport, gender);
        return ResponseEntity.ok(athletes);
    }

    @GetMapping("/gender-country")
    public ResponseEntity<List<GenderAthleteDTO>> findByGenderAndCoutryAndSport(@RequestParam String sport, String gender, String country) {

        List<GenderAthleteDTO> athletes = sportService.findByGenderAndCoutryAndSport(sport, gender, country);
        return ResponseEntity.ok(athletes);
    }
}
