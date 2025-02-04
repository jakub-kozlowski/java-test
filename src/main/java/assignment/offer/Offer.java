package assignment.offer;

import assignment.Item;

import java.math.BigDecimal;
import java.util.List;

public interface Offer {
    boolean doesApply(List<Item> items, int purchaseDaysDelta);
    BigDecimal getDiscount(List<Item> items);
}
