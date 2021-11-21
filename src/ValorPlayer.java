import java.util.*;

public class ValorPlayer extends Player{
    // Class to create an instance of a player
    private int nHero;
    private int nMonster;
    private String symbol;
    private List<Heroes> heroes;
    private List<Monsters> curMonsters;
    public Map<Integer,ArrayList<Monsters>> monsters=new HashMap<Integer,ArrayList<Monsters>>();

    public List<Monsters> getCurMonsters() {
        // Returns a list of monsters
        return curMonsters;
    }

    public void createMonster(List<String> list, String type){
        // Creates monsters array and stores it grouped by their level
        Monsters monster;
        for(String str: list){
            String[] words = str.split("\\s+");
            if(Objects.equals(type, "Dragon"))
                monster = new Dragon();
            else if(Objects.equals(type, "Exoskeleton"))
                monster = new Exoskeleton();
            else
                monster = new Spirit();
            monster.setPlayerId(Integer.parseInt(words[0]));
            monster.setName(words[1]);
            monster.setLevel(Integer.parseInt(words[2]));
            monster.setHp(monster.getLevel() * 100);
            monster.setDamage(Integer.parseInt(words[3]));
            monster.setDefense(Integer.parseInt(words[4]));
            monster.setDodge_chance(Integer.parseInt(words[5]));
            monsters.get(Integer.parseInt(words[2])).add(monster);
        }
    }

    public void createMonsters(){
        // Maps all monsters with their level and creates a list
        for(int i=1;i<=10;i++){
            monsters.put(i, new ArrayList<Monsters>());
        }
        List<String> ld = Dragon.getList();
        createMonster(ld, "Dragon");
        List<String> le = Exoskeleton.getList();
        createMonster(le, "Exoskeleton");
        List<String> ls = Spirit.getList();
        createMonster(ls, "Spirit");
    }

    public Monsters getMonster(Heroes hero){
        // returns a monster of the hero's level
        Random rand = new Random();
        Monsters monster = monsters.get(hero.getLevel()).get(rand.nextInt(monsters.get(hero.getLevel()).size()));
        return monster;
    }

    public void getMonsters(){
        // Returns random monsters of same level as the hero from the list of monsters
        curMonsters = new ArrayList<>();
        for(Heroes hero: this.getHeroes()){
            while (true) { // Avoiding repetition of monsters
                Monsters monster = getMonster(hero);
                if (curMonsters.contains(monster)) {
                    continue;
                }
                else {
                    curMonsters.add(monster);
                    break;
                }
            }
        }
    }

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

    public int getnMonster() {
        return nMonster;
    }

    public void setnMonster(int nMonster) {
        this.nMonster = nMonster;
    }

}
