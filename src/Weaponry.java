import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Weaponry extends MarketItems implements isBuyableSellable, isUsable{
    // Class to create a weapon
    public static List<String> allLines;
    private int id;
    private String Name;
    private int cost;
    private int level;
    private int damage;
    private String equip;
    private int req_hands;

    public static List<String> getAllLines() {
        return allLines;
    }

    // Setters and getters for all the weapon attributes
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getReq_hands() {
        return req_hands;
    }

    public void setReq_hands(int req_hands) {
        this.req_hands = req_hands;
    }

    // Overriding equals method for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weaponry weaponry = (Weaponry) o;
        return id == weaponry.id && cost == weaponry.cost && level == weaponry.level && damage == weaponry.damage && req_hands == weaponry.req_hands && Objects.equals(Name, weaponry.Name) && Objects.equals(equip, weaponry.equip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, cost, level, damage, equip, req_hands);
    }


    public String getType() {
        return "Weaponry";
    }

    @Override
    public void display(){
        // Calls the displayWeaponry method for displaying the list of weapons
        Display.displayWeaponry(weapons);
    }

    @Override
    public void createList(){
        // Creates a list of weapons
        List<String> list = this.getList();
        weapons = new ArrayList<>();
        for(String str: list){
            String[] words = str.split("\\s+");
            Weaponry weapon = new Weaponry();
            weapon.setId(Integer.parseInt(words[0]));
            weapon.setName(words[1]);
            weapon.setCost(Integer.parseInt(words[2]));
            weapon.setLevel(Integer.parseInt(words[3]));
            weapon.setDamage(Integer.parseInt(words[4]));
            weapon.setReq_hands(Integer.parseInt(words[5]));
            weapon.setEquip("No");
            weapons.add(weapon);
        }
    }

    public List<String> getList() {
        // Returns a list of weapons
        allLines = Parser.parser("Weaponry.txt"); // Parses the Weaponry.txt config file
        return this.getItemList(allLines); // Returns the list of items from the parsed config file
    }

    @Override
    public void buy(Market market, Heroes hero, int id) {
        // method to buy the weapon
        Weaponry item = market.getWeaponry().weapons.get(id); // Getting the selected weapon object
        item.setEquip("Yes"); // Equipping the weapon
        hero.getWeapons().add(item); // Adding the item to hero's inventory
        hero.setIsEquipped(true); // Setting isEquipped to true
        hero.setCurWeapon(item); // Setting this item as hero's current weapon
    }

}
