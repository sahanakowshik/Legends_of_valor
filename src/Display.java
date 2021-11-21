import java.util.List;

public class Display {
    // Helper class for display functions
    public static void displayBoard(Board board){
        // Displays the map
        String nexus = "N - N - N";
        String inAccessible = "I - I - I";
        String plain = "P - P - P";
        String bush = "B - B - B";
        String cave = "C - C - C";
        String koulou = "K - K - K";

        for(int i=0;i<GameConstants.boardSize;i++){
            if (i != 0) {
                System.out.println();
            }
            StringBuilder line = new StringBuilder();
            for(int j=0;j<GameConstants.boardSize;j++){
                if (j != 0) {
                    line.append("  ");
                }
                if (board.getGrid()[i][j] instanceof NexusCell) {
                    line.append(nexus);
                } else if (board.getGrid()[i][j] instanceof InaccessibleCell) {
                    line.append(inAccessible);
                } else if (board.getGrid()[i][j] instanceof PlainCell) {
                    line.append(plain);
                } else if (board.getGrid()[i][j] instanceof BushCell) {
                    line.append(bush);
                } else if (board.getGrid()[i][j] instanceof CaveCell) {
                    line.append(cave);
                } else if (board.getGrid()[i][j] instanceof KoulouCell) {
                    line.append(koulou);
                }
            }
            System.out.println(line);

            // print the content
            for(int j=0;j<GameConstants.boardSize;j++) {
                String roleContent = "";
                if (j != 0) {
                    System.out.print("  ");
                }
                if(board.getGrid()[i][j].getIsHeroSet()){
                    if(board.getGrid()[i][j] instanceof isAccessible){
                        // check if monster at the same cell
                        if (!board.getGrid()[i][j].getIsMonsterSet()) {
                            roleContent += board.getGrid()[i][j].getHero().getSymbol();
                            roleContent += "   ";
                        } else {
                            roleContent += board.getGrid()[i][j].getHero().getSymbol();
                            roleContent += " ";
                            roleContent += board.getGrid()[i][j].getMonster().getSymbol();
                        }
                        System.out.print("|" + "\u001b[33m " + roleContent + " \u001b[0m" + "|");
                    }
                }
                else if(board.getGrid()[i][j].getIsMonsterSet()){
                    if(board.getGrid()[i][j] instanceof isAccessible){

                        System.out.print("|" + "\u001b[33m    " + board.getGrid()[i][j].getMonster().getSymbol() + " \u001b[0m" + "|");
                    }
                }
                else
                    try {
                        System.out.print("|" + board.getGrid()[i][j].getSymbol() + "|");
                    } catch (Exception e) {
                        System.out.println(e);
                        e.printStackTrace();
                    }
            }
            System.out.println();

            // print the bottom
            System.out.println(line);
        }
    }

    public static void displayHeroes(List<Heroes> heroes){
        // Displays the list of heroes
        System.out.println("Id      Name                      Level   HP   mana strength agility dexterity starting_money starting_experience  Defense");
        for(int i=0;i<heroes.size();i++){
            heroes.get(i).displayHero(i);
        }
    }

    public static void displayMonsters(List<Monsters> monsters){
        // Displays the list of monsters
        System.out.println("Id      Name                  Level   Hp   Damage   Defense Dodge_Chance");
        for(int i=0;i<monsters.size();i++){
            monsters.get(i).displayMonster(i);
        }
    }

    public static void displayLegend(String symbol){
        // Displays the legend
        System.out.println("---------Legend---------");
        System.out.println("N       = Nexus");
        System.out.println("I       = Blocked space");
        System.out.println("<Blank> = Accessible space");
//        System.out.println(symbol + "       = Current player");
    }

    public static void displayArmory(List<Armory> armories){
        // Displays the list of armories
        String[] words = Armory.allLines.get(0).split("/");
        String line = String.join("   ", words);
        System.out.println("Id   " + line + "   Equip");
        for(Armory armory: armories){
            System.out.format("%d   %15s   %4d   %2d   %4d   %3s", armory.getId(), armory.getName(), armory.getCost(), armory.getReq_level(), armory.getDamage_reduction(), armory.getEquip());
            System.out.println();
        }
    }

    public static void displayWeaponry(List<Weaponry> weapons){
        // Displays the list of weapons
        String[] words = Weaponry.allLines.get(0).split("/");
        String line = String.join("   ", words);
        System.out.println("Id   " + line + "   Equip");
        for(Weaponry weapon: weapons){
            System.out.format("%d   %7s   %4d   %2d   %4d   %2d   %3s", weapon.getId(), weapon.getName(), weapon.getCost(), weapon.getLevel(), weapon.getDamage(), weapon.getReq_hands(), weapon.getEquip());
            System.out.println();
        }
    }

    public static void displayPotions(List<Potions> potions){
        // Displays the list of potions
        String[] words = Potions.allLines.get(0).split("/");
        String line = String.join("   ", words);
        System.out.println("Id   " + line);
        for(Potions potion: potions){
            System.out.format("%d   %15s   %4d   %2d   %3d   %30s", potion.getId(), potion.getName(), potion.getCost(), potion.getReq_level(), potion.getAtt_increase(), potion.getAtt_affected());
            System.out.println();
        }
    }

    public static void displaySpells(List<Spell> spells){
        // Displays the list of spells
        String[] words = Spell.allLines.get(0).split("/");
        String line = String.join("   ", words);
        System.out.println("Id   " + line);
        for(Spell spell: spells){
            System.out.format("%d   %16s   %4d   %2d   %4d   %4d", spell.getId(), spell.getName(), spell.getCost(), spell.getReq_level(), spell.getDamage(), spell.getMana_cost());
            System.out.println();
        }
    }
}
