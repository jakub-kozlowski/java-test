package assignment;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ItemTest {
    @Test
    public void everyItemEnumName_isSameAsItsStringName() {
        boolean namesMatch = Arrays.stream(Item.values()).allMatch(item -> item.name().equalsIgnoreCase(item.name));
        Assert.assertTrue(namesMatch);
    }
}