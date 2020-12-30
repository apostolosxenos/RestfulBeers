package com.xenos.beers.controller;


import com.xenos.beers.model.Beer;
import com.xenos.beers.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BeerController {

    @Autowired
    BeerRepository beerRepository;

    @GetMapping("/beers")
    public ResponseEntity<List<Beer>> getAllBeers(@RequestParam(required = false) String brand) {

        try {

            List<Beer> beers = new ArrayList<>();

            if (brand == null) {
                beerRepository.findAll().forEach(beers::add);
            } else {
                beerRepository.findByBrand(brand).forEach(beers::add);
            }

            if (beers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(beers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/beers")
    public ResponseEntity<Beer> createTutorial(@RequestBody Beer beer) {

        try {

            Beer newBeer = beerRepository
                    .save(new Beer(UUID.randomUUID(),
                            beer.getBrand(),
                            beer.getAppearance(),
                            beer.getAroma(),
                            beer.getAlcohol()));
            return new ResponseEntity<>(newBeer, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
