public class CaveCell extends Cell implements isAccessible{
    private String symbol = "\u001b[34m  M  \u001b[0m";
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
        return "CAVE";
    }
}
