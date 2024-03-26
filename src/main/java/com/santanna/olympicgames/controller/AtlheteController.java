package com.santanna.olympicgames.controller;

import com.santanna.olympicgames.domain.dto.AthleteRequestDTO;
import com.santanna.olympicgames.domain.dto.AthleteResponseDTO;
import com.santanna.olympicgames.domain.dto.UpdateAthleteDTO;
import com.santanna.olympicgames.exceptions.ValidationException;
import com.santanna.olympicgames.service.AthleteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;

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
        try {
            AthleteRequestDTO athlete = athleteService.getAthleteById(id);
            return ResponseEntity.ok(athlete);
        } catch (ValidationException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AthleteResponseDTO> creatAthlete(@RequestBody AthleteRequestDTO athleteDTO, UriComponentsBuilder uriBuilder) {
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
    @Transactional
    public ResponseEntity<?> updateDataAthlete(@RequestBody UpdateAthleteDTO athleteDTO) {
        try {
            athleteService.updateAthlete(athleteDTO);
            return ResponseEntity.ok().build();
        } catch (ValidationException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<AthleteRequestDTO> deleteAthlete(@PathVariable Long id) {
        athleteService.deleteAthlete(id);
        return ResponseEntity.noContent().build();
    }
}

