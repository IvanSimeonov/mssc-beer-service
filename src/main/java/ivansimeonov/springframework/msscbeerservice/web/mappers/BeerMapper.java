package ivansimeonov.springframework.msscbeerservice.web.mappers;

import ivansimeonov.springframework.msscbeerservice.domain.Beer;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @Author ivansimeonov
 * @Date 20.04.22
 */
@Mapper(uses = DateMapper.class, componentModel = "spring")
public interface BeerMapper {
    @Mapping(target = "quantityOnHand", source = "")
    @Mapping(target = "lastModifiedDate", source = "")
    BeerDto beerToBeerDto(Beer beer);

    @Mapping(target = "quantityToBrew", source = "")
    @Mapping(target = "modifiedDate", source = "")
    @Mapping(target = "minOnHand", source = "")
    Beer beerDtoToBeer(BeerDto beerDto);
}
