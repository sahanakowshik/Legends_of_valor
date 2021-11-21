public class ValorBoard extends Board{
    // Class to create a Valor board

    @Override
    public void createBoard() {
        // creates a board by getting cell instances from cellFactory
        for(int i=0;i<GameConstants.boardSize;i++){
            for(int j=0;j<GameConstants.boardSize;j++){
                if((i == 0 || i == GameConstants.boardSize-1) && (j+1)%3 != 0){ // creates nexus cells
                    this.getGrid()[i][j] = CellFactory.getCell("nexus");
                }
                else if((j+1)%3 == 0){ // creates inaccessible cells
                    this.getGrid()[i][j] = CellFactory.getCell("inaccessible");
                }
                else{
                    if(GameFunctions.getRandomBoolean((float) 0.2)){ // creates a bush cell with 20% probability
                        this.getGrid()[i][j] = CellFactory.getCell("bush");
                    }
                    else if(GameFunctions.getRandomBoolean((float) 0.2)){ // creates a cave cell with 20% probability
                        this.getGrid()[i][j] = CellFactory.getCell("cave");
                    }
                    else if(GameFunctions.getRandomBoolean((float) 0.2)){ // creates a koulou cell with 20% probability
                        this.getGrid()[i][j] = CellFactory.getCell("koulou");
                    }
                    else { // creates a plain cell otherwise
                        this.getGrid()[i][j] = CellFactory.getCell("plain");
                    }
                }

                this.getGrid()[i][j].setHeroSet(false); // sets isHeroSet flag to false indicating there are no heroes in the cells at the beginning
            }
        }
    }

    public void addPlayer(ValorPlayer player){
        // adds heroes to the game board
        this.getGrid()[GameConstants.boardSize-1][0].setHero(player.getHeroes().get(0)); // hero 1
        player.getHeroes().get(0).setI(GameConstants.boardSize-1); // sets ith position
        player.getHeroes().get(0).setJ(0); // sets jth position
        this.getGrid()[GameConstants.boardSize-1][0].setHeroSet(true); // sets isHeroSet flag to true
        this.getGrid()[GameConstants.boardSize-1][3].setHero(player.getHeroes().get(1)); // hero 2
        player.getHeroes().get(1).setI(GameConstants.boardSize-1); // sets ith position
        player.getHeroes().get(1).setJ(3); // sets jth position
        this.getGrid()[GameConstants.boardSize-1][3].setHeroSet(true); // sets isHeroSet flag to true
        this.getGrid()[GameConstants.boardSize-1][6].setHero(player.getHeroes().get(2)); // hero 3
        player.getHeroes().get(2).setI(GameConstants.boardSize-1); // sets ith position
        player.getHeroes().get(2).setJ(6); // sets jth position
        this.getGrid()[GameConstants.boardSize-1][6].setHeroSet(true); // sets isHeroSet flag to true
    }

    public void addMonster(ValorPlayer player){
        this.getGrid()[0][1].setMonster(player.getCurMonsters().get(0));
        player.getCurMonsters().get(0).setI(0); // sets ith position
        player.getCurMonsters().get(0).setJ(1); // sets jth position
        this.getGrid()[0][1].setMonsterSet(true); // sets isMonsterSet flag to true
        this.getGrid()[0][4].setMonster(player.getCurMonsters().get(1));
        player.getCurMonsters().get(1).setI(0); // sets ith position
        player.getCurMonsters().get(1).setJ(4); // sets jth position
        this.getGrid()[0][4].setMonsterSet(true); // sets isMonsterSet flag to true
        this.getGrid()[0][7].setMonster(player.getCurMonsters().get(2));
        player.getCurMonsters().get(2).setI(0); // sets ith position
        player.getCurMonsters().get(2).setJ(7); // sets jth position
        this.getGrid()[0][7].setMonsterSet(true); // sets isMonsterSet flag to true
    }

    public boolean canMove(int i, int j){
        // Checks if the hero can move to the given position
        if(i < 0 || j < 0){
            System.out.println("Out of bounds");
            return false;
        }
        if(i >= GameConstants.boardSize || j >= GameConstants.boardSize) {
            System.out.println("Out of bounds");
            return false;
        }
        if(this.getGrid()[i][j] instanceof isAccessible) // checks if the cell is accessible
            return true;
        else
            return false;
    }


    public void moveHero(int i, int j, Heroes hero){
        // Moves the hero to the given position
        if(this.getGrid()[i][j] instanceof isAccessible){ // checks if the cell is accessible
            this.getGrid()[i][j].setHero(hero);
            this.getGrid()[i][j].setHeroSet(true); // sets isHeroSet flag to true
            this.getGrid()[hero.getI()][hero.getJ()].setHeroSet(false); // resets the current isHeroSet flag to false
            hero.setI(i); // sets ith position
            hero.setJ(j); // sets jth position
        }
    }

    public void moveMonster(int i, int j, Monsters monster){
        // Moves the monster to the current position
        if(this.getGrid()[i][j] instanceof isAccessible){ // checks if the cell is accessible
            this.getGrid()[i][j].setMonster(monster);
            this.getGrid()[i][j].setMonsterSet(true); // sets isMonsterSet flag to true
            this.getGrid()[monster.getI()][monster.getJ()].setMonsterSet(false); // resets the current isMonsterSet flag to false
            monster.setI(i); // sets ith position
            monster.setJ(j); // sets jth position
        }
    }
}
