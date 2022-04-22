package ivansimeonov.springframework.msscbeerservice.services;

import ivansimeonov.springframework.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 22.04.22
 */
public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto addNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, BeerDto beerDto);

    void deleteBeerById(UUID beerId);
}
