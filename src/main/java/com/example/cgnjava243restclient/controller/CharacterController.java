package com.example.cgnjava243restclient.controller;

import com.example.cgnjava243restclient.model.RickAndMortyChar;
import com.example.cgnjava243restclient.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
