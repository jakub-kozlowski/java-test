package assignment;

import assignment.offer.ApplesOffer;
import assignment.offer.Offer;
import assignment.offer.SoupAndBreadOffer;

import java.util.Arrays;
import java.util.List;

public class App {
    private ParameterParser parameterParser;
    private List<Offer> offers;
    private ItemPricing itemPricing;

    public App() {
        parameterParser = new ParameterParser();
        itemPricing = new ItemPricing();
        offers = Arrays.asList(new ApplesOffer(itemPricing), new SoupAndBreadOffer(itemPricing));
    }

    public static void main( String[] args )
    {
        new App().run(args);
    }

    void run(String[] args) {
        parameterParser.parse(args);
        if (parameterParser.isParsingFailed()) {
            outputUsage();
        } else if (!parameterParser.hasUnknownItems()) {
            BasketPricing basketPricing = new BasketPricing(
                    parameterParser.getItems(),
                    itemPricing,
                    offers,
                    parameterParser.getPurchasedDaysDeltaFromToday());
            outputPricing(basketPricing);
        } else {
            outputErrorParsingParameters();
        }
    }

    private void outputPricing(BasketPricing basketPricing) {
        System.out.println("Total: " + basketPricing.getTotal().setScale(2));
    }

    private void outputErrorParsingParameters() {
        System.out.println("Following items are unknown: " + String.join(", ", parameterParser.getUnknownItems()));
    }

    private void outputUsage() {
        System.out.println("Usage: price-basket itemName [itemNames...] purchaseTimeDeltaInDays");
    }
}
