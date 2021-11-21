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

    public List<String> getItemList(List<String> allLines){
        // creates a list of items from allLines (allLines contains strings from parsed config files)
        List<String> list = new ArrayList<>();
        for (int i=1;i<allLines.size();i++) {
            String str = i + "   " + allLines.get(i);
            list.add(str);
        }
        return list;
    }

    public abstract void display();

    public abstract void createList();
}
