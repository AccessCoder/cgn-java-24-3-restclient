package com.example.cgnjava243restclient.controller;

import com.example.cgnjava243restclient.exceptions.InvalidIdException;
import com.example.cgnjava243restclient.model.RickAndMortyChar;
import com.example.cgnjava243restclient.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/char")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService service;

    @GetMapping
    public List<RickAndMortyChar> getAllChars() throws IOException {
        return service.getAllChars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RickAndMortyChar> getCharById(@PathVariable int id) throws InvalidIdException {
        return new ResponseEntity<>(service.getRickAndMortyCharById(id), HttpStatus.CREATED);
    }


}
