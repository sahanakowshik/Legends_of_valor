import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class ValorGame extends RpgGame{
    ValorPlayer player;
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
        Market market = new Market();

        String[] data = {"w", "a", "s", "d", "i", "e", "m", "q", "b"};
        String choice;
        while(true) {
            for (Heroes hero : player.getHeroes()) {
                if(Objects.equals(board.grid[hero.getI()][hero.getJ()].getName(), "Nexus")){
                    market.buySell(hero);
                }
                label:
                do {
                    choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is the heroes turn to move: \u001b[0m \nMove(W/A/S/D)\nBackToNexus(B)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
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
                            case "q":
                                // Quit the game
                                System.out.println("Thanks for playing");
                                return;
                        }
                    }
                } while (true);
            }

            for(Monsters monster: player.getCurMonsters()){
                if (!board.canMove(monster.getI() + 1, monster.getJ())) {
                    System.out.println("Inaccessible! Please enter a valid choice....");
                } else {
                    board.moveMonster(monster.getI() + 1, monster.getJ(), monster);
                    Display.displayBoard(board);
                    Display.displayLegend(player.getSymbol());
//                    System.out.println("\u001B[42m " + monster.getName() + "You have moved \u001b[0m");
                    Parser.parseMusic("mixkit-player-jumping-in-a-video-game-2043.wav");
                }
            }
            System.out.println("\u001B[42m Monsters have moved \u001b[0m");
        }
    }
}
