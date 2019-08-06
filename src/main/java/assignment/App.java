package assignment;

public class App {
    private ParameterParser parameterParser;

    public App() {
        parameterParser = new ParameterParser();
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
            BasketPricing basketPricing = new BasketPricing(parameterParser.getItems(), new ItemPricing());
            outputPricing(basketPricing);
        } else {
            outputErrorParsingParameters();
        }
    }

    private void outputPricing(BasketPricing basketPricing) {
        System.out.println("Total: " + basketPricing.getTotal());
    }

    private void outputErrorParsingParameters() {
        System.out.println("Following items are unknown: " + String.join(", ", parameterParser.getUnknownItems()));
    }

    private void outputUsage() {
        System.out.println("Usage: price-basket itemName [itemNames...] purchaseTimeDeltaInDays");
    }
}
