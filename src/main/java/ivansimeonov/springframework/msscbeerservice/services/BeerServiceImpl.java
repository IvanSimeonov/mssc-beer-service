package ivansimeonov.springframework.msscbeerservice.services;

import ivansimeonov.springframework.msscbeerservice.domain.Beer;
import ivansimeonov.springframework.msscbeerservice.repositories.BeerRepository;
import ivansimeonov.springframework.msscbeerservice.web.controller.NotFoundException;
import ivansimeonov.springframework.msscbeerservice.web.mappers.BeerMapper;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerDto;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerPagedList;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author ivansimeonov
 * @Date 22.04.22
 */
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Autowired
    public BeerServiceImpl(BeerMapper beerMapper, BeerRepository beerRepository) {
        this.beerMapper = beerMapper;
        this.beerRepository = beerRepository;
    }

    @Override
    public BeerPagedList beersList(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        if (!StringUtils.hasLength(beerName) && !StringUtils.hasLength(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.hasLength(beerName) && StringUtils.hasLength(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.hasLength(beerName) && !StringUtils.hasLength(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }
        if (showInventoryOnHand) {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());

        } else {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());

        }
        return beerPagedList;
    }

    @Override
    public BeerDto getByUpc(String upc) {
        return beerMapper.beerToBeerDto(beerRepository.findBeerByUpc(upc));
    }

    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));

        } else {
            return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
        }
    }

    @Override
    public BeerDto addNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeerById(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerName());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        beerRepository.deleteById(beerId);
    }
}
