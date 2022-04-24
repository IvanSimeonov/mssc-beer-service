package ivansimeonov.springframework.msscbeerservice.repositories;

import ivansimeonov.springframework.msscbeerservice.domain.Beer;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 14.04.22
 */
@Repository
public interface BeerRepository extends JpaRepository<Beer, UUID> {

    Beer findBeerByUpc(String upc);

    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

}
