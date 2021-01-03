package com.xenos.beers.controller;

import com.xenos.beers.model.Beer;
import com.xenos.beers.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class BeerController {

    @Autowired
    BeerService beerService;

    @GetMapping("/beers")
    public ResponseEntity<List<Beer>> getAllBeers(@RequestParam(required = false) String brand) {
        if (beerService.getAllBeers().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return new ResponseEntity<>(beerService.getAllBeers(), HttpStatus.OK);
    }

    @GetMapping("/beers/{uuid}")
    public ResponseEntity<Beer> getBeerByUuid(@PathVariable("uuid") UUID uuid) {
        Optional<Beer> beer = Optional.ofNullable(beerService.getBeerByUuid(uuid));
        if (beer.isPresent()) return new ResponseEntity(beer, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/beers")
    public ResponseEntity<Beer> postBeer(@RequestBody Beer beer) {
        beerService.insertBeer(beer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/beers/{uuid}")
    public ResponseEntity<Beer> putBeer(@PathVariable("uuid") UUID uuid, @RequestBody Beer beerData) {
        if (beerService.updateBeer(uuid, beerData) == -1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/beers/{uuid}")
    public ResponseEntity<Beer> deleteBeerByUuid(@PathVariable("uuid") UUID uuid) {
        if (beerService.deleteByUuid(uuid) == -1)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/beers")
    public ResponseEntity<HttpStatus> deleteAllBeers() {
        if (beerService.deleteAll() != 1) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
