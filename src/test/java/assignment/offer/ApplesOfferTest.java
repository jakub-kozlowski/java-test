package assignment.offer;

import assignment.Item;
import assignment.ItemPricing;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplesOfferTest {
    ApplesOffer unit;
    ItemPricing itemPricing;
    private List<Item> singleApple = Collections.singletonList(Item.APPLE);

    @Before
    public void setup() {
        itemPricing = new ItemPricing();
        unit = new ApplesOffer(itemPricing);
    }

    @Test
    public void appleOffer_appliesOnlyToApples() {
        int purchaseDeltaDays = 10;
        assertTrue( unit.doesApply(singleApple, purchaseDeltaDays) );

        assertTrue( Arrays.stream(Item.values())
                .filter(item -> ! item.equals(Item.APPLE))
                .noneMatch(item -> unit.doesApply(Collections.singletonList(item), purchaseDeltaDays)) );
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

    @Test
    public void appleOffer_doesNotApplyToday() {
        assertFalse( unit.doesApply(singleApple, 0));
    }

    @Test
    public void appleOffer_appliesThreeDaysHence() {
        assertTrue( unit.doesApply(singleApple, 3));
    }

    @Test
    public void appleOffer_doesNotApplyOnFirstDayOfMonthPlusTwo() {
        int daysToMonthPlusTwo = (int)ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().withDayOfMonth(1).plus(2, ChronoUnit.MONTHS));
        assertFalse( unit.doesApply(singleApple, daysToMonthPlusTwo));
    }
}