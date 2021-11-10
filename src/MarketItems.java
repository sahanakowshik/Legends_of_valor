import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class MarketItems{
    // An abstract class to create market items
    protected List<Armory> armories;
    protected List<Weaponry> weapons;
    protected List<Potions> potions;
    protected List<Spell> fireSpells;
    protected List<Spell> iceSpells;
    protected List<Spell> lightningSpells;

    public abstract void display();

    public abstract void createList();
}
