public abstract class Board {
    // Class to create a board/map
    private Cell[][] grid; // creates an instance of the game board/grid

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }


    public int getBoardSize() {
        return GameConstants.boardSize;
    }

    public Board(){
        grid = new Cell[this.getBoardSize()][this.getBoardSize()];
    }

    public abstract void createBoard();
}
