import java.util.*;

public class ValorGame extends RpgGame{
    ValorPlayer player;
    private Market market;
    private int newGameFlag;
    ASCIIArtGenerator artGen = new ASCIIArtGenerator();

    @Override
    public String getName() {
        return "Legends of Valor";
    }

    public void updateAttributes(ValorBoard board, Heroes hero){
        // Updates hero's attributes based on the type of cell he is in
        if(Objects.equals(board.getGrid()[hero.getI()][hero.getJ()].getName(), "Nexus")){
            market.buySell(hero);
        }
        else if(Objects.equals(board.getGrid()[hero.getI()][hero.getJ()].getName(), "Bush")){
            hero.setDexterity((int) (hero.getDexterity() * 1.1));
        }
        else if(Objects.equals(board.getGrid()[hero.getI()][hero.getJ()].getName(), "Cave")){
            hero.setAgility((int) (hero.getAgility() * 1.1));
        }
        else if(Objects.equals(board.getGrid()[hero.getI()][hero.getJ()].getName(), "Koulou")){
            hero.setStrength((int) (hero.getStrength() * 1.1));
        }
    }

    public void teleport(Heroes hero, ValorBoard board){
        // Gather the options for teleportation based on the hero's current lane
        String laneOptions = "";
        String[] laneData = new String[2];
        if (hero.getJ() == 0 || hero.getJ() == 1) {
            laneData[0] = "2";
            laneData[1] = "3";
            laneOptions = "2.Mid Lane or 3.Bot Lane?: ";
        }
        else if (hero.getJ() == 3 || hero.getJ() == 4) {
            laneData[0] = "1";
            laneData[1] = "3";
            laneOptions = "1.Top Lane or 3.Bot Lane?: ";
        }
        else if (hero.getJ() == 6 || hero.getJ() == 7) {
            laneData[0] = "1";
            laneData[1] = "2";
            laneOptions = "1.Top Lane or 2.Mid Lane?: ";
        }
        // Get the hero's target lane
        String laneChoice = GameFunctions.safeScanString(new Scanner(System.in), laneOptions);
        while (!Arrays.asList(laneData).contains(laneChoice)) {
            System.out.println("Please enter a valid choice....");
            laneChoice = GameFunctions.safeScanString(new Scanner(System.in), laneOptions);
        }

        // check the hero's current row
        // Set the target teleportation square to -1, -1 (ineligible) and monsterLocation to -1 (no monster)
        int rowCheck = hero.getI();
        int teleRow = -1;
        int teleCol = -1;
        int monsterLoc = -1;
        System.out.println("Lane Choice:" + laneChoice);
        switch(laneChoice) {
            case "1":
                //Check if there are any monsters
                for (int k = rowCheck; k < board.getBoardSize(); k++) {
                    if (board.getGrid()[k][0].getIsMonsterSet() || board.getGrid()[k][1].getIsMonsterSet()) {
                        monsterLoc = k;
                    }

                    if (monsterLoc != -1) {
                        break;
                    }
                }

                // If there is no monster, then the hero can teleport anywhere in the lane that they have traveled already
                if (monsterLoc == -1) {
                    monsterLoc = rowCheck - 1;
                }

                //Place the teleporting hero in front of any monster but not where a hero is
                for (int j = monsterLoc + 1; j < board.getBoardSize(); j++) {
                    if (!board.getGrid()[j][0].getIsHeroSet()) {
                        teleRow = j;
                        teleCol = 0;
                        break;
                    }

                    if (!board.getGrid()[j][1].getIsHeroSet()) {
                        teleRow = j;
                        teleCol = 1;
                        break;
                    }
                }

                // If teleRow and teleCol never change, then the hero is ineligible to teleport to that lane
                if (teleRow == -1 && teleCol == -1) {
                    System.out.println("You cannot teleport!");
                }
                else {
                    board.moveHero(teleRow, teleCol, hero);
                }
                break;
            case "2":
                //Check if there are any monsters
                for (int k = rowCheck; k < board.getBoardSize(); k++) {
                    if (board.getGrid()[k][3].getIsMonsterSet() || board.getGrid()[k][4].getIsMonsterSet()) {
                        monsterLoc = k;
                    }

                    if (monsterLoc != -1) {
                        break;
                    }
                }

                // If there is no monster, then the hero can teleport anywhere in the lane that they have traveled already
                if (monsterLoc == -1) {
                    monsterLoc = rowCheck - 1;
                }

                //Place the teleporting hero in front of any monster but not where a hero is
                for (int j = monsterLoc + 1; j < board.getBoardSize(); j++) {
                    if (!board.getGrid()[j][3].getIsHeroSet()) {
                        teleRow = j;
                        teleCol = 3;
                        break;
                    }

                    if (!board.getGrid()[j][4].getIsHeroSet()) {
                        teleRow = j;
                        teleCol = 4;
                        break;
                    }
                }

                // If teleRow and teleCol never change, then the hero is ineligible to teleport to that lane
                if (teleRow == -1 && teleCol == -1) {
                    System.out.println("You cannot teleport!");
                }
                else {
                    board.moveHero(teleRow, teleCol, hero);
                }
                break;
            case "3":
                //Check if there are any monsters
                for (int k = rowCheck; k < board.getBoardSize(); k++) {
                    if (board.getGrid()[k][6].getIsMonsterSet() || board.getGrid()[k][7].getIsMonsterSet()) {
                        monsterLoc = k;
                    }

                    if (monsterLoc != -1) {
                        break;
                    }
                }

                // If there is no monster, then the hero can teleport anywhere in the lane that they have traveled already
                if (monsterLoc == -1) {
                    monsterLoc = rowCheck - 1;
                }

                //Place the teleporting hero in front of any monster but not where a hero is
                for (int j = monsterLoc + 1; j < board.getBoardSize(); j++) {
                    if (!board.getGrid()[j][6].getIsHeroSet()) {
                        teleRow = j;
                        teleCol = 6;
                        break;
                    }

                    if (!board.getGrid()[j][7].getIsHeroSet()) {
                        teleRow = j;
                        teleCol = 7;
                        break;
                    }
                }

                 // If teleRow and teleCol never change, then the hero is ineligible to teleport to that lane
                if (teleRow == -1 && teleCol == -1) {
                    System.out.println("You cannot teleport!");
                }
                else {
                    board.moveHero(teleRow, teleCol, hero);
                }
                break;
        }
        Display.displayBoard(board);
        Display.displayLegend(hero.getSymbol());
        System.out.println("\u001B[42m " + hero.getSymbol() + "You have teleported \u001b[0m");
        Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
    }


