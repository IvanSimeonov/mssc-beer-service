package ivansimeonov.springframework.msscbeerservice.web.mappers;

import ivansimeonov.springframework.msscbeerservice.domain.Beer;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * @Author ivansimeonov
 * @Date 20.04.22
 */
@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
