import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IceSpell extends Spell{
    // Class to create a list of ice spells

    public static List<String> getAllLines() {
        return allLines;
    }

    public String getType() {
        return "Ice Spell";
    }

    @Override
    public void display(){
        Display.displaySpells(iceSpells);
    }

    @Override
    public void createList() {
        // Creates a list of ice spells
        List<String> list = this.getList();
        iceSpells = new ArrayList<>();
        for(String str: list){
            String[] words = str.split("\\s+");
            Spell spell = new IceSpell();
            this.createSpell(spell, words);
            iceSpells.add(spell);
        }
    }

    public List<String> getList() {
        // Returns a list of ice spells
        allLines = Parser.parser("IceSpells.txt");
        List<String> list = new ArrayList<>();
        for (int i=1;i<allLines.size();i++) {
            String str = i + "   " + allLines.get(i);
            list.add(str);
        }
        return list;
    }

    @Override
    public void buy(Market market, Heroes hero, int id) {
        hero.getSpells().add(market.getIceSpell().iceSpells.get(id));
    }
}
