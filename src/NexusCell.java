public class NexusCell extends Cell implements isAccessible {
    // Holds attributes to create a market cell

//    private String symbol = "\u001b[34m  N  \u001b[0m";
    private String symbol = "       ";
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "Nexus";
    }

    @Override
    public String getName() {
        return "Nexus";
    }
}