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
            Spell spell = new FireSpell();
            spell.setId(Integer.parseInt(words[0]));
            spell.setName(words[1]);
            spell.setCost(Integer.parseInt(words[2]));
            spell.setReq_level(Integer.parseInt(words[3]));
            spell.setDamage(Integer.parseInt(words[4]));
            spell.setMana_cost(Integer.parseInt(words[5]));
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
}
