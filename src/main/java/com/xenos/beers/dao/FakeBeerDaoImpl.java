package com.xenos.beers.dao;

import com.xenos.beers.model.Beer;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDao")
public class FakeBeerDaoImpl implements BeerDao {

    private final Map<UUID, Beer> fakeDatabase;

    public FakeBeerDaoImpl(Map<UUID, Beer> fakeDatabase) {
        this.fakeDatabase = new HashMap<>();
    }

    @Override
    public int insertNewBeer(UUID beerId, Beer beer) {
        fakeDatabase.put(beerId, beer); //Map handles duplicate key exception
        return 1;
    }

    @Override
    public Beer selectBeerById(UUID beerId) {
        return fakeDatabase.get(beerId);
    }

    @Override
    public List<Beer> selectAllBeers() {
        return new ArrayList<>(fakeDatabase.values());
    }

    @Override
    public int updateBeerById(UUID beerId, Beer beerUpdate) {
        fakeDatabase.put(beerId,beerUpdate);
        return 1;
    }

    @Override
    public int deleteBeerById(UUID beerId) {
        fakeDatabase.remove(beerId);
        return 1;
    }
}
