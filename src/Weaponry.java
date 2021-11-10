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

    //    @Override
    public String getType() {
        return "Weaponry";
    }

    @Override
    public void display(){
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
        allLines = Parser.parser("Weaponry.txt");
        List<String> list = new ArrayList<>();
        for (int i=1;i<allLines.size();i++) {
            String str = i + "   " + allLines.get(i);
            list.add(str);
        }
        return list;
    }
}
