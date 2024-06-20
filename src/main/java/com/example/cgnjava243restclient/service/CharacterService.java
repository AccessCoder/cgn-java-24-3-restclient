package com.example.cgnjava243restclient.service;

import com.example.cgnjava243restclient.exceptions.InvalidIdException;
import com.example.cgnjava243restclient.model.RickAndMortyChar;
import com.example.cgnjava243restclient.model.RickAndMortyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.List;

@Service
public class CharacterService {

    private final RestClient restClient;

    //Wir lagern die URL in die Environment Variablen aus und lassen sie durch die @Value Annotation (von Spring, NICHT lombok) auslesen!
    public CharacterService(@Value("${RICK_URL}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<RickAndMortyChar> getAllChars() throws IOException {
        RickAndMortyResponse response = restClient.get()
                .uri("/character")
                .retrieve() //schickt Anfrage ab!
                .body(RickAndMortyResponse.class);
        if (response != null){
            return response.getResults();
        } else {
            throw new IOException("No Data Found");
        }

    }

    public RickAndMortyChar getRickAndMortyCharById(int id) throws InvalidIdException {
        if (id <= 826){
            return restClient.get()
                    .uri("/character/"+id)
                    .retrieve()
                    .body(RickAndMortyChar.class);
        }else throw new InvalidIdException("No Character found with id: " + id);

    }
}
