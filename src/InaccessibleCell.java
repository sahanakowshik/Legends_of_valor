public class InaccessibleCell extends Cell{
    // Holds attributes to create an inaccessible cell

    private String symbol = "\u001b[31m X X X \u001b[0m";
    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "Inaccessible";
    }

    @Override
    public String getName() {
        return "Inaccessible";
    }
}
