public class MarketCell extends Cell implements isAccessible {
    // Holds attributes to create a market cell

    private String symbol = "\u001b[34m  M  \u001b[0m";
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "Market";
    }

    @Override
    public String getName() {
        return "Market";
    }
}
