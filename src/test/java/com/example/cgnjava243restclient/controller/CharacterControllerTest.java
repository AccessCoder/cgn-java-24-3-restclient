package com.example.cgnjava243restclient.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CharacterControllerTest {

    @Autowired
    private MockMvc mvc;

    private static MockWebServer mockWebServer;

    @BeforeAll //Kennzeichnet Methoden die ausgeführt werden bevor die Tests starten.
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll //Kennzeichnet Methoden die ausgeführt werden, nachdem die Tests durchgelaufen sind.
    static void shutDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource //Gibt uns die Möglichkeit Umgebungsvariablen zu überschreiben
    static void backendProps(DynamicPropertyRegistry registry){ //<- DynamicPropertyRegistry muss übergeben werden!
        registry.add("RICK_URL", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getRickAndMortyCharById_shouldReturnRickSanchez_whenCalledWithId1() throws Exception {
        //GIVEN
        mockWebServer.enqueue(new MockResponse() // <- Wir stellen eine Response in die "Warteschlange", der Mockwebserver gibt sie bei erster Möglichkeit zurück!
                .addHeader("Content-Type", "application/json")
                .setBody("""
                        {
                         }
                       
                        """));
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/char/10000"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "apiPath": "uri=/api/char/10000",
                            "errorCode": "NOT_FOUND",
                            "errorMsg": "No Character found with id: 10000"
                            
                        }
                        """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorTime").isNotEmpty());
    }

}