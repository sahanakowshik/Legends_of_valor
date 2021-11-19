import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LightningSpell extends Spell{
//    public static List<String> allLines;

    public static List<String> getAllLines() {
        return allLines;
    }

    public String getType() {
        return "Lightning Spell";
    }

    @Override
    public void display(){
        Display.displaySpells(lightningSpells);
    }

    @Override
    public void createList() {
        // Creates a list of lightning spells
        List<String> list = this.getList();
        lightningSpells = new ArrayList<>();
        for(String str: list){
            String[] words = str.split("\\s+");
            Spell spell = new LightningSpell();
            this.createSpell(spell, words);
            lightningSpells.add(spell);
        }
    }

    public List<String> getList() {
        // Returns a list of lightning spells
        allLines = Parser.parser("LightningSpells.txt");
        List<String> list = new ArrayList<>();
        for (int i=1;i<allLines.size();i++) {
            String str = i + "   " + allLines.get(i);
            list.add(str);
        }
        return list;
    }

    @Override
    public void buy(Market market, Heroes hero, int id) {
        hero.getSpells().add(market.getLightningSpell().lightningSpells.get(id));
    }
}
