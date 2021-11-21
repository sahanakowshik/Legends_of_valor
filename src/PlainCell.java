public class PlainCell extends Cell implements isAccessible {
    // Holds attributes to create plain cell

    private String symbol = "       ";
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "Plain";
    }

    @Override
    public String getName() {
        return "Plain";
    }
}
