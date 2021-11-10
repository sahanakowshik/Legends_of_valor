import java.util.*;

public class LegendsPlayer extends Player{
    // Class to create an instance of a player
    private int nHero;
    private String symbol;
    private List<Heroes> heroes;

    public List<Heroes> getHeroes() {
        return heroes;
    }

    public void setHeroes() {
        this.heroes = new ArrayList<>();
    }

    public String getSymbol() {
        return symbol;
    }

    public void addHeroes(){
        // Adds heroes to a player
        for(int i=0;i<this.getnHero();i++){
            int choice = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the hero class:\n1. Warrior\n2. Sorceror\n3. Paladin\n", 1, 3);
            if(choice == 1) {
                heroes.add(new Warrior());
            }
            else if(choice == 2) {
                heroes.add(new Sorcerer());
            }
            else {
                heroes.add(new Paladin());
            }
            System.out.println(heroes.get(i).getType() + " List:\n");
            heroes.get(i).displayList();
            int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of the hero\n", 1, heroes.get(i).getN());
            String[] str = heroes.get(i).getAllLines().get(id).split("\\s+");
            heroes.get(i).setPlayerId(id);
            heroes.get(i).setName(str[0]);
            heroes.get(i).setMana(Integer.parseInt(str[1]));
            heroes.get(i).setStrength(Integer.parseInt(str[2]));
            heroes.get(i).setAgility(Integer.parseInt(str[3]));
            heroes.get(i).setDexterity(Integer.parseInt(str[4]));
            heroes.get(i).setStarting_money(Integer.parseInt(str[5]));
            heroes.get(i).setStarting_exp(Integer.parseInt(str[6]));
            heroes.get(i).setDefense(0);
            heroes.get(i).setHp(100);
            heroes.get(i).setLevel(1);
        }

    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getnHero() {
        return nHero;
    }

    public void setnHero(int nHero) {
        this.nHero = nHero;
    }

}
