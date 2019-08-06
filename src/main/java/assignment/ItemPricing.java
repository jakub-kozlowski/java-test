package assignment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ItemPricing {

    Map<Item, BigDecimal> prices = new HashMap<Item, BigDecimal>() {{
        put(Item.APPLE, new BigDecimal("0.10"));
        put(Item.BREAD, new BigDecimal("0.80"));
        put(Item.MILK, new BigDecimal("1.30"));
        put(Item.SOUP, new BigDecimal("0.65"));
    }};

    public BigDecimal getPrice(Item item) {
        final BigDecimal price = prices.get(item);
        if( price == null )
            throw new IllegalStateException("Price not found for item: " + item);
        return price;
    }
}
