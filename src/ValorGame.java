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

        String[] data = {"w", "a", "s", "d", "i", "e", "m", "q", "b", "t"};
        String choice;
        while(true) {
            for (Heroes hero : player.getHeroes()) {
                if(Objects.equals(board.grid[hero.getI()][hero.getJ()].getName(), "Nexus")){
                    market.buySell(hero);
                }
                label:
                do {
                    choice = GameFunctions.safeScanString(new Scanner(System.in), "\u001B[32m It is the heroes turn to move: \u001b[0m \nMove(W/A/S/D)\nBackToNexus(B)\nTeleport(T)\nCheck player Info(I)\nCheck weapons Inventory (E)\nShow map (M)\nQuit (Q)\n");
                    choice = choice.toLowerCase();
                    if (!Arrays.asList(data).contains(choice)) {
                        System.out.println("Please enter a valid choice....");
                    } else {
                        switch (choice) {
                            case "w": // Move up
                                if (!board.canMove(hero.getI() - 1, hero.getJ()) || board.grid[hero.getI() - 1][hero.getJ()].getIsHeroSet() == true) {
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
                                        for (int i = rowCheck; i < board.getBoardSize(); i++) {
                                            if (board.grid[i][0].getIsMonsterSet() || board.grid[i][1].getIsMonsterSet()) {
                                                monsterLoc = i;
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
                                        for (int i = rowCheck; i < board.getBoardSize(); i++) {
                                            if (board.grid[i][3].getIsMonsterSet() || board.grid[i][4].getIsMonsterSet()) {
                                                monsterLoc = i;
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
                                        for (int i = rowCheck; i < board.getBoardSize(); i++) {
                                            if (board.grid[i][6].getIsMonsterSet() || board.grid[i][7].getIsMonsterSet()) {
                                                monsterLoc = i;
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
