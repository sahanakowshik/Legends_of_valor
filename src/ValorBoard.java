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
                    else {
                        grid[i][j] = CellFactory.getCell("plain");
                    }
                }

                grid[i][j].setHeroSet(false);
            }
        }
    }

    public void addPlayer(ValorPlayer player){
        this.grid[GameConstants.boardSize-1][0].setHero(player.getHeroes().get(0));
        player.getHeroes().get(0).setI(GameConstants.boardSize-1);
        player.getHeroes().get(0).setJ(0);
        this.grid[GameConstants.boardSize-1][0].setHeroSet(true);
        this.grid[GameConstants.boardSize-1][3].setHero(player.getHeroes().get(1));
        player.getHeroes().get(1).setI(GameConstants.boardSize-1);
        player.getHeroes().get(1).setJ(3);
        this.grid[GameConstants.boardSize-1][3].setHeroSet(true);
        this.grid[GameConstants.boardSize-1][6].setHero(player.getHeroes().get(2));
        player.getHeroes().get(2).setI(GameConstants.boardSize-1);
        player.getHeroes().get(2).setJ(6);
        this.grid[GameConstants.boardSize-1][6].setHeroSet(true);
    }

    public void addMonster(ValorPlayer player){
        this.grid[0][1].setMonster(player.getCurMonsters().get(0));
        player.getCurMonsters().get(0).setI(0);
        player.getCurMonsters().get(0).setJ(1);
        this.grid[0][1].setMonsterSet(true);
        this.grid[0][4].setMonster(player.getCurMonsters().get(1));
        player.getCurMonsters().get(1).setI(0);
        player.getCurMonsters().get(1).setJ(4);
        this.grid[0][4].setMonsterSet(true);
        this.grid[0][7].setMonster(player.getCurMonsters().get(2));
        player.getCurMonsters().get(2).setI(0);
        player.getCurMonsters().get(2).setJ(7);
        this.grid[0][7].setMonsterSet(true);
    }

    public boolean canMove(int i, int j){
        // Checks if the player can move to the given position
        if(i < 0 || j < 0){
            System.out.println("Out of bounds");
            return false;
        }
        if(i >= GameConstants.boardSize || j >= GameConstants.boardSize) {
            System.out.println("Out of bounds");
            return false;
        }
        if(this.grid[i][j] instanceof isAccessible)
            return true;
        else
            return false;
    }


    public void moveHero(int i, int j, Heroes hero){
        // Moves the player position
        if(this.grid[i][j] instanceof isAccessible){
            this.grid[i][j].setHero(hero);
            this.grid[i][j].setHeroSet(true);
            this.grid[hero.getI()][hero.getJ()].setHeroSet(false);
//            this.grid[this.i][this.j].setPlayer(null);
            hero.setI(i);
            hero.setJ(j);
        }
    }

    public void moveMonster(int i, int j, Monsters monster){
        // Moves the player position
        if(this.grid[i][j] instanceof isAccessible){
            this.grid[i][j].setMonster(monster);
            this.grid[i][j].setMonsterSet(true);
            this.grid[monster.getI()][monster.getJ()].setMonsterSet(false);
//            this.grid[this.i][this.j].setPlayer(null);
            monster.setI(i);
            monster.setJ(j);
        }
    }
}
