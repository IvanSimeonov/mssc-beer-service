package ivansimeonov.springframework.msscbeerservice.repositories;

import ivansimeonov.springframework.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 14.04.22
 */
@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
