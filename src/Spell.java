import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Spell extends MarketItems implements isCastable, isBuyableSellable{
    // Class to create a spell
    private int id;
    private String Name;
    private int cost;
    private int req_level;
    private int damage;
    private int mana_cost;
    public static List<String> allLines;

    // Overriding equals method for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spell spell = (Spell) o;
        return id == spell.id && cost == spell.cost && req_level == spell.req_level && damage == spell.damage && mana_cost == spell.mana_cost && Objects.equals(Name, spell.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, cost, req_level, damage, mana_cost);
    }

    // // Getters and setters for all the spell attributes
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMana_cost() {
        return mana_cost;
    }

    public void setMana_cost(int mana_cost) {
        this.mana_cost = mana_cost;
    }

    public void createSpell(Spell spell, String[] words){
        // Creates a spell
        spell.setId(Integer.parseInt(words[0]));
        spell.setName(words[1]);
        spell.setCost(Integer.parseInt(words[2]));
        spell.setReq_level(Integer.parseInt(words[3]));
        spell.setDamage(Integer.parseInt(words[4]));
        spell.setMana_cost(Integer.parseInt(words[5]));
    }

    public abstract String getType();

}
