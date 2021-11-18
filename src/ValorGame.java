import java.util.*;

public class ValorGame extends RpgGame{
    ValorPlayer player;
    private Market market;
    private int round;

    @Override
    public String getName() {
        return null;
    }


    @Override
    public void startGame() throws Exception {
        player = new ValorPlayer();
        player.setName(GameFunctions.safeScanString(new Scanner(System.in), "Please enter your name:\n"));
        player.setnHero(3);
//        player.setnHero(GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the number of heroes (1-3):\n", 1, 3));
//        player.setSymbol(GameFunctions.safeScanString(new Scanner(System.in), "Please enter your symbol:\n"));

        player.setHeroes();
        player.addHeroes();
        player.getHeroes().get(0).setSymbol("H1");
        player.getHeroes().get(1).setSymbol("H2");
        player.getHeroes().get(2).setSymbol("H3");

        player.setnMonster(3);
        player.createMonsters();
        player.getMonsters();
        player.getCurMonsters().get(0).setSymbol("M1");
        player.getCurMonsters().get(1).setSymbol("M2");
        player.getCurMonsters().get(2).setSymbol("M3");


        ValorBoard board = new ValorBoard();
        board.createBoard(); // Creates a map
        board.addPlayer(player);
        board.addMonster(player);
        Display.displayBoard(board);
        market = new Market();
        market.createMarketList();
        round = 0;
        String[] data = {"w", "a", "s", "d", "i", "e", "m", "q", "b", "f", "t"};

        String choice;
        while(true) {
            round++;
            for(int i=0;i<player.getHeroes().size();i++){
//            for (Heroes hero : player.getHeroes()) {
                Heroes hero = player.getHeroes().get(i);
                Monsters monster = player.getCurMonsters().get(i);
                hero.setHp((int) (hero.getHp() * 1.1));
                hero.setMana((int) (hero.getMana() * 1.1));
                if(Objects.equals(board.grid[hero.getI()][hero.getJ()].getName(), "Nexus")){
                    market.buySell(hero);
                }
                else if(Objects.equals(board.grid[hero.getI()][hero.getJ()].getName(), "Bush")){
                    hero.setDexterity((int) (hero.getDexterity() * 1.1));
                }
                else if(Objects.equals(board.grid[hero.getI()][hero.getJ()].getName(), "Cave")){
                    hero.setAgility((int) (hero.getAgility() * 1.1));
                }
                else if(Objects.equals(board.grid[hero.getI()][hero.getJ()].getName(), "Koulou")){
                    hero.setStrength((int) (hero.getStrength() * 1.1));
                }
                label:
                do {
                    boolean isAttack = hero.isMonsterNearby(board);
                    if (isAttack) {
                        choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m " + hero.getName() + ", you run into a monster: \u001b[0m \nFight(F)\nMove(W/A/S/D)\nBackToNexus(B)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                    } else {
                        choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is the heroes turn to move: \u001b[0m \nMove(W/A/S/D)\nBackToNexus(B)\nTeleport(T)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                    }
//                    choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is the heroes turn to move: \u001b[0m \nMove(W/A/S/D)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                    choice = choice.toLowerCase();
                    if (!Arrays.asList(data).contains(choice)) {
                        System.out.println("Please enter a valid choice....");
                    } else {
                        switch (choice) {
                            case "w": // Move up
                                if (!board.canMove(hero.getI() - 1, hero.getJ()) || board.grid[hero.getI() - 1][hero.getJ()].getIsHeroSet() == true) {
                                    System.out.println("Inaccessible! Please enter a valid choice....");
                                } else {
                                    if(board.grid[hero.getI()][hero.getJ()].getIsMonsterSet() || board.grid[hero.getI()][(int) (hero.getJ() - Math.pow(-1, (hero.getJ()+1)%3))].getIsMonsterSet()){
                                        System.out.println("Monster in sight, you cannot move ahead!!\nPlease enter a different option");
                                    }
                                    else {
                                        board.moveHero(hero.getI() - 1, hero.getJ(), hero);
                                        Display.displayBoard(board);
                                        Display.displayLegend(hero.getSymbol());
                                        System.out.println("\u001B[42m " + hero.getName() + ", You have moved \u001b[0m");
                                        Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                        break label;
                                    }
                                }
                                break;
                            case "s": // Move down
                                if (!board.canMove(hero.getI() + 1, hero.getJ()) || board.grid[hero.getI() + 1][hero.getJ()].getIsHeroSet() == true) {
                                    System.out.println("Inaccessible! Please enter a valid choice....");
                                } else {
                                    board.moveHero(hero.getI() + 1, hero.getJ(), hero);
                                    Display.displayBoard(board);
                                    Display.displayLegend(player.getSymbol());
                                    System.out.println("\u001B[42m " + hero.getName() + "You have moved \u001b[0m");
                                    Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                    break label;
                                }
                                break;
                            case "a": // Move left
                                if (!board.canMove(hero.getI(), hero.getJ() - 1) || board.grid[hero.getI()][hero.getJ() - 1].getIsHeroSet() == true) {
                                    System.out.println("Inaccessible! Please enter a valid choice....");
                                } else {
                                    board.moveHero(hero.getI(), hero.getJ() - 1, hero);
                                    Display.displayBoard(board);
                                    Display.displayLegend(player.getSymbol());
                                    System.out.println("\u001B[42m " + hero.getName() + "You have moved \u001b[0m");
                                    Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                    break label;
                                }
                                break;
                            case "d": // Move right
                                if (!board.canMove(hero.getI(), hero.getJ() + 1) || board.grid[hero.getI()][hero.getJ() + 1].getIsHeroSet() == true) {
                                    System.out.println("Inaccessible! Please enter a valid choice....");
                                } else {
                                    board.moveHero(hero.getI(), hero.getJ() + 1, hero);
                                    Display.displayBoard(board);
                                    Display.displayLegend(player.getSymbol());
                                    System.out.println("\u001B[42m " + hero.getName() + "You have moved \u001b[0m");
                                    Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                    break label;
                                }
                                break;
                            case "b": // Go back to nexus
                                board.moveHero(board.getBoardSize() - 1, hero.getJ(), hero);
                                Display.displayBoard(board);
                                Display.displayLegend(player.getSymbol());
                                System.out.println("\u001B[42m " + hero.getName() + "You have moved \u001b[0m");
                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                break label;
                                case "t":
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
                                String laneChoice = GameFunctions.safeScanString(new Scanner(System.in), laneOptions);
                                while (!Arrays.asList(laneData).contains(laneChoice)) {
                                    System.out.println("Please enter a valid choice....");
                                    laneChoice = GameFunctions.safeScanString(new Scanner(System.in), laneOptions);
                                }
                                
                                int rowCheck = hero.getI();
                                int teleRow = -1;
                                int teleCol = -1;
                                int monsterLoc = -1;
                                System.out.println("Lane Choice:" + laneChoice);
                                switch(laneChoice) {
                                    case "1":
                                        //Check if there are any monsters
                                        for (int k = rowCheck; k < board.getBoardSize(); k++) {
                                            if (board.grid[k][0].getIsMonsterSet() || board.grid[k][1].getIsMonsterSet()) {
                                                monsterLoc = k;
                                            }

                                            if (monsterLoc != -1) {
                                                break;
                                            }
                                        }

                                        if (monsterLoc == -1) {
                                            monsterLoc = rowCheck - 1;
                                        }

                                        //Place the teleporting hero in front of any monster but not where a hero is
                                        for (int j = monsterLoc + 1; j < board.getBoardSize(); j++) {
                                            if (!board.grid[j][0].getIsHeroSet()) {
                                                teleRow = j;
                                                teleCol = 0;
                                                break;
                                            }

                                            if (!board.grid[j][1].getIsHeroSet()) {
                                                teleRow = j;
                                                teleCol = 1;
                                                break;
                                            }
                                        }
                                        
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
                                            if (board.grid[k][3].getIsMonsterSet() || board.grid[k][4].getIsMonsterSet()) {
                                                monsterLoc = k;
                                            }

                                            if (monsterLoc != -1) {
                                                break;
                                            }
                                        }

                                        if (monsterLoc == -1) {
                                            monsterLoc = rowCheck - 1;
                                        }

                                        //Place the teleporting hero in front of any monster but not where a hero is
                                        for (int j = monsterLoc + 1; j < board.getBoardSize(); j++) {
                                            if (!board.grid[j][3].getIsHeroSet()) {
                                                teleRow = j;
                                                teleCol = 3;
                                                break;
                                            }

                                            if (!board.grid[j][4].getIsHeroSet()) {
                                                teleRow = j;
                                                teleCol = 4;
                                                break;
                                            }
                                        }
                                        
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
                                            if (board.grid[k][6].getIsMonsterSet() || board.grid[k][7].getIsMonsterSet()) {
                                                monsterLoc = k;
                                            }

                                            if (monsterLoc != -1) {
                                                break;
                                            }
                                        }

                                        if (monsterLoc == -1) {
                                            monsterLoc = rowCheck - 1;
                                        }

                                        //Place the teleporting hero in front of any monster but not where a hero is
                                        for (int j = monsterLoc + 1; j < board.getBoardSize(); j++) {
                                            if (!board.grid[j][6].getIsHeroSet()) {
                                                teleRow = j;
                                                teleCol = 6;
                                                break;
                                            }

                                            if (!board.grid[j][7].getIsHeroSet()) {
                                                teleRow = j;
                                                teleCol = 7;
                                                break;
                                            }
                                        }

                                        if (teleRow == -1 && teleCol == -1) {
                                            System.out.println("You cannot teleport!");
                                        }
                                        else {
                                            board.moveHero(teleRow, teleCol, hero);
                                        }
                                        break;
                                }
                                Display.displayBoard(board);
                                Display.displayLegend(player.getSymbol());
                                System.out.println("\u001B[42m " + hero.getName() + "You have teleported \u001b[0m");
                                Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                break label;
                            case "e": // Show inventory of all heroes
                                System.out.println("\u001B[36m " + hero.getName() + " Inventory \u001b[0m");
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
                                Display.displayLegend(player.getSymbol());
                                break;
                            case "f":
                                System.out.println("Fight begins!");
                                Display.displayBoard(board);
                                List<Monsters> nearMonsters = hero.getNearByMonsters(board);
                                Random rand = new Random();
                                int randInt = rand.nextInt(nearMonsters.size());
                                Monsters curMonster = nearMonsters.get(randInt);
//                                Monsters curMonster;
                                int fchoice = hero.fight(curMonster, market);
                                if(fchoice == 1)
                                    return;
                                System.out.println("Monsters info");
                                Display.displayMonsters(player.getCurMonsters());
                                if(curMonster.getHp() <= 0){
                                    System.out.println(hero.getName() + " You have won the fight!");
                                    board.moveMonster(0, monster.getJ(), monster);
                                    Parser.parseMusic("mixkit-achievement-bell-600.wav");
                                    curMonster.setHp(curMonster.getLevel() * 100);
                                    hero.setStarting_money(hero.getStarting_money() + curMonster.getLevel() * 100);
                                    hero.setStarting_exp(hero.getStarting_exp() + 2);
                                    hero.setExp(hero.getExp() + 2);
                                }
                                break label;
                            case "q":
                                // Quit the game
                                System.out.println("Thanks for playing");
                                return;
                        }
                    }
                } while (true);
//            }

//            for(Monsters monster: player.getCurMonsters()){
                // check if needed to attack heroes before move down
                if (monster.isHeroNearby(board)) {
                    // get all nearby heroes and choose a random one to attack
                    List<Heroes> nearHeroes = monster.getNearByHeroes(board);
                    Random rand = new Random();
                    int randInt = rand.nextInt(nearHeroes.size());
                    Heroes curHero = nearHeroes.get(randInt);
                    monster.attackHero(curHero);
                    if(hero.getHp() <= 0){
                        hero.setHp((hero.getLevel() * 100) / 2);
                        board.moveHero(board.getBoardSize() - 1, hero.getJ(), hero);
                        Display.displayBoard(board);
                        Display.displayLegend(player.getSymbol());
                        System.out.println("\u001B[42m " + hero.getName() + "You have moved back to nexus \u001b[0m");
                        Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                    }
                } else {
                    if (!board.canMove(monster.getI() + 1, monster.getJ())) {
                        System.out.println("Inaccessible! Please enter a valid choice....");
                    } else {
                        board.moveMonster(monster.getI() + 1, monster.getJ(), monster);
                        Display.displayBoard(board);
                        Display.displayLegend(player.getSymbol());
//                    System.out.println("\u001B[42m " + monster.getName() + "You have moved \u001b[0m");
                        Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                    }
                    System.out.println("\u001B[42m Monsters have moved \u001b[0m");
                }
            }
        }
    }
}
