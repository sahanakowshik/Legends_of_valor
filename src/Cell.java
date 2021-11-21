public abstract class Cell{
    // Class to hold attributes of a single cell in the map
    private boolean isHeroSet; // a flag to check if a hero is present in a cell
    private boolean isMonsterSet; // a flag to check if a monster is present in a cell
    private Heroes hero; // hero present in the current cell
    private Monsters monster; // monster present in the current cell

    public boolean getIsHeroSet() {
        return isHeroSet;
    }

    public void setHeroSet(boolean heroSet) {
        isHeroSet = heroSet;
    }

    public boolean getIsMonsterSet() {
        return isMonsterSet;
    }

    public void setMonsterSet(boolean monsterSet) {
        isMonsterSet = monsterSet;
    }

    public Monsters getMonster() {
        return monster;
    }

    public void setMonster(Monsters monster) {
        this.monster = monster;
    }

    public Heroes getHero() {
        return hero;
    }

    public void setHero(Heroes hero) {
        this.hero = hero;
    }

    public abstract String getSymbol();

    public abstract String toString();

    public abstract String getName();
}
