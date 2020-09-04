package com.xenos.beers.service;

import com.xenos.beers.dao.BeerDao;
import com.xenos.beers.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BeerService {

    private final BeerDao beerDao;

    @Autowired
    public BeerService(@Qualifier("fakeDao") BeerDao beerDao) {
        this.beerDao = beerDao;
    }

    public int postNewBeer(UUID beerId, Beer beer) {
        UUID beerUuid = beerId == null ? UUID.randomUUID() : beerId;
        return beerDao.insertNewBeer(beerUuid, beer);
    }

    public Beer getBeerById(UUID beerId) {
        return beerDao.selectBeerById(beerId);
    }

    public List<Beer> getAllBeers() {
        return beerDao.selectAllBeers();
    }

    public int putBeerById(UUID beerId, Beer beerUpdate) {
        return beerDao.updateBeerById(beerId, beerUpdate);
    }

    public int removeBeerById(UUID beerId) {
        return beerDao.deleteBeerById(beerId);
    }
}
