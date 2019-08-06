package assignment.offer;

import assignment.Item;
import assignment.ItemPricing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

public class ApplesOffer extends TimeLimitedOffer {
    static final BigDecimal PERCENTAGE_DISCOUNT = new BigDecimal("0.10");
    static final Item discountedItem = Item.APPLE;
    private BigDecimal discountPerItem;

    public ApplesOffer(ItemPricing itemPricing) {
        this.discountPerItem = itemPricing.getPrice(discountedItem).multiply(PERCENTAGE_DISCOUNT);
    }

    @Override
    protected LocalDate initTimeFrom() {
        return LocalDate.now().plusDays(3);
    }

    @Override
    protected LocalDate initTimeTo() {
        return LocalDate.now().withDayOfMonth(1).plus(2, ChronoUnit.MONTHS).minusDays(1);
    }

    @Override
    protected boolean doesApplyForItems(List<Item> items) {
        return items.contains(discountedItem);
    }

    @Override
    public BigDecimal getDiscount(List<Item> items) {
        return discountPerItem.multiply(new BigDecimal(items.stream().filter(discountedItem::equals).count()));
    }
}
