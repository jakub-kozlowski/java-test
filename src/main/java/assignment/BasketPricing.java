package assignment;

import java.math.BigDecimal;
import java.util.List;

public class BasketPricing {
    ItemPricing itemPricing;
    List<Item> items;

    public BasketPricing(List<Item> items, ItemPricing itemPricing) {
        this.items = items;
        this.itemPricing = itemPricing;
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(itemPricing::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
