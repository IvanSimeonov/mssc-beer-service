package ivansimeonov.springframework.msscbeerservice.bootstrap;

import ivansimeonov.springframework.msscbeerservice.domain.Beer;
import ivansimeonov.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author ivansimeonov
 * @Date 14.04.22
 */
@Slf4j
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            log.info("Loading DUMMY Beer Data!!!!");
            beerRepository.save(Beer.builder()
                    .beerName("Heineken")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(234)
                    .minOnHand(13)
                    .upc(3330000001L)
                    .price(new BigDecimal("13.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Zagorka")
                    .beerStyle("IPA")
                    .quantityToBrew(150)
                    .minOnHand(7)
                    .upc(3330000002L)
                    .price(new BigDecimal("11.99"))
                    .build());
        }
        log.info(String.format("Total Beer Entries: %d", beerRepository.count()));
    }
}
