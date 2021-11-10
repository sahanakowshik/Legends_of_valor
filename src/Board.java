public abstract class Board {
    // Class to create a board/map
    public Cell[][] grid;

    public int getBoardSize() {
        return GameConstants.boardSize;
    }

//    public void setBoardSize(int boardSize) {
//        GameConstants.boardSize = boardSize;
//    }

    public Board(){
        grid = new Cell[this.getBoardSize()][this.getBoardSize()];
    }

//    public Board(int boardSize){
//        setBoardSize(boardSize);
//        board = new Cell[this.getBoardSize()][this.getBoardSize()];
//    }
    public abstract void createBoard();
}
