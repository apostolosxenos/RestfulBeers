package com.xenos.beers.dao;

import com.xenos.beers.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerDao {

    int insertNewBeer(UUID beerId, Beer beer);

    Beer selectBeerById(UUID beerId);

    List<Beer> selectAllBeers();

    int updateBeerById(UUID beerId, Beer beerUpdate);

    int deleteBeerById(UUID beerId);
}
