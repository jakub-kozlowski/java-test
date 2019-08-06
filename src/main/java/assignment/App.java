package assignment;

import java.util.List;

public class App {
    private ParameterParser parameterParser;
    private List<Item> items;

    public App() {
        parameterParser = new ParameterParser();
    }

    public static void main( String[] args )
    {
        new App().run(args);
    }

    void run(String[] args) {
        items = parameterParser.parse(args);

        if( ! parameterParser.hasUnknownItems() ) {
            BasketPricing basketPricing = new BasketPricing(items, new ItemPricing());
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
}
