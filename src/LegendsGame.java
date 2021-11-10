import java.util.*;

public class LegendsGame extends RpgGame{
    public Map<Integer,ArrayList<Monsters>> monsters=new HashMap<Integer,ArrayList<Monsters>>();
    private List<Monsters> curMonsters; // Stores monsters in each fight
    private LegendsPlayer player; // Creates a player object
    private Market market; // Creates a market object
    // hero and monster flags are used to check for hero reviving setup
    private int heroFlag;
    private int monsterFlag;
    private int level;
    private int hcount;
    ASCIIArtGenerator artGen = new ASCIIArtGenerator();

    public List<Monsters> getCurMonsters() {
        // Returns a list of monsters
        return curMonsters;
    }

    @Override
    public String getName() {
        return "Legends: Monsters and Heroes";
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

    public void getMonsters(LegendsPlayer player){
        // Returns random monsters of same level as the hero from the list of monsters
        Random rand = new Random();
        curMonsters = new ArrayList<>();
        for(Heroes hero: player.getHeroes()){
            while (true) { // Avoiding repetition of monsters
                Monsters monster = monsters.get(hero.getLevel()).get(rand.nextInt(monsters.get(hero.getLevel()).size()));
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

    public int fight(int monIndex){
        // Logic to fight the monster
        for (int i = 0; i < player.getnHero(); i++) {
            Heroes curHero = player.getHeroes().get(i);
            Monsters curMonster = this.getCurMonsters().get(monIndex);
            System.out.println("\u001B[32m " + curHero.getName() + " vs " + curMonster.getName() + " \u001b[0m");
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
                    if (curHero.getWeapons().size() > 0) {
                        dmg = (int) ((curHero.getStrength() + curHero.getCurWeapon().getDamage()) * 0.05);
                    } else {
                        dmg = (int) (curHero.getStrength() * 0.05);
                    }
                    curMonster.setHp(Math.max((curMonster.getHp() - dmg), 0)); // Reduces the health of the monster
                    System.out.println("\u001B[33m" + curHero.getName() + " has dealt " + dmg + " damage! \u001b[0m");
                    Parser.parseMusic("mixkit-quick-ninja-strike-2146.wav");
                    if (curMonster.getHp() <= 0) {
                        // Checking if player won the round
                        Display.displayMonsters(this.getCurMonsters());
                        System.out.println("\u001B[32m" + curHero.getName() + " Won! \u001b[0m");
                        Parser.parseMusic("mixkit-achievement-bell-600.wav");
                        level = Math.max(level, curMonster.getLevel());
                        heroFlag = 1;
                        curMonster.setHp(curMonster.getLevel() * 100);
                        this.getCurMonsters().remove(curMonster);
                        if(this.getCurMonsters().size() == 0)
                            break;
                        monIndex = (monIndex) % this.getCurMonsters().size();
                        continue;
                    }
                }
                monIndex = (monIndex+1) % this.getCurMonsters().size();
            } else if (fchoice == 2) {
                // Cast a spell
                if (curHero.getSpells().size() == 0) {
                    System.out.println("\u001B[31m You don't have any spells to cast \u001b[0m");
                    i--;
                    continue;
                } else {
                    curHero.showSpells(); // shows the spells available with the current hero
                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of the spell you want to cast\n 0. Quit\n", 0, curHero.getSpells().size());
                    if (id == 0) {
                        System.out.println("Thanks for playing");
                        return 1;
                    } else if (curHero.getSpells().get(id - 1).getMana_cost() > curHero.getMana()) { //checking if player has the required mana to cast the spell
                        System.out.println("\u001B[31m You don't have the required mana to cast this spell \u001b[0m");
                        i--;
                        continue;
                    } else {
                        if (Objects.equals(curHero.getSpells().get(id - 1).getType(), "Fire Spell")) {
                            market.getFireSpell().use(curMonster, curHero, i, id, market); // Calls the default use method from the isCastable interface
                            Parser.parseMusic("mixkit-fireball-spell-1347.wav");
                        }
                        else if (Objects.equals(curHero.getSpells().get(id - 1).getType(), "Ice Spell")) {
                            market.getIceSpell().use(curMonster, curHero, i, id, market); // Calls the default use method from the isCastable interface
                            Parser.parseMusic("mixkit-thin-icicles-spell-882.wav");
                        }
                        else {
                            market.getLightningSpell().use(curMonster, curHero, i, id, market); // Calls the default use method from the isCastable interface
                            Parser.parseMusic("mixkit-light-spell-873.wav");
                        }
                        if (curMonster.getHp() <= 0) {
                            // Checking if player won the round
                            Display.displayMonsters(this.getCurMonsters());
                            System.out.println("\u001B[32m" + curHero.getName() + " Won! \u001b[0m");
                            Parser.parseMusic("mixkit-achievement-bell-600.wav");
                            level = Math.max(level, curMonster.getLevel());
                            heroFlag = 1;
                            curMonster.setHp(curMonster.getLevel() * 100);
                            this.getCurMonsters().remove(curMonster);
                            if(this.getCurMonsters().size() == 0)
                                break;
                            monIndex = (monIndex) % this.getCurMonsters().size();
                            continue;
                        }
                    }
                }
                monIndex = (monIndex+1) % this.getCurMonsters().size();
            } else if (fchoice == 3) {
                // Use a potion
                if (curHero.getPotions().size() == 0) {
                    System.out.println("\u001B[31m You don't have any potions \u001b[0m");
                    i--;
                    continue;
                } else {
                    curHero.showPotions();
                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the id of the potion you want to use\n 0. Quit\n", 0, curHero.getPotions().size());
                    if (id == 0) {
                        System.out.println("Thanks for playing");
                        return 1;
                    } else {
                        market.getPotion().use(player, i, id); // Calls the default use method from the isDrinkable interface
                        monIndex = (monIndex+1) % this.getCurMonsters().size();
                    }
                }
            } else if(fchoice == 4){
                if (GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Would you like to equip/unequip a weapon?\n1. Yes\n2. No\n", 1, 2) == 1) {
                    if (curHero.getWeapons().size() == 0) {
                        System.out.println("\u001B[31m You don't own any weapon \u001b[0m");
                        i--;
                        continue;
                    }
                    curHero.showWeapons();
                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Enter the id of the weapon you want to equip\n0. Quit\n", 0, curHero.getWeapons().size());
                    if (id == 0) {
                        i--;
                        continue;
                    }
                    for (Weaponry item : curHero.getWeapons()) {
                        item.setEquip("No"); // Setting other weapons as unequipped
                    }
                    Weaponry item = curHero.getWeapons().get(id - 1);
                    item.setEquip("Yes");
                    curHero.getWeapons().add(item);
                    curHero.setIsEquipped(true);
                    curHero.setCurWeapon(item); // Updating the current weapon
                    i--;
                }
                if (GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Would you like to equip/unequip an armory?\n1. Yes\n2. No\n", 1, 2) == 1) {
                    if (curHero.getArmories().size() == 0) {
                        System.out.println("\u001B[31m You don't own any armory \u001b[0m");
                        i--;
                        continue;
                    }
                    curHero.showArmories();
                    int id = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Enter the id of the armory you want to equip\n0. Quit\n", 0, curHero.getArmories().size());
                    if (id == 0){
                        i--;
                        continue;
                    }
                    for (Armory item : curHero.getArmories()) {
                        item.setEquip("No"); // Setting other armories as unequipped
                    }
                    Armory item = curHero.getArmories().get(id - 1);
                    item.setEquip("Yes");
                    curHero.getArmories().add(item);
                    curHero.setIsEquipped(true);
                    curHero.setCurArmory(item); // Updating the current armor
                    i--;
                }
            }
            else{
                curHero.showInventory();
                System.out.println();
                i--;
                continue;
            }

            int monDmg = (int) (curMonster.getDamage() * 0.05); // Calculating damage dealt by monster
            if (GameFunctions.getRandomBoolean((float) (curHero.getAgility() * 0.001))) { // Checking if hero dodged the attack
                System.out.println("\u001B[32m You have dodged the attack! \u001b[0m");
                continue;
            } else {
                if(curHero.getArmories().size() == 0)
                    curHero.setHp(Math.max((curHero.getHp() - monDmg),0)); // Updating hp of hero without armory
                else
                    curHero.setHp(Math.max(Math.min((curHero.getHp() + curHero.getCurArmory().getDamage_reduction() - monDmg), curHero.getHp()),0)); // Updating hp of hero without armory
                System.out.println("\u001B[31m" + curMonster.getName() + " has dealt " + monDmg + " damage! \u001b[0m");
                Parser.parseMusic("mixkit-quick-ninja-strike-2146.wav");
                if (curHero.getHp() <= 0) {
                    // Checking if monster won the round
                    System.out.println("\u001B[31m Monster won! \u001b[0m");
                    Parser.parseMusic("mixkit-8-bit-lose-2031.wav");
                    monsterFlag = 1;
                    hcount++;
                }
            }

            // Updating mana and health after each round
            curHero.setHp((int) (curHero.getHp() * (1.1)));
            curHero.setMana((int) (curHero.getMana() * (1.1)));
            System.out.println("Heroes");
            Display.displayHeroes(player.getHeroes());
            System.out.println("Monsters");
            Display.displayMonsters(this.getCurMonsters());
        }
        return 0;
    }

    @Override
    public void startGame() throws Exception {
        // The game starts
        artGen.printTextArt("Welcome", ASCIIArtGenerator.ART_SIZE_MEDIUM);
        Parser.parseMusic("mixkit-game-level-completed-2059.wav");
        System.out.println("Welcome to the game of Legends: Monsters and Heroes!!");
        player = new LegendsPlayer();
        player.setName(GameFunctions.safeScanString(new Scanner(System.in), "Please enter your name:\n"));
        player.setnHero(GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the number of heroes (1-3):\n", 1, 3));
        player.setSymbol(GameFunctions.safeScanString(new Scanner(System.in), "Please enter your symbol:\n"));
        player.setHeroes();
        LegendsBoard board = new LegendsBoard();
        board.createBoard(); // Creates a map
        board.addPlayer(player);
        System.out.println("Lets build the team");
        player.addHeroes(); // Heroes are added to players
        System.out.println("Your team:");
        Display.displayHeroes(player.getHeroes());
        this.createMonsters(); // Creates monsters
        market = new Market();
        market.createMarketList(); // A market is created
        Display.displayBoard(board);
        Display.displayLegend(player.getSymbol());

        while(true){
            String[] data = {"w", "a", "s", "d", "i", "e", "m", "q"};
            String choice;
            label:
            do {
                choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is your turn to move: \u001b[0m \nMove(W/A/S/D)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                choice = choice.toLowerCase();
                if(!Arrays.asList(data).contains(choice)) {
                    System.out.println("Please enter a valid choice....");
                }else {
                    switch (choice) {
                        case "w": // Move up
                            if (!board.canMove(board.getI() - 1, board.getJ())) {
                                System.out.println("Inaccessible! Please enter a valid choice....");
                            } else {
                                board.move(board.getI() - 1, board.getJ(), player);
                                Display.displayBoard(board);
                                Display.displayLegend(player.getSymbol());
                                System.out.println("\u001B[42m " + player.getName() + ", You have moved \u001b[0m");
                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                break label;
                            }
                            break;
                        case "s": // Move down
                            if (!board.canMove(board.getI() + 1, board.getJ())) {
                                System.out.println("Inaccessible! Please enter a valid choice....");
                            } else {
                                board.move(board.getI() + 1, board.getJ(), player);
                                Display.displayBoard(board);
                                Display.displayLegend(player.getSymbol());
                                System.out.println("\u001B[42m " + player.getName() + "You have moved \u001b[0m");
                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                break label;
                            }
                            break;
                        case "a": // Move left
                            if (!board.canMove(board.getI(), board.getJ() - 1)) {
                                System.out.println("Inaccessible! Please enter a valid choice....");
                            } else {
                                board.move(board.getI(), board.getJ() - 1, player);
                                Display.displayBoard(board);
                                Display.displayLegend(player.getSymbol());
                                System.out.println("\u001B[42m " + player.getName() + "You have moved \u001b[0m");
                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                break label;
                            }
                            break;
                        case "d": // Move right
                            if (!board.canMove(board.getI(), board.getJ() + 1)) {
                                System.out.println("Inaccessible! Please enter a valid choice....");
                            } else {
                                board.move(board.getI(), board.getJ() + 1, player);
                                Display.displayBoard(board);
                                Display.displayLegend(player.getSymbol());
                                System.out.println("\u001B[42m " + player.getName() + "You have moved \u001b[0m");
                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                break label;
                            }
                            break;
                        case "e": // Show inventory of all heroes
                            for (int i = 0; i < player.getnHero(); i++) {
                                System.out.println("\u001B[36m " + player.getHeroes().get(i).getName() + " Inventory \u001b[0m");
                                player.getHeroes().get(i).showInventory();
                                System.out.println();
                            }
                            break;
                        case "i":
                            // Show info of all heroes
                            Display.displayHeroes(player.getHeroes());
                            break;
                        case "m":
                            // Display board
                            Display.displayBoard(board);
                            Display.displayLegend(player.getSymbol());
                            break;
                        case "q":
                            // Quit the game
                            System.out.println("Thanks for playing");
                            return;
                    }
                }
            }while (true);

            if(board.grid[board.getI()][board.getJ()].getSymbol().contains("M")){
                // Player enters a market
                market.buySell(player);
            }
            else if(!board.grid[board.getI()][board.getJ()].getSymbol().contains("I") && GameFunctions.getRandomBoolean((float)0.17)){
                // Player encounters a monster
                System.out.println("\u001B[41m You have encountered the monsters!! \u001b[0m");
                Parser.parseMusic("mixkit-aggressive-beast-roar-13.wav");
                this.getMonsters(player);
                int level = 1;
                System.out.println(this.getCurMonsters().size());
                System.out.println("Heroes");
                Display.displayHeroes(player.getHeroes());
                System.out.println("Monsters");
                Display.displayMonsters(this.getCurMonsters());
                heroFlag = 0;
                monsterFlag = 0;
                hcount = 0;
                while(this.getCurMonsters().size() > 0 && hcount < player.getHeroes().size()) {
                    int monIndex = 0;
                    int j = this.fight(monIndex); // Fighting the monster
                    if(j == 1)
                        return;
                }
                if(heroFlag == 0 && monsterFlag == 1){ // Monsters win the fight
                    System.out.println("\u001B[41m Monsters won the fight! \u001b[0m");
                    Parser.parseMusic("mixkit-ominous-drums-227.wav");
                    for(Heroes hero : player.getHeroes()){
                        hero.setHp((hero.getLevel() * 100) / 2);
                    }
                }
                else if(heroFlag == 1) { // Heroes win the fight
                    System.out.println("\u001B[42m Heroes won the fight! \u001b[0m");
                    Parser.parseMusic("mixkit-ethereal-fairy-win-sound-2019.wav");
                    for (Heroes hero : player.getHeroes()) {
                        if (hero.getHp() != 0) {
                            // Updating money and exp for heroes that are alive
                            hero.setStarting_money(hero.getStarting_money() + level * 100);
                            hero.setStarting_exp(hero.getStarting_exp() + 2);
                            hero.setExp(hero.getExp() + 2);
                        }
                        else{
                            // Reviving heroes with health 0
                            hero.setHp((hero.getLevel() * 100) / 2);
                        }
                        if (hero.getExp() >= (hero.getLevel() * 10)) { // Checking if the hero levels up
                            hero.levelUp(); // Levels up the hero
                            Display.displayHeroes(player.getHeroes());
                        }
                    }
                }
            }
        }
    }
}
