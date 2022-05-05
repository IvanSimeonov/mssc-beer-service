package ivansimeonov.springframework.msscbeerservice.services;

import ivansimeonov.springframework.msscbeerservice.web.model.BeerDto;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerPagedList;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 22.04.22
 */
public interface BeerService {

    BeerPagedList beersList(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getByUpc(String upc);

    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto addNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, BeerDto beerDto);

    void deleteBeerById(UUID beerId);
}
