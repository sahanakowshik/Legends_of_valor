import java.util.*;

public abstract class Monsters extends ValorPlayer{
    // Class to create monsters
    private String name;
    private int level;
    private int damage;
    private int defense;
    private int dodge_chance;
    private int hp;
    private int i; // Holds current row position
    private int j; // Holds current column position

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

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
        System.out.format("%d   %15s    %10d     %d    %d     %d     %d%n", i+1, this.getName(), this.getLevel(), this.getHp(), this.getDamage(), this.getDefense(), this.getDodge_chance());
    }
    public abstract int getN();

    public abstract String getType();

    // Check the isHeroSet flag for all the cells nearby
    public boolean isHeroNearby(Board board) {
        if (board.getGrid()[i][j].getIsHeroSet() || board.getGrid()[i+1][j].getIsHeroSet()) {
            return true;
        }

        if (board.getGrid()[i][j-1].getIsHeroSet() || board.getGrid()[i+1][j-1].getIsHeroSet()) {
            return true;
        }

        return false;
    }

    // Return all the heroes in adjacent cells, in a list
    public List<Heroes> getNearByHeroes(Board board) {
        if (this.isHeroNearby(board)) { // get all near heroes if there are
            List<Heroes> nearHeroes = new ArrayList<>();
            if (board.getGrid()[i][j].getIsHeroSet()) {
                nearHeroes.add(board.getGrid()[i][j].getHero());
            } else if (board.getGrid()[i+1][j].getIsHeroSet()) {
                nearHeroes.add(board.getGrid()[i+1][j].getHero());
            } else if (board.getGrid()[i][j-1].getIsHeroSet()) {
                nearHeroes.add(board.getGrid()[i][j-1].getHero());
            } else if (board.getGrid()[i+1][j-1].getIsHeroSet()) {
                nearHeroes.add(board.getGrid()[i+1][j-1].getHero());
            }
            return nearHeroes;
        }
        return null;
    }

    public void attackHero(Heroes hero) {
        // Attacks the hero
        int monDmg = (int) (this.getDamage() * 0.05); // Calculating damage dealt by monster
        if (GameFunctions.getRandomBoolean((float) (hero.getAgility() * 0.001))) { // Checking if hero dodged the attack
            System.out.println("\u001B[32m " + hero.getName() + " have dodged the attack! \u001b[0m");
        } else {
            if(hero.getArmories().size() == 0)
                hero.setHp(Math.max((hero.getHp() - monDmg),0)); // Updating hp of hero without armory
            else
                hero.setHp(Math.max(Math.min((hero.getHp() + hero.getCurArmory().getDamage_reduction() - monDmg), hero.getHp()),0)); // Updating hp of hero without armory
            System.out.println("\u001B[31m " + name + " has dealt " + monDmg + " damage to " + hero.getName() + "! \u001b[0m");
            Parser.parseMusic("mixkit-quick-ninja-strike-2146.wav");
            if (hero.getHp() <= 0) {
                // Checking if monster won the round
                System.out.println("\u001B[31m Monster won! \u001b[0m");
                Parser.parseMusic("mixkit-8-bit-lose-2031.wav");
            }
        }
    }

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

    public static List<String> getItemList(List<String> allLines){
        // returns a list of string items
        List<String> list = new ArrayList<>();
        for (int i=1;i<allLines.size();i++) {
            String str = i + "   " + allLines.get(i);
            list.add(str);
        }
        return list;
    }
}
