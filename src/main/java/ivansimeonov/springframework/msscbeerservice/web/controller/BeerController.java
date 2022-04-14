package ivansimeonov.springframework.msscbeerservice.web.controller;

import ivansimeonov.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 14.04.22
 */

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        // TODO: 14.04.22 Implement Method
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> addBeer(@RequestBody BeerDto beerDto) {
        // TODO: 14.04.22 Implement Method
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
        // TODO: 14.04.22 Implement Method
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<BeerDto> deleteBeerById(@PathVariable("beerId") UUID beerId) {
        // TODO: 14.04.22 Implement Method
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
