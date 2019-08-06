package assignment;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    public void parserTranslatesStringParametersToItems() {
        List<Item> items = unit.parse(null);
        assertThat(items).isEmpty();

        items = unit.parse("Apple", "Milk");
        assertThat(items).isEqualTo(Arrays.asList(Item.APPLE, Item.MILK));
    }

    @Test
    public void whenUnknownItem_hasUnknownItems_returnsTrue() {
        String unknownItem = "Bananas";
        assertFalse(Arrays.stream(Item.values()).anyMatch(item -> item.name().equalsIgnoreCase(unknownItem)));

        unit.parse(unknownItem);
        assertTrue(unit.hasUnknownItems());
    }
}