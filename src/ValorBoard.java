import java.util.Random;

public class ValorBoard extends Board{
    Random random = new Random();

    public void createCell(){

    }

    @Override
    public void createBoard() {
//        this.grid = new Cell[GameConstants.boardSize][GameConstants.boardSize];
        for(int i=0;i<GameConstants.boardSize;i++){
            for(int j=0;j<GameConstants.boardSize;j++){
                if((i == 0 || i == GameConstants.boardSize-1) && (j+1)%3 != 0){
                    grid[i][j] = CellFactory.getCell("nexus");
                }
                else if((j+1)%3 == 0){
                    grid[i][j] = CellFactory.getCell("inaccessible");
                }
                else{
                    if(GameFunctions.getRandomBoolean((float) 0.2)){
                        grid[i][j] = CellFactory.getCell("bush");
                    }
                    else if(GameFunctions.getRandomBoolean((float) 0.2)){
                        grid[i][j] = CellFactory.getCell("cave");
                    }
                    else if(GameFunctions.getRandomBoolean((float) 0.2)){
                        grid[i][j] = CellFactory.getCell("koulou");
                    }
                    else if(GameFunctions.getRandomBoolean((float) 0.4)){
                        grid[i][j] = CellFactory.getCell("plain");
                    }
                }

//                grid[i][j].setIsSet(false);
            }
        }
    }

    public void move(int i, int j, Heroes hero){
        // Moves the player position
        if(this.grid[i][j] instanceof isAccessible){
            this.grid[i][j].setHero(hero);
            this.grid[i][j].setIsSet(true);
            this.grid[hero.getI()][hero.getJ()].setIsSet(false);
//            this.grid[this.i][this.j].setPlayer(null);
            hero.setI(i);
            hero.setJ(j);
        }
    }
}
