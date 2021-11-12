public abstract class Cell{
    // Class to hold attributes of a single cell in the map
    private boolean isHeroSet;
    private boolean isMonsterSet;
    private Heroes hero;
    private Monsters monster;

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

//    protected String cell[][];

//    public boolean getIsSet() {
//        return isSet;
//    }
//
//    public void setIsSet(boolean set) {
//        isSet = set;
//    }

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
