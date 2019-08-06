package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterParser {

    private List<Item> items;
    private List<String> unknownItems;
    private int purchasedDaysDeltaFromToday;
    private boolean parsingFailed;

    public ParameterParser() {
        items = new ArrayList<>();
        unknownItems = new ArrayList<>(0);
    }

    public void parse(String... args) {
        if(args == null || args.length < 1) {
            parsingFailed = true;
        } else {
            parseItems(args);
            parseDaysDelta(args);
        }
    }

    private void parseDaysDelta(String[] args) {
        try {
            purchasedDaysDeltaFromToday = Integer.valueOf(args[args.length-1]);
        }
        catch (NumberFormatException e) {
            parsingFailed = true;
        }
    }

    private void parseItems(String[] args) {
        for (int i=0; i < args.length - 1; i++) {
            try {
                items.add(Item.valueOf(args[i].toUpperCase()));
            } catch (IllegalArgumentException e) {
                unknownItems.add(args[i]);
            }
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public int getPurchasedDaysDeltaFromToday() {
        return purchasedDaysDeltaFromToday;
    }

    public boolean isParsingFailed() {
        return parsingFailed;
    }

    public boolean hasUnknownItems() {
        return ! unknownItems.isEmpty();
    }

    public List<String> getUnknownItems() {
        return unknownItems;
    }
}
