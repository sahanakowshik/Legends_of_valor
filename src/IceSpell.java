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
        // Calls the displaySpells method for displaying the list of ice spells
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
        allLines = Parser.parser("IceSpells.txt"); // Parses the IceSpells.txt config file
        return this.getItemList(allLines); // Returns the list of items from the parsed config file
    }

    @Override
    public void buy(Market market, Heroes hero, int id) {
        // method to buy the ice spell
        hero.getSpells().add(market.getIceSpell().iceSpells.get(id));
    }
}
