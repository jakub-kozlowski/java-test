package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterParser {

    List<String> unknownItems;

    public ParameterParser() {
        unknownItems = new ArrayList<>(0);
    }

    public List<Item> parse(String... args) {
        if(args == null)
            return Collections.emptyList();

        final List<Item> items = new ArrayList<>(args.length);
        for(String arg : args) {
            try {
                items.add(Item.valueOf(arg.toUpperCase()));
            } catch( IllegalArgumentException e ) {
                unknownItems.add(arg);
            }
        }
        return items;
    }

    public boolean hasUnknownItems() {
        return ! unknownItems.isEmpty();
    }

    public List<String> getUnknownItems() {
        return unknownItems;
    }
}
