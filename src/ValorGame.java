public class ValorGame extends RpgGame{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void startGame() throws Exception {
        ValorBoard board = new ValorBoard();
        board.createBoard();
        Display.displayBoard(board);
    }
}
