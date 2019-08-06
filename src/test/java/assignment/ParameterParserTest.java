package assignment;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParameterParserTest {

    ParameterParser unit;

    @Before
    public void setup() {
        unit = new ParameterParser();
    }

    @Test
    public void whenNoArguments_parsingFails() {
        unit.parse(new String[] {});
        assertTrue(unit.isParsingFailed());
    }

    @Test
    public void lastArgument_isPurchaseDaysDelta() {
        unit.parse("Apple", "10");
        assertFalse(unit.isParsingFailed());
        assertThat(unit.getItems()).isEqualTo(Arrays.asList(Item.APPLE));
        assertThat(unit.getPurchasedDaysDeltaFromToday()).isEqualTo(10);
    }

    @Test
    public void whenLastArgumentIsNotInteger_parsingFails() {
        unit.parse("2.0");
        assertTrue(unit.isParsingFailed());
    }

    @Test
    public void parserTranslatesStringParametersToItems() {
        unit.parse("Apple", "Milk", "0");
        assertFalse(unit.isParsingFailed());
        assertThat(unit.getItems()).isEqualTo(Arrays.asList(Item.APPLE, Item.MILK));
    }

    @Test
    public void whenUnknownItem_hasUnknownItems_returnsTrue() {
        String unknownItem = "Bananas";
        assertFalse(Arrays.stream(Item.values()).anyMatch(item -> item.name().equalsIgnoreCase(unknownItem)));

        unit.parse(unknownItem, "0");
        assertFalse(unit.isParsingFailed());
        assertTrue(unit.hasUnknownItems());
    }
}