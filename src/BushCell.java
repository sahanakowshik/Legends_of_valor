public class BushCell extends Cell implements isAccessible{
    private String symbol = "\u001b[34m  M  \u001b[0m";
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
        return "BUSH";
    }
}
