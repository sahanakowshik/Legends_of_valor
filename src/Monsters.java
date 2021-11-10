import java.util.*;

public abstract class Monsters extends LegendsPlayer{
    // Class to create monsters
    private String name;
    private int level;
    private int damage;
    private int defense;
    private int dodge_chance;
    private int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

//    public Map<Integer,List<String>> monsters=new HashMap<Integer,List<String>>();
    // Combine monsters level wise and make list
    // add method to get n monsters at random

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodge_chance() {
        return dodge_chance;
    }

    public void setDodge_chance(int dodge_chance) {
        this.dodge_chance = dodge_chance;
    }

    public void displayMonster(int i){
        System.out.format("%d   %15s    %10d     %d    %d     %d%n", i+1, this.getName(), this.getLevel(), this.getHp(), this.getDamage(), this.getDefense(), this.getDodge_chance());
    }
    public abstract int getN();

    public abstract String getType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monsters monsters = (Monsters) o;
        return level == monsters.level && damage == monsters.damage && defense == monsters.defense && dodge_chance == monsters.dodge_chance && hp == monsters.hp && Objects.equals(name, monsters.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, damage, defense, dodge_chance, hp);
    }
}
