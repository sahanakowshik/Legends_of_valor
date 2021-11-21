public class KoulouCell extends Cell implements isAccessible{
    // Holds attributes to create a Koulou cell

    private String symbol = "       ";
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "Koulou";
    }

    @Override
    public String getName() {
        return "Koulou";
    }
}
