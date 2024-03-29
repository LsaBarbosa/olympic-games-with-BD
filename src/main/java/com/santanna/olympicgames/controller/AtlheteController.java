package com.santanna.olympicgames.controller;

import com.santanna.olympicgames.domain.dto.*;
import com.santanna.olympicgames.domain.enums.Sport;
import com.santanna.olympicgames.exceptions.ValidationException;
import com.santanna.olympicgames.service.AthleteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/atlhetes")
public class AtlheteController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping()
    public ResponseEntity<Page<AthleteRequestDTO>> getAthletes(Pageable page) {
        var athletesByPage = athleteService.getAllAthletes(page);
        return ResponseEntity.ok(athletesByPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AthleteRequestDTO> getAthleteById(@PathVariable Long id) {

        AthleteRequestDTO athlete = athleteService.getAthleteById(id);
        return ResponseEntity.ok(athlete);

    }

    @GetMapping("/sport/all")
    public ResponseEntity<List<SportsDTO>> getAthletesBySport(@RequestParam String sport) {

        List<SportsDTO> athletes = athleteService.findAthletesBySport(sport);
        return ResponseEntity.ok(athletes);

    }

    @GetMapping("/sport-by-country")
    public ResponseEntity<CountryAndSportsDTO> sportByCountry(@RequestParam String country) {

        List<Sport> sportsByCountry = athleteService.sportsByCountry(country);
        return ResponseEntity.ok(new CountryAndSportsDTO(country.toUpperCase(), sportsByCountry));

    }

    @PostMapping

    public ResponseEntity<AthleteResponseDTO> creatAthlete(@RequestBody @Valid AthleteRequestDTO athleteDTO, UriComponentsBuilder uriBuilder) {
        try {
            AthleteResponseDTO athlete = athleteService.createAthlete(athleteDTO);
            String url = "/athletes/{id}";
            URI uri = uriBuilder.path(url).buildAndExpand(athleteDTO.id()).toUri();
            return ResponseEntity.created(uri).body(athlete);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().build();

        }
    }

    @PutMapping

    public ResponseEntity<String> updateDataAthlete(@RequestBody @Valid UpdateAthleteDTO athleteDTO) {
        athleteService.updateAthlete(athleteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<AthleteRequestDTO> deleteAthlete(@PathVariable Long id) {
        try {
            athleteService.deleteAthlete(id);
            return ResponseEntity.noContent().build();
        } catch (ValidationException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}

