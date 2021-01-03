package com.xenos.beers.service;

import com.xenos.beers.model.Beer;
import com.xenos.beers.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BeerService {

    @Autowired
    BeerRepository beerRepository;

    public List<Beer> getAllBeers() {
        List<Beer> beers = new ArrayList<>();
        beerRepository.findAll().forEach(beers::add);
        return beers;
    }

    public Beer getBeerByUuid(UUID uuid) {
        return beerRepository.findById(uuid).get();
    }

    public void insertBeer(Beer beer) {
        beerRepository.save(new Beer(beer.getBrand(), beer.getAppearance(), beer.getAroma(), beer.getAlcohol()));
    }

    public int updateBeer(UUID uuid, Beer beerData) {

        Optional<Beer> beerInDatabase = beerRepository.findById(uuid);

        if (beerInDatabase.isPresent()) {

            Beer beerToUpdate = beerInDatabase.get();
            beerToUpdate.setBrand(beerData.getBrand());
            beerToUpdate.setAppearance(beerData.getAppearance());
            beerToUpdate.setAroma(beerData.getAroma());
            beerToUpdate.setAlcohol(beerData.getAlcohol());
            beerRepository.save(beerToUpdate);
            return 1;
        }

        return -1;
    }

    public int deleteByUuid(UUID uuid) {
        Optional<Beer> beerInDatabase = beerRepository.findById(uuid);
        if (beerInDatabase.isPresent()) {
            beerRepository.deleteById(uuid);
            return 1;
        }
        return -1;
    }

    public int deleteAll() {
        beerRepository.deleteAll();
        return 1;
    }

}
