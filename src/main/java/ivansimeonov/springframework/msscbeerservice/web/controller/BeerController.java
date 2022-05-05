package ivansimeonov.springframework.msscbeerservice.web.controller;

import ivansimeonov.springframework.msscbeerservice.services.BeerService;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerDto;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerPagedList;
import ivansimeonov.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 14.04.22
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    private final BeerService beerService;

    @GetMapping(produces = {"application/json"}, path = "beer")
    public ResponseEntity<BeerPagedList> beersList(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beersList = beerService.beersList(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(beersList, HttpStatus.OK);
    }

    @GetMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
    }

    @GetMapping("beerUpc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc) {
        return new ResponseEntity<>(beerService.getByUpc(upc), HttpStatus.OK);
    }

    @PostMapping("beer")
    public ResponseEntity<BeerDto> addBeer(@Validated @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.addNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) {
        return new ResponseEntity<>(beerService.updateBeerById(beerId, beerDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("beer/{beerId}")
    public ResponseEntity<BeerDto> deleteBeerById(@PathVariable("beerId") UUID beerId) {
        this.beerService.deleteBeerById(beerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
