package assignment;

import assignment.offer.Offer;

import java.math.BigDecimal;
import java.util.List;

public class BasketPricing {
    private ItemPricing itemPricing;
    private List<Item> items;
    private List<Offer> offers;
    private int purchasedDaysDeltaFromToday;

    public BasketPricing(List<Item> items, ItemPricing itemPricing, List<Offer> offers, int purchasedDaysDeltaFromToday) {
        this.items = items;
        this.itemPricing = itemPricing;
        this.offers = offers;
        this.purchasedDaysDeltaFromToday = purchasedDaysDeltaFromToday;
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(itemPricing::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(getOffersDiscount());
    }

    private BigDecimal getOffersDiscount() {
        return offers.stream()
                .filter(offer -> offer.doesApply(items, purchasedDaysDeltaFromToday))
                .map(offer -> offer.getDiscount(items))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
