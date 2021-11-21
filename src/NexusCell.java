public class NexusCell extends Cell implements isAccessible {
    // Holds attributes to create a nexus cell

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
