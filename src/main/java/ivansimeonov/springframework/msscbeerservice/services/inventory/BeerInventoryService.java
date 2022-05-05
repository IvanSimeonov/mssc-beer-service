package ivansimeonov.springframework.msscbeerservice.services.inventory;

import java.util.UUID;

/**
 * @Author ivansimeonov
 * @Date 5.05.22
 */
public interface BeerInventoryService {
    Integer getOnHandInventory(UUID beerId);
}
