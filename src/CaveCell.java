public class CaveCell extends Cell implements isAccessible{
    // Holds attributes to create a cave cell

    private String symbol = "       ";
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "Cave";
    }

    @Override
    public String getName() {
        return "Cave";
    }
}
