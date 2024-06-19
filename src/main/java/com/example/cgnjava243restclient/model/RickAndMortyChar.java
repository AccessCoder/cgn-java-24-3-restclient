package com.example.cgnjava243restclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {
 *  *             "id": 1,
 *  *             "name": "Rick Sanchez",
 *  *             "status": "Alive",
 *  *             "species": "Human",
 *  *             "gender": "Male"
 *  *
 *  *
 *  *
 *  *         }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RickAndMortyChar {

    private int id;
    private String name;
    private String status;
    private String species;
    private String gender;
}
