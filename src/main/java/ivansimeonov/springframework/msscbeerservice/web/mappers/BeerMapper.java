package ivansimeonov.springframework.msscbeerservice.web.mappers;

import ivansimeonov.springframework.msscbeerservice.domain.Beer;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author ivansimeonov
 * @Date 20.04.22
 */
@Component
@Mapper(uses = DateMapper.class, componentModel = "spring")
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
