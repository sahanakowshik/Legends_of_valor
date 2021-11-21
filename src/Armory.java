import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Armory extends MarketItems implements isUsable, isBuyableSellable{
    // Class to create an armory
    public static List<String> allLines;
    private int id;
    private String Name;
    private int cost;
    private int req_level;
    private int damage_reduction;
    private String equip;

    public static List<String> getAllLines() {
        return allLines;
    }
    // Setters and getters for all the armory attributes
    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getReq_level() {
        return req_level;
    }

    public void setReq_level(int req_level) {
        this.req_level = req_level;
    }

    public int getDamage_reduction() {
        return damage_reduction;
    }

    public void setDamage_reduction(int damage_reduction) {
        this.damage_reduction = damage_reduction;
    }

    public String getType() {
        return "Armory";
    }

    // Overriding equals method for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Armory armory = (Armory) o;
        return id == armory.id && cost == armory.cost && req_level == armory.req_level && damage_reduction == armory.damage_reduction && Objects.equals(Name, armory.Name) && Objects.equals(equip, armory.equip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, cost, req_level, damage_reduction, equip);
    }

    @Override
    public void display(){
        // Calls the displayArmory method for displaying the list of armories
        Display.displayArmory(armories);
    }

    @Override
    public void createList() {
        // Creates a list of armories
        List<String> list = this.getList();
        armories = new ArrayList<>();
        for(String str: list){
            String[] words = str.split("\\s+");
            Armory armory = new Armory();
            armory.setId(Integer.parseInt(words[0]));
            armory.setName(words[1]);
            armory.setCost(Integer.parseInt(words[2]));
            armory.setReq_level(Integer.parseInt(words[3]));
            armory.setDamage_reduction(Integer.parseInt(words[4]));
            armory.setEquip("No");
            armories.add(armory);
        }
    }

    public List<String> getList() {
        // Returns the list of armories
        allLines = Parser.parser("Armory.txt"); // Parses the Armory.txt config file
        return this.getItemList(allLines); // Returns the list of items from the parsed config file
    }

    public void buy(Market market, Heroes hero, int id){
        // method to buy the armory
        Armory item = market.getArmory().armories.get(id); // Getting the selected armory object
        item.setEquip("Yes"); // Equipping the armory
        hero.getArmories().add(item); // Adding the item to hero's inventory
        hero.setIsEquipped(true); // Setting isEquipped to true
        hero.setCurArmory(item); // Setting this item as hero's current armory
    }

}
