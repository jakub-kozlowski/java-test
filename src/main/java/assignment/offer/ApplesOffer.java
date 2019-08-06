package assignment.offer;

import assignment.Item;
import assignment.ItemPricing;

import java.math.BigDecimal;
import java.util.List;

public class ApplesOffer implements Offer {
    static final BigDecimal PERCENTAGE_DISCOUNT = new BigDecimal("0.10");
    static final Item discountedItem = Item.APPLE;
    private BigDecimal discountPerItem;

    public ApplesOffer(ItemPricing itemPricing) {
        this.discountPerItem = itemPricing.getPrice(discountedItem).multiply(PERCENTAGE_DISCOUNT);
    }

    @Override
    public boolean doesApply(List<Item> items) {
        return items.contains(discountedItem);
    }

    @Override
    public BigDecimal getDiscount(List<Item> items) {
        return discountPerItem.multiply(new BigDecimal(items.stream().filter(discountedItem::equals).count()));
    }
}
