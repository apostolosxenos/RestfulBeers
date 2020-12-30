package com.xenos.beers.repository;

import com.xenos.beers.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    List<Beer> findByBrand(String brand);

    List<Beer> findByAppearance(String appearance);

    List<Beer> findByAroma(String aroma);

    List<Beer> findByAlcohol(float alcohol);

}
