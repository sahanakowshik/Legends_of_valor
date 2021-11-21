public class BushCell extends Cell implements isAccessible{
    // Holds attributes to create a Bush cell

    private String symbol = "       ";
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "Bush";
    }

    @Override
    public String getName() {
        return "Bush";
    }
}
