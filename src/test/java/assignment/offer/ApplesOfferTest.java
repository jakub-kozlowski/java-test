package assignment.offer;

import assignment.Item;
import assignment.ItemPricing;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ApplesOfferTest {
    ApplesOffer unit;
    ItemPricing itemPricing;

    @Before
    public void setup() {
        itemPricing = new ItemPricing();
        unit = new ApplesOffer(itemPricing);
    }

    @Test
    public void appleOffer_appliesOnlyToApples() {
        assertTrue( unit.doesApply(Collections.singletonList(Item.APPLE)) );

        assertTrue( Arrays.stream(Item.values())
                .filter(item -> ! item.equals(Item.APPLE))
                .noneMatch(item -> unit.doesApply(Collections.singletonList(item))) );
    }

    @Test
    public void appleOffer_isAPercentageDiscount() {
        List<Item> basket = Arrays.asList(Item.APPLE, Item.APPLE);
        BigDecimal discount = unit.getDiscount(basket);
        assertThat(discount).isEqualByComparingTo(ApplesOffer.PERCENTAGE_DISCOUNT
                .multiply(itemPricing.getPrice(Item.APPLE)
                        .multiply(new BigDecimal(basket.size())
                        )));
    }
}