package assignment.offer;

import assignment.Item;
import assignment.ItemPricing;

import java.math.BigDecimal;
import java.util.List;

public class SoupAndBreadOffer implements Offer {
    static final Item qualifyingItem = Item.SOUP;
    static final Item discountedItem = Item.BREAD;

    private BigDecimal discountPerItem;

    public SoupAndBreadOffer(ItemPricing itemPricing) {
        discountPerItem = itemPricing.getPrice(discountedItem).divide(new BigDecimal(2), BigDecimal.ROUND_FLOOR);
    }

    @Override
    public boolean doesApply(List<Item> items) {
        return items.contains(discountedItem)
                && items.stream()
                .filter(qualifyingItem::equals)
                .count() >= 2;
    }

    @Override
    public BigDecimal getDiscount(List<Item> items) {
        final long discountedItemCount = items.stream().filter(discountedItem::equals).count();
        final long qualifiedForDiscountCount = items.stream().filter(qualifyingItem::equals).count() / 2;
        return new BigDecimal(Math.min(discountedItemCount, qualifiedForDiscountCount)).multiply(discountPerItem);
    }
}
