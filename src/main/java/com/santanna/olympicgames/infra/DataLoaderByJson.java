package com.santanna.olympicgames.infra;

import com.santanna.olympicgames.exceptions.ValidationException;
import com.santanna.olympicgames.service.JsonDataService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataLoaderByJson {
    @Autowired
    private JsonDataService service;

    @Value("${json.file}")
    private String jsonFile;

    @PostConstruct
    public void loadData() {
        try {
            service.populateOlympicGamesDataBaseFromJson(jsonFile);
        } catch (IOException e) {
            e.printStackTrace(); // Adicione esta linha
            throw new ValidationException(HttpStatusCode.valueOf(500), "Internal Server Error");
        }
    }
}
