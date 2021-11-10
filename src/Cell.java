public abstract class Cell{
    // Class to hold attributes of a single cell in the map
    private boolean isSet;
    private LegendsPlayer player;

    public boolean getIsSet() {
        return isSet;
    }

    public void setIsSet(boolean set) {
        isSet = set;
    }

    public LegendsPlayer getPlayer() {
        return player;
    }

    public void setPlayer(LegendsPlayer player) {
        this.player = player;
    }

    public abstract String getSymbol();

    public abstract String toString();

    public abstract String getName();
}
