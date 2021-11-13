public class KoulouCell extends Cell implements isAccessible{
//    private String symbol = "\u001b[34m  K  \u001b[0m";
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
        return "KOULOU";
    }
}
