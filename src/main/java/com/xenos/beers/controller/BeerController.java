package com.xenos.beers.controller;

import com.xenos.beers.model.Beer;
import com.xenos.beers.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/beers/{uuid}")
    public ResponseEntity<Beer> getTutorialByUuid(@PathVariable("uuid") UUID uuid) {

        Optional<Beer> beer = beerRepository.findById(uuid);

        if (beer.isPresent()) {

            return new ResponseEntity<>(beer.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/beers")
    public ResponseEntity<Beer> createTutorial(@RequestBody Beer beer) {

        try {

            Beer newBeer = beerRepository
                    .save(new Beer(beer.getBrand(),
                            beer.getAppearance(),
                            beer.getAroma(),
                            beer.getAlcohol()));
            return new ResponseEntity<>(newBeer, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/beers/{uuid}")
    public ResponseEntity<Beer> updateBeer(@PathVariable("uuid") UUID uuid, @RequestBody Beer beer) {

        Optional<Beer> beerInDatabase = beerRepository.findById(uuid);

        if (beerInDatabase.isPresent()) {

            if(beer.getBrand() == null || beer.getBrand().isEmpty()) {
                beer.setBrand(beerInDatabase.get().getBrand());
            }

            if(beer.getAppearance() == null || beer.getAppearance().isEmpty()) {
                beer.setAppearance(beerInDatabase.get().getAppearance());
            }

            if(beer.getAroma() == null || beer.getAroma().isEmpty()) {
                beer.setAroma(beerInDatabase.get().getAroma());
            }

            if(beer.getAlcohol() == 0.0f) {
                beer.setAlcohol(beerInDatabase.get().getAlcohol());
            }

            beer.setUuid(beerInDatabase.get().getUuid());

            return new ResponseEntity<>(beerRepository.save(beer), HttpStatus.OK);

        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/beers/{uuid}")
    public ResponseEntity<HttpStatus> deleteBeer(@PathVariable("uuid") UUID uuid) {

        Optional<Beer> beerInDatabase = beerRepository.findById(uuid);

        if (beerInDatabase.isPresent()) {
            beerRepository.deleteById(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/beers")
    public ResponseEntity<HttpStatus> deleteAllBeers() {
        beerRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
