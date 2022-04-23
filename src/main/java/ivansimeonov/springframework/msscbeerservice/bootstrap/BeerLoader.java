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

    public static final String BEER_1_UPC = "066634523456";
    public static final String BEER_2_UPC = "066644552211";
    public static final String BEER_3_UPC = "088822211134";

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
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("13.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Zagorka")
                    .beerStyle("IPA")
                    .quantityToBrew(150)
                    .minOnHand(7)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Pirinsko")
                    .beerStyle("ALE")
                    .quantityToBrew(120)
                    .minOnHand(3)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("12.99"))
                    .build());
        }
        log.info(String.format("Total Beer Entries: %d", beerRepository.count()));
    }
}
