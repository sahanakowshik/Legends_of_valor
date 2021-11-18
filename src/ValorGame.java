import java.util.*;

public class ValorGame extends RpgGame{
    ValorPlayer player;
    private Market market;
    private int newGameFlag;

    @Override
    public String getName() {
        return null;
    }

    public void updateAttributes(ValorBoard board, Heroes hero){
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
    }


    @Override
    public void startGame() throws Exception {
        while(true) {
            newGameFlag = 0;
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
            String[] data = {"w", "a", "s", "d", "i", "e", "m", "q", "b", "f"};

            String choice;
            while (true) {
                for (int i = 0; i < player.getHeroes().size(); i++) {
//            for (Heroes hero : player.getHeroes()) {
                    Heroes hero = player.getHeroes().get(i);
                    Monsters monster = player.getCurMonsters().get(i);
                    hero.setHp((int) (hero.getHp() * 1.1));
                    hero.setMana((int) (hero.getMana() * 1.1));
                    if (Objects.equals(board.grid[hero.getI()][hero.getJ()].getName(), "Nexus")) {
                        market.buySell(hero);
                    }

                    label:
                    do {
                        boolean isAttack = hero.isMonsterNearby(board);
                        if (isAttack) {
                            choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m " + hero.getSymbol() + ", you run into a monster: \u001b[0m \nFight(F)\nMove(W/A/S/D)\nBackToNexus(B)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                        } else {
                            choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is " + hero.getSymbol() + " turn to move: \u001b[0m \nMove(W/A/S/D)\nBackToNexus(B)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                        }
//                    choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is the heroes turn to move: \u001b[0m \nMove(W/A/S/D)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                        choice = choice.toLowerCase();
                        if (!Arrays.asList(data).contains(choice)) {
                            System.out.println("Please enter a valid choice....");
                        } else {
                            switch (choice) {
                                case "w": // Move up
                                    if (!board.canMove(hero.getI() - 1, hero.getJ())) {
                                        System.out.println("Inaccessible! Please enter a valid choice....");
                                    } else {
                                        if (board.grid[hero.getI()][hero.getJ()].getIsMonsterSet() || board.grid[hero.getI()][(int) (hero.getJ() - Math.pow(-1, (hero.getJ() + 1) % 3))].getIsMonsterSet()) {
                                            System.out.println("Monster in sight, you cannot move ahead!!\nPlease enter a different option");
                                        } else {
                                            board.moveHero(hero.getI() - 1, hero.getJ(), hero);
                                            updateAttributes(board, hero);
                                            Display.displayBoard(board);
                                            Display.displayLegend(hero.getSymbol());
                                            System.out.println("\u001B[42m " + hero.getSymbol() + ", You have moved \u001b[0m");
                                            Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                            break label;
                                        }
                                    }
                                    break;
                                case "s": // Move down
                                    if (!board.canMove(hero.getI() + 1, hero.getJ())) {
                                        System.out.println("Inaccessible! Please enter a valid choice....");
                                    } else {
                                        board.moveHero(hero.getI() + 1, hero.getJ(), hero);
                                        updateAttributes(board, hero);
                                        Display.displayBoard(board);
                                        Display.displayLegend(player.getSymbol());
                                        System.out.println("\u001B[42m " + hero.getSymbol() + "You have moved \u001b[0m");
                                        Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                        break label;
                                    }
                                    break;
                                case "a": // Move left
                                    if (!board.canMove(hero.getI(), hero.getJ() - 1)) {
                                        System.out.println("Inaccessible! Please enter a valid choice....");
                                    } else {
                                        board.moveHero(hero.getI(), hero.getJ() - 1, hero);
                                        updateAttributes(board, hero);
                                        Display.displayBoard(board);
                                        Display.displayLegend(player.getSymbol());
                                        System.out.println("\u001B[42m " + hero.getSymbol() + "You have moved \u001b[0m");
                                        Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                        break label;
                                    }
                                    break;
                                case "d": // Move right
                                    if (!board.canMove(hero.getI(), hero.getJ() + 1)) {
                                        System.out.println("Inaccessible! Please enter a valid choice....");
                                    } else {
                                        board.moveHero(hero.getI(), hero.getJ() + 1, hero);
                                        updateAttributes(board, hero);
                                        Display.displayBoard(board);
                                        Display.displayLegend(player.getSymbol());
                                        System.out.println("\u001B[42m " + hero.getSymbol() + "You have moved \u001b[0m");
                                        Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                        break label;
                                    }
                                    break;
                                case "b": // Go back to nexus
                                    board.moveHero(board.getBoardSize() - 1, hero.getJ(), hero);
                                    updateAttributes(board, hero);
                                    Display.displayBoard(board);
                                    Display.displayLegend(player.getSymbol());
                                    System.out.println("\u001B[42m " + hero.getSymbol() + "You have moved \u001b[0m");
                                    Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
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
                                    Display.displayLegend(player.getSymbol());
                                    break;
                                case "f":
                                    System.out.println("Fight begins!");
                                    updateAttributes(board, hero);
                                    Display.displayBoard(board);
                                    List<Monsters> nearMonsters = hero.getNearByMonsters(board);
                                    Random rand = new Random();
                                    int randInt = rand.nextInt(nearMonsters.size());
                                    Monsters curMonster = nearMonsters.get(randInt);
//                                Monsters curMonster;
                                    int fchoice = hero.fight(curMonster, market);
                                    if (fchoice == 1)
                                        return;
                                    System.out.println("Monsters info");
                                    Display.displayMonsters(player.getCurMonsters());
                                    if (curMonster.getHp() <= 0) {
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
                    if (hero.getI() == 0) {
                        System.out.println("Heroes won the game");
                        newGameFlag = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Do you want to play another game?\n1. Yes\n2. No", 1, 2);
                        break;
                    }
//            for(Monsters monster: player.getCurMonsters()){
                    // check if needed to attack heroes before move down
                    if (monster.isHeroNearby(board)) {
                        // get all nearby heroes and choose a random one to attack
                        List<Heroes> nearHeroes = monster.getNearByHeroes(board);
                        Random rand = new Random();
                        int randInt = rand.nextInt(nearHeroes.size());
                        Heroes curHero = nearHeroes.get(randInt);
                        monster.attackHero(curHero);
                        if (hero.getHp() <= 0) {
                            hero.setHp((hero.getLevel() * 100) / 2);
                            board.moveHero(board.getBoardSize() - 1, hero.getJ(), hero);
                            Display.displayBoard(board);
                            Display.displayLegend(player.getSymbol());
                            System.out.println("\u001B[42m " + hero.getSymbol() + "You have moved back to nexus \u001b[0m");
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
                    if (monster.getI() == GameConstants.boardSize - 1) {
                        System.out.println("Monsters won the game");
                        newGameFlag = GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Do you want to play another game?\n1. Yes\n2. No", 1, 2);
                        break;
                    }
                }
                if(newGameFlag == 1 || newGameFlag == 2)
                    break;
            }
            if(newGameFlag == 2)
                return;
        }
    }
}
