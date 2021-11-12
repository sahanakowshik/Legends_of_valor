import java.util.Scanner;

public class ValorGame extends RpgGame{
    ValorPlayer player;
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void startGame() throws Exception {
//        ValorBoard board = new ValorBoard();
//        board.createBoard();
////        for(int i=0;i<GameConstants.boardSize;i++){
////            for(int j=0;j<GameConstants.boardSize;j++){
////                System.out.print(board.grid[i][j] + " ");
////            }
////            System.out.println();
////        }
//        Display.displayBoard(board);
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
//        player.setnHero(GameFunctions.safeScanIntWithLimit(new Scanner(System.in), "Please enter the number of heroes (1-3):\n", 1, 3));
//        player.setSymbol(GameFunctions.safeScanString(new Scanner(System.in), "Please enter your symbol:\n"));

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

    }
}