    @Override
    public void startGame() throws Exception {
        // The game starts
        artGen.printTextArt("Welcome", ASCIIArtGenerator.ART_SIZE_MEDIUM);
        Parser.parseMusic("mixkit-game-level-completed-2059.wav");
        System.out.println("Welcome to the game of Legends of Valor!!");
        while(true) {
            newGameFlag = 0;
            player = new ValorPlayer(); // creates new player
            player.setName(GameFunctions.safeScanString(new Scanner(System.in), "Please enter your name:\n"));
            player.setnHero(3);

            player.setHeroes(); // creates new heroes list
            player.addHeroes(); // adds heroes to players
            player.getHeroes().get(0).setSymbol("H1");
            player.getHeroes().get(1).setSymbol("H2");
            player.getHeroes().get(2).setSymbol("H3");

            while (true) {
                player.setnMonster(3);
                player.createMonsters(); // creates monsters
                player.getMonsters(); // adds 3 monsters of hero's level
                player.getCurMonsters().get(0).setSymbol("M1");
                player.getCurMonsters().get(1).setSymbol("M2");
                player.getCurMonsters().get(2).setSymbol("M3");

                ValorBoard board = new ValorBoard(); // creates a game board instance
                board.createBoard(); // Creates a map
                board.addPlayer(player); // adds player to the game board
                board.addMonster(player); // adds monsters to the game board
                Display.displayBoard(board);
                market = new Market(); // creates new market instance
                market.createMarketList(); // creates the market inventory
                String[] data = {"w", "a", "s", "d", "i", "e", "m", "q", "b", "f", "t"};

                String choice;
                while (true) {
                    for (int i = 0; i < player.getHeroes().size(); i++) {
                        Heroes hero = player.getHeroes().get(i); // current hero
                        Monsters monster = player.getCurMonsters().get(i); // current monster
                        hero.setHp((int) (hero.getHp() * 1.1)); // updating hp in every round
                        hero.setMana((int) (hero.getMana() * 1.1)); // updating mana in every round
                        if (Objects.equals(board.getGrid()[hero.getI()][hero.getJ()].getName(), "Nexus")) {
                            market.buySell(hero); // allowing heroes to buy or sell an item in the nexus cell
                        }
                        int flag = 0;

                        label:
                        do {
                            // Check if there is a monster nearby, if yes, show the fight menu, else, show the move menu
                            boolean isAttack = hero.isMonsterNearby(board);
                            if (isAttack) {
                                choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[31m " + hero.getSymbol() + ", you have run into a monster: \u001b[0m \nFight(F)\nMove(W/A/S/D)\nBackToNexus(B)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                            } else {
                                choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is " + hero.getSymbol() + " turn to move: \u001b[0m \nMove (W/A/S/D)\nBackToNexus (B)\nTeleport (T)\nCheck player Info (I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                            }
                            choice = choice.toLowerCase();
                            if (!Arrays.asList(data).contains(choice)) {
                                System.out.println("Please enter a valid choice....");
                            } else {
                                switch (choice) {
                                    case "w": // Move up
                                        if (!board.canMove(hero.getI() - 1, hero.getJ()) || board.getGrid()[hero.getI() - 1][hero.getJ()].getIsHeroSet() == true) {
                                            System.out.println("Inaccessible! Please enter a valid choice....");
                                        } else {
                                            if (board.getGrid()[hero.getI()][hero.getJ()].getIsMonsterSet() || board.getGrid()[hero.getI()][(int) (hero.getJ() - Math.pow(-1, (hero.getJ() + 1) % 3))].getIsMonsterSet()) {
                                                System.out.println("\u001B[31m Monster in sight, you cannot move ahead!! Please enter a different option \u001B[0m");
                                            } else {
                                                board.moveHero(hero.getI() - 1, hero.getJ(), hero); // move the hero up
                                                if (hero.getI() == 0) { // checking if hero wins the game
                                                    System.out.println("\u001B[32m Heroes won the game \u001B[0m");
                                                    Parser.parseMusic("mixkit-ethereal-fairy-win-sound-2019.wav");
                                                    newGameFlag = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Do you want to play another game?\n1. Yes\n2. No\n", 1, 2);
                                                    break label;
                                                }
                                                updateAttributes(board, hero); // update hero's attributes
                                                Display.displayBoard(board);
                                                Display.displayLegend(hero.getSymbol());
                                                System.out.println("\u001B[42m " + hero.getSymbol() + ", you have moved \u001b[0m");
                                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                                break label;
                                            }
                                        }
                                        break;
                                    case "s": // Move down
                                        if (!board.canMove(hero.getI() + 1, hero.getJ()) || board.getGrid()[hero.getI() + 1][hero.getJ()].getIsHeroSet() == true) {
                                            System.out.println("Inaccessible! Please enter a valid choice....");
                                        } else {
                                            board.moveHero(hero.getI() + 1, hero.getJ(), hero); // move the hero down
                                            updateAttributes(board, hero); // update hero's attributes
                                            Display.displayBoard(board);
                                            Display.displayLegend(hero.getSymbol());
                                            System.out.println("\u001B[42m " + hero.getSymbol() + ", you have moved \u001b[0m");
                                            Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                            break label;
                                        }
                                        break;
                                    case "a": // Move left
                                        if (!board.canMove(hero.getI(), hero.getJ() - 1) || board.getGrid()[hero.getI()][hero.getJ() - 1].getIsHeroSet() == true) {
                                            System.out.println("Inaccessible! Please enter a valid choice....");
                                        } else {
                                            board.moveHero(hero.getI(), hero.getJ() - 1, hero); // move the hero left
                                            updateAttributes(board, hero); // update hero's attributes
                                            Display.displayBoard(board);
                                            Display.displayLegend(hero.getSymbol());
                                            System.out.println("\u001B[42m " + hero.getSymbol() + ", you have moved \u001b[0m");
                                            Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                            break label;
                                        }
                                        break;
                                    case "d": // Move right
                                        if (!board.canMove(hero.getI(), hero.getJ() + 1) || board.getGrid()[hero.getI()][hero.getJ() + 1].getIsHeroSet() == true) {
                                            System.out.println("Inaccessible! Please enter a valid choice....");
                                        } else {
                                            board.moveHero(hero.getI(), hero.getJ() + 1, hero); // move the hero right
                                            updateAttributes(board, hero); // update hero's attributes
                                            Display.displayBoard(board);
                                            Display.displayLegend(hero.getSymbol());
                                            System.out.println("\u001B[42m " + hero.getSymbol() + ", you have moved \u001b[0m");
                                            Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                            break label;
                                        }
                                        break;
                                    case "b": // Go back to nexus
                                        board.moveHero(board.getBoardSize() - 1, hero.getJ(), hero); // move the hero back to nexus
                                        updateAttributes(board, hero); // update hero's attributes
                                        Display.displayBoard(board);
                                        Display.displayLegend(hero.getSymbol());
                                        System.out.println("\u001B[42m " + hero.getSymbol() + ", you have moved \u001b[0m");
                                        Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                        break label;
                                    case "t":
                                        teleport(hero, board); // calls the teleport method
                                        break label;
                                    case "e": // Show inventory of all heroes
                                        System.out.println("\u001B[36m " + hero.getSymbol() + " Inventory \u001b[0m");
                                        hero.showInventory();
                                        System.out.println();
                                        break;
                                    case "i":
                                        // Show info of all heroes
                                        Display.displayHeroes(player.getHeroes());
                                        break;
                                    case "m":
                                        // Display board
                                        Display.displayBoard(board);
                                        Display.displayLegend(hero.getSymbol());
                                        break;
                                    case "f":
                                        // fight
                                        System.out.println("Fight begins!");
                                        updateAttributes(board, hero); // update hero's attributes before the fight
                                        Display.displayBoard(board);
                                        try {
                                            List<Monsters> nearMonsters = hero.getNearByMonsters(board); // gets nearby monsters
                                            Random rand = new Random();
                                            int randInt = rand.nextInt(nearMonsters.size()); // picks a random monster from the ones nearby
                                            Monsters curMonster = nearMonsters.get(randInt);
                                            int fchoice = hero.fight(curMonster, market); // fighting the monster
                                            if (fchoice == 1)
                                                return;
                                            System.out.println("Monsters info");
                                            Display.displayMonsters(player.getCurMonsters());

                                            if (curMonster.getHp() <= 0) { // if the monster dies
                                                System.out.println("\u001B[32m " + hero.getSymbol() + ", you have won the fight! \u001B[0m");
                                                board.moveMonster(0, curMonster.getJ(), curMonster); // monster respawning
                                                flag = 1;
                                                Parser.parseMusic("mixkit-achievement-bell-600.wav");
                                                curMonster.setHp(curMonster.getLevel() * 100);
                                                // update hero's attributs after winning the fight
                                                hero.setStarting_money(hero.getStarting_money() + curMonster.getLevel() * 100);
                                                hero.setStarting_exp(hero.getStarting_exp() + 2);
                                                hero.setExp(hero.getExp() + 2);
                                                if (hero.getExp() >= (hero.getLevel() * 10)) { // checking if hero level's up
                                                    System.out.println("\u001B[44m " + hero.getSymbol() + " Leveled up! \u001B[0m");
                                                    hero.levelUp(); // level up hero
                                                    Display.displayHeroes(player.getHeroes());
                                                    while (true) { // Avoiding repetition of monsters
                                                        Monsters newMonster = player.getMonster(hero); // getting new monster of the hero's new level
                                                        if (player.getCurMonsters().contains(newMonster)) {
                                                            continue;
                                                        } else {
                                                            player.getCurMonsters().set(i, newMonster);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Please enter a valid choice");
                                            break;
                                        }

                                        break label;
                                    case "q":
                                        // Quit the game
                                        System.out.println("Thanks for playing");
                                        return;
                                }
                            }
                        } while (true);
                        if (newGameFlag == 1 || newGameFlag == 2)
                            break;
                        if(flag == 1)
                            continue;

                        // check if needed to attack heroes before move down
                        if (monster.isHeroNearby(board)) {
                            // get all nearby heroes and choose a random one to attack
                            List<Heroes> nearHeroes = monster.getNearByHeroes(board);
                            Random rand = new Random();
                            int randInt = rand.nextInt(nearHeroes.size());
                            Heroes curHero = nearHeroes.get(randInt);
                            monster.attackHero(curHero);
                            if (hero.getHp() <= 0) { // if hero dies
                                hero.setHp((hero.getLevel() * 100) / 2); // reduce hero's health to half
                                board.moveHero(board.getBoardSize() - 1, hero.getJ(), hero); // hero respawning
                                Display.displayBoard(board);
                                Display.displayLegend(hero.getSymbol());
                                System.out.println("\u001B[42m " + hero.getSymbol() + ", you have moved back to nexus \u001b[0m");
                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                            }
                        } else {
                            if (!board.canMove(monster.getI() + 1, monster.getJ())) {
                                System.out.println("Inaccessible! Please enter a valid choice....");
                            } else {
                                board.moveMonster(monster.getI() + 1, monster.getJ(), monster); // move the monster forward
                                Display.displayBoard(board);
                                Display.displayLegend(hero.getSymbol());
                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                            }
                            System.out.println("\u001B[42m " + monster.getSymbol() + ", you have moved \u001b[0m");
                        }
                        if (monster.getI() == GameConstants.boardSize - 1) { // checking if monster the game
                            System.out.println("\u001B[31m Monsters won the game \u001B[0m");
                            Parser.parseMusic("mixkit-ominous-drums-227.wav");
                            newGameFlag = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Do you want to play another game?\n1. Yes\n2. No\n", 1, 2);
                            break;
                        }
                    }
                    if (newGameFlag == 1 || newGameFlag == 2)
                        break;
                }
                if (newGameFlag == 2)
                    return;
                else {
                    if (GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "1. Continue this game\n2. Start a new game\n", 1, 2) == 1)
                        newGameFlag = 0;
                    else
                        break;
                }
            }
        }
    }
}
