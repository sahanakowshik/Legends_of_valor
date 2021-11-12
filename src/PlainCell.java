public class PlainCell extends Cell implements isAccessible {
    // Holds attributes to create a safe/common cell

    private String symbol = "  P  ";
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
