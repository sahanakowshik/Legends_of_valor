import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class Heroes extends ValorPlayer{
    // Class to hold attributes of a hero
    private String name;
    private int mana;
    private int strength;
    private int agility;
    private int dexterity;
    private int starting_money;
    private int starting_exp;
    private int level;
    private int hp;
    private int defense;
    private Weaponry curWeapon;
    private Armory curArmory;
    private List<Weaponry> weapons; // Holds weapons bought by the hero
    private List<Armory> armories; // Holds armories bought by the hero
    private List<Potions> potions; // Holds potions bought by the hero
    private List<Spell> spells; // Holds spells bought by the hero
    private boolean isEquipped; // to check if the hero is equipped
    private int exp; // to check if the hero levels up
    private int i; // Holds current row position
    private int j; // Holds current column position

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private String symbol;

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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Weaponry getCurWeapon() {
        return curWeapon;
    }

    public void setCurWeapon(Weaponry curWeapon) {
        this.curWeapon = curWeapon;
    }

    public Armory getCurArmory() {
        return curArmory;
    }

    public void setCurArmory(Armory curArmory) {
        this.curArmory = curArmory;
    }

    public boolean getIsEquipped() {
        return isEquipped;
    }

    public void setIsEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

    public Heroes(){
        weapons = new ArrayList<>();
        armories = new ArrayList<>();
        potions = new ArrayList<>();
        spells = new ArrayList<>();
        isEquipped = false;
        exp = 0;
    }

    // Get the isMonsterSet flag to figure out whether there is a monster in the adjacent cell
    public boolean isMonsterNearby(Board board) {
        if (board.getGrid()[i][j].getIsMonsterSet() || board.getGrid()[i-1][j].getIsMonsterSet()) {
            return true;
        }

        if (board.getGrid()[i][(int) (j - Math.pow(-1, (j+1)%3))].getIsMonsterSet() || board.getGrid()[i-1][(int) (j - Math.pow(-1, (j+1)%3))].getIsMonsterSet()) {
            return true;
        }

        return false;
    }

    // Will return all the monsters nearby. In a List
    public List<Monsters> getNearByMonsters(Board board) {
        if (this.isMonsterNearby(board)) { // get all near heroes if there are
            List<Monsters> nearMonsters = new ArrayList<>();
            try {
                if (board.getGrid()[i][j].getIsMonsterSet()) {
                    nearMonsters.add(board.getGrid()[i][j].getMonster());
                }
            } catch (Exception ignored) {

            }
            try {
                if (board.getGrid()[i - 1][j].getIsMonsterSet()) {
                    nearMonsters.add(board.getGrid()[i - 1][j].getMonster());
                }
            } catch (Exception ignored) {

            }
            try {
                if (board.getGrid()[i][j - 1].getIsMonsterSet()) {
                    nearMonsters.add(board.getGrid()[i][j - 1].getMonster());
                }
            } catch (Exception ignored) {

            }
            try {
                if (board.getGrid()[i + 1][j - 1].getIsMonsterSet()) {
                    nearMonsters.add(board.getGrid()[i + 1][j - 1].getMonster());
                }
            } catch (Exception ignored) {

            }
            try {
                if (board.getGrid()[i][j + 1].getIsMonsterSet()) {
                    nearMonsters.add(board.getGrid()[i][j + 1].getMonster());
                }
            } catch (Exception ignored) {

            }
            try {
                if (board.getGrid()[i - 1][j + 1].getIsMonsterSet()) {
                    nearMonsters.add(board.getGrid()[i - 1][j + 1].getMonster());
                }
            } catch (Exception ignored) {

            }
            return nearMonsters;
        }
        return null;
    }


    public void levelUp() {
        // Levels up the hero and updates the skills
        System.out.println(this.getName() + " Leveled up!");
        this.setLevel(this.getLevel() + 1);
        this.setMana((int) (this.getMana() * 1.1));
        if (Objects.equals(this.getType(), "Warrior")) {
            this.setStrength((int) (this.getStrength() * 1.1));
            this.setAgility((int) (this.getAgility() * 1.1));
            this.setDexterity((int) (this.getDexterity() * 1.05));
        } else if (Objects.equals(this.getType(), "Sorcerer")) {
            this.setStrength((int) (this.getStrength() * 1.05));
            this.setAgility((int) (this.getAgility() * 1.1));
            this.setDexterity((int) (this.getDexterity() * 1.1));
        } else {
            this.setStrength((int) (this.getStrength() * 1.1));
            this.setAgility((int) (this.getAgility() * 1.05));
            this.setDexterity((int) (this.getDexterity() * 1.1));
        }
    }

    public void usePotion(String[] words, int att_inc){
        // Using the potion
        for(String word: words){
            if(word.toLowerCase().equals("mana"))
                this.setMana(this.getMana() + att_inc);
            else if(word.toLowerCase().equals("health"))
                this.setHp(this.getHp() + att_inc);
            else if(word.toLowerCase().equals("strength"))
                this.setStrength(this.getStrength() + att_inc);
            else if(word.toLowerCase().equals("agility"))
                this.setAgility(this.getAgility() + att_inc);
            else if(word.toLowerCase().equals("dexterity"))
                this.setDexterity(this.getDexterity() + att_inc);
            else if(word.toLowerCase().equals("defense"))
                this.setDefense(this.getDefense() + att_inc);
        }
    }

    public List<Weaponry> getWeapons() {
        return weapons;
    }

    public void addWeapon(Weaponry weapon) {
        this.weapons.add(weapon);
    }

    public List<Armory> getArmories() {
        return armories;
    }

    public void addArmory(Armory armory) {
        this.armories.add(armory);
    }

    public List<Potions> getPotions() {
        return potions;
    }

    public void addPotion(Potions potion) {
        this.potions.add(potion);
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void addSpell(Spell spell) {
        this.spells.add(spell);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStarting_money() {
        return starting_money;
    }

    public void setStarting_money(int starting_money) {
        this.starting_money = starting_money;
    }

    public int getStarting_exp() {
        return starting_exp;
    }

    public void displayHero(int i){
        System.out.format("%d   %20s    %8d     %4d    %4d    %4d     %4d     %4d     %4d    %2d  %3d%n", i+1, this.getName(), this.getLevel(), this.getHp(), this.getMana(), this.getStrength(), this.getAgility(), this.getDexterity(), this.getStarting_money(), this.getStarting_exp(), this.getDefense());
    }

    public void setStarting_exp(int starting_exp) {
        this.starting_exp = starting_exp;
    }

    public void showInventory(){
        // Shows the hero's inventory
        this.showArmories();
        System.out.println();
        this.showWeapons();
        System.out.println();
        this.showPotions();
        System.out.println();
        this.showSpells();
    }

    public void showArmories(){
        // Shows the hero's armories
        System.out.println("\u001B[33m List of armories: \u001b[0m");
        Display.displayArmory(armories);
    }

    public void showWeapons(){
        // Shows the hero's weapons
        System.out.println("\u001B[33m List of weapons: \u001b[0m");
        Display.displayWeaponry(weapons);
    }

    public void showPotions(){
        // Shows the hero's potions
        System.out.println("\u001B[33m List of potions: \u001b[0m");
        Display.displayPotions(potions);
    }

    public void showSpells(){
        // Shows the hero's spells
        System.out.println("\u001B[33m List of spells \u001b[0m");
        Display.displaySpells(spells);
    }

    public int fight(Monsters curMonster, Market market){
        do {
            System.out.println("\u001B[32m " + this.getName() + " vs " + curMonster.getName() + " \u001b[0m");
            int fchoice = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "What do you want to do?\n0. Quit\n1. Attack\n2. Cast a spell\n3. Use Potion\n4. Equip an item\n5. Check Inventory\n", 0, 5);
            if (fchoice == 0) {
                System.out.println("Thanks for playing");
                return 1;
            } else if (fchoice == 1) {
                // Attack the monster
                if (GameFunctions.getRandomBoolean((float) (curMonster.getDodge_chance() * 0.01))) { // Checking for monster dodging the attack
                    System.out.println("\u001B[31m The monster has dodged the attack! \u001b[0m");
                    Parser.parseMusic("mixkit-troll-warrior-laugh-409.wav");
                } else {
                    int dmg;
                    // Calculating the damage dealt by the hero
                    if (this.getWeapons().size() > 0) {
                        dmg = (int) ((this.getStrength() + this.getCurWeapon().getDamage()) * 0.05);
                    } else {
                        dmg = (int) (this.getStrength() * 0.05);
                    }
                    curMonster.setHp(Math.max((curMonster.getHp() - dmg), 0)); // Reduces the health of the monster
//                    market.getWeaponry().use(dmg, curMonster);
                    System.out.println("\u001B[33m" + this.getName() + " has dealt " + dmg + " damage! \u001b[0m");
                    Parser.parseMusic("mixkit-quick-ninja-strike-2146.wav");
                }
                break;
            } else if (fchoice == 2) {
                // Cast a spell
                if (this.getSpells().size() == 0) {
                    System.out.println("\u001B[31m You don't have any spells to cast \u001b[0m");
                    i--;
                    continue;
                } else {
                    this.showSpells(); // shows the spells available with the current hero
                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of the spell you want to cast\n 0. Quit\n", 0, this.getSpells().size());
                    if (id == 0) {
                        System.out.println("Thanks for playing");
                        return 1;
                    } else if (this.getSpells().get(id - 1).getMana_cost() > this.getMana()) { //checking if player has the required mana to cast the spell
                        System.out.println("\u001B[31m You don't have the required mana to cast this spell \u001b[0m");
                        i--;
                        continue;
                    } else {
                        if (Objects.equals(this.getSpells().get(id - 1).getType(), "Fire Spell")) {
                            market.getFireSpell().use(curMonster, this, i, id, market); // Calls the default use method from the isCastable interface
                            Parser.parseMusic("mixkit-fireball-spell-1347.wav");
                        } else if (Objects.equals(this.getSpells().get(id - 1).getType(), "Ice Spell")) {
                            market.getIceSpell().use(curMonster, this, i, id, market); // Calls the default use method from the isCastable interface
                            Parser.parseMusic("mixkit-thin-icicles-spell-882.wav");
                        } else {
                            market.getLightningSpell().use(curMonster, this, i, id, market); // Calls the default use method from the isCastable interface
                            Parser.parseMusic("mixkit-light-spell-873.wav");
                        }
                    }
                }
                break;
            } else if (fchoice == 3) {
                // Use a potion
                if (this.getPotions().size() == 0) {
                    System.out.println("\u001B[31m You don't have any potions \u001b[0m");
                    i--;
                    continue;
                } else {
                    this.showPotions();
                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of the potion you want to use\n 0. Quit\n", 0, this.getPotions().size());
                    if (id == 0) {
                        System.out.println("Thanks for playing");
                        return 1;
                    } else {
                        market.getPotion().use(this, id); // Calls the default use method from the isDrinkable interface
                    }
                }
                break;
            } else if (fchoice == 4) {
                if (GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Would you like to equip/unequip a weapon?\n1. Yes\n2. No\n", 1, 2) == 1) {
                    if (this.getWeapons().size() == 0) {
                        System.out.println("\u001B[31m You don't own any weapon \u001b[0m");
                        i--;
                        continue;
                    }
                    this.showWeapons();
                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Enter the id of the weapon you want to equip\n0. Quit\n", 0, this.getWeapons().size());
                    if (id == 0) {
                        i--;
                        continue;
                    }
                    for (Weaponry item : this.getWeapons()) {
                        item.setEquip("No"); // Setting other weapons as unequipped
                    }
                    Weaponry item = this.getWeapons().get(id - 1);
                    item.setEquip("Yes");
                    this.getWeapons().add(item);
                    this.setIsEquipped(true);
                    this.setCurWeapon(item); // Updating the current weapon
                    i--;
                }
                if (GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Would you like to equip/unequip an armory?\n1. Yes\n2. No\n", 1, 2) == 1) {
                    if (this.getArmories().size() == 0) {
                        System.out.println("\u001B[31m You don't own any armory \u001b[0m");
                        i--;
                        continue;
                    }
                    this.showArmories();
                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Enter the id of the armory you want to equip\n0. Quit\n", 0, this.getArmories().size());
                    if (id == 0) {
                        i--;
                        continue;
                    }
                    for (Armory item : this.getArmories()) {
                        item.setEquip("No"); // Setting other armories as unequipped
                    }
                    Armory item = this.getArmories().get(id - 1);
                    item.setEquip("Yes");
                    this.getArmories().add(item);
                    this.setIsEquipped(true);
                    this.setCurArmory(item); // Updating the current armor
                    i--;
                }
                break;
            } else {
                this.showInventory();
                System.out.println();
                i--;
                continue;
            }
        }while(true);
        return 0;
    }

    public abstract int getN();

    public abstract String getType();

    public abstract void displayList();

    public abstract List<String> getAllLines();
}
