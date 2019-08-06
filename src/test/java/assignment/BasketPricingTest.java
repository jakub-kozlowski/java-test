package assignment;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketPricingTest {

    BasketPricing unit;
    ItemPricing itemPricing;

    @Before
    public void setup() {
        itemPricing = new ItemPricing();
    }

    @Test
    public void whenNoItems_BasketTotalIsZero() {
        unit = new BasketPricing(Collections.emptyList(), itemPricing, Collections.EMPTY_LIST, 0);

        assertThat(unit.getTotal()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    public void whenItemsInBasket_BasketTotalIsTheItemPriceSum() {
        unit = new BasketPricing(Arrays.asList(Item.APPLE, Item.BREAD, Item.MILK, Item.SOUP), itemPricing, Collections.EMPTY_LIST, 0);

        assertThat(unit.getTotal()).isEqualByComparingTo(new BigDecimal("2.85"));
    }
}