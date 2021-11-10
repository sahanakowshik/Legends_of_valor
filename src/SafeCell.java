public class SafeCell extends Cell implements isAccessible {
    // Holds attributes to create a safe/common cell

    private String symbol = "     ";
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "Safe";
    }

    @Override
    public String getName() {
        return "Safe";
    }
}
