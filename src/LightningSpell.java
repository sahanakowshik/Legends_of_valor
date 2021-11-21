import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LightningSpell extends Spell{
    // Class to create a list of lightning spells

    public static List<String> getAllLines() {
        return allLines;
    }

    public String getType() {
        return "Lightning Spell";
    }

    @Override
    public void display(){
        // Calls the displaySpells method for displaying the list of lightning spells
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
        allLines = Parser.parser("LightningSpells.txt"); // Parses the LightningSpells.txt config file
        return this.getItemList(allLines); // Returns the list of items from the parsed config file
    }

    @Override
    public void buy(Market market, Heroes hero, int id) {
        // method to buy the lightning spell
        hero.getSpells().add(market.getLightningSpell().lightningSpells.get(id));
    }
}
