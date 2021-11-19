import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FireSpell extends Spell{
    // Class to create a list of fire spells

    public static List<String> getAllLines() {
        return allLines;
    }

    public String getType() {
        return "Fire Spell";
    }

    @Override
    public void display(){
        Display.displaySpells(fireSpells);
    }

    @Override
    public void createList() {
        // Creates a list of fire spells
        List<String> list = this.getList();
        fireSpells = new ArrayList<>();
        for(String str: list){
            String[] words = str.split("\\s+");
            Spell spell = new FireSpell();
            this.createSpell(spell, words);
            fireSpells.add(spell);
        }
    }

    public List<String> getList() {
        // Returns a list of fire spells
        allLines = Parser.parser("FireSpells.txt");
        List<String> list = new ArrayList<>();
        for (int i=1;i<allLines.size();i++) {
            String str = i + "   " + allLines.get(i);
            list.add(str);
        }
        return list;
    }

    @Override
    public void buy(Market market, Heroes hero, int id) {
        hero.getSpells().add(market.getFireSpell().fireSpells.get(id));
    }
}
