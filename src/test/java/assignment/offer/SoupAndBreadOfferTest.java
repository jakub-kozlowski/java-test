package assignment.offer;

import assignment.Item;
import assignment.ItemPricing;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class SoupAndBreadOfferTest {

    SoupAndBreadOffer unit;
    List<Item> twoSoupsAndBread;

    @Before
    public void setup() {
        unit = new SoupAndBreadOffer(new ItemPricing());
        twoSoupsAndBread = new ArrayList<>();
        twoSoupsAndBread.add(Item.SOUP);
        twoSoupsAndBread.add(Item.SOUP);
        twoSoupsAndBread.add(Item.BREAD);
    }

    @Test
    public void applies_whenTwoSoupsAndBreadInBasket() {
        List<Item> items = new ArrayList<>();

        items.add(Item.SOUP);
        items.add(Item.BREAD);
        assertFalse( unit.doesApply(items, 0) );

        items.add(Item.SOUP);
        assertTrue( unit.doesApply(items, 0) );
    }

    @Test
    public void whenFourSoupsAndThreeBreads_TwoBreadsGetDiscounted() {
        List<Item> items = Arrays.asList(Item.SOUP, Item.SOUP, Item.SOUP, Item.SOUP, Item.BREAD, Item.BREAD, Item.BREAD);

        ItemPricing itemPricing = new ItemPricing();
        BigDecimal halfBreadPrice = itemPricing.getPrice(Item.BREAD).divide(new BigDecimal(2));
        assertThat(unit.getDiscount(items)).isEqualByComparingTo(halfBreadPrice.add(halfBreadPrice));
    }

    @Test
    public void breadOffer_notValidTwoDaysAgo() {
        assertFalse( unit.doesApply(twoSoupsAndBread, -2));
    }

    @Test
    public void breadOffer_validSixDaysFromToday() {
        assertTrue( unit.doesApply(twoSoupsAndBread, 6));
    }
}