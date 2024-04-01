package com.santanna.olympicgames.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santanna.olympicgames.domain.entity.Athlete;
import com.santanna.olympicgames.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class JsonDataService {
    @Autowired
    private AthleteRepository repository;

    public void populateOlympicGamesDataBaseFromJson(String jsonFile) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        List<Athlete> athletes =  objectMapper.readValue(new File(jsonFile), new TypeReference<List<Athlete>>() {});
        repository.saveAll(athletes);
    }
}
