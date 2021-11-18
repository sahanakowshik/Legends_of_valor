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
        String[] data = {"w", "a", "s", "d", "i", "e", "m", "q", "b", "f"};

        String choice;
        while(true) {
            round++;

            for (Heroes hero : player.getHeroes()) {
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
                        choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is the heroes turn to move: \u001b[0m \nMove(W/A/S/D)\nBackToNexus(B)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
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
                                    board.moveHero(hero.getI() - 1, hero.getJ(), hero);
                                    Display.displayBoard(board);
                                    Display.displayLegend(hero.getSymbol());
                                    System.out.println("\u001B[42m " + hero.getName() + ", You have moved \u001b[0m");
                                    Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                                    break label;
                                }
                                break;
                            case "s": // Move down
                                if (!board.canMove(hero.getI() + 1, hero.getJ())) {
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
                                if (!board.canMove(hero.getI(), hero.getJ() - 1)) {
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
                                if (!board.canMove(hero.getI(), hero.getJ() + 1)) {
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
//                                Monsters curMonster;
//                                hero.fight(curMonster);
                                break label;
                            case "q":
                                // Quit the game
                                System.out.println("Thanks for playing");
                                return;
                        }
                    }
                } while (true);
            }

            for(Monsters monster: player.getCurMonsters()){
                // check if needed to attack heroes before move down
                if (monster.isHeroNearby(board)) {
                    // get all nearby heroes and choose a random one to attack
                    List<Heroes> nearHeroes = monster.getNearByHeroes(board);
                    Random rand = new Random();
                    int randInt = rand.nextInt(nearHeroes.size());
                    Heroes curHero = nearHeroes.get(randInt);
                    monster.attachHero(curHero);
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
