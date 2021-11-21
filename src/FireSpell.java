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
        // Calls the displaySpells method for displaying the list of fire spells
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
        allLines = Parser.parser("FireSpells.txt"); // Parses the FireSpells.txt config file
        return this.getItemList(allLines); // Returns the list of items from the parsed config file
    }

    @Override
    public void buy(Market market, Heroes hero, int id) {
        // method to buy the fire spell
        hero.getSpells().add(market.getFireSpell().fireSpells.get(id));
    }
}
