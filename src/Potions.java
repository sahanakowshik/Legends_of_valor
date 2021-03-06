import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Potions extends MarketItems implements isBuyableSellable, isDrinkable{
    // Class to store attributes for the potions
    public static List<String> allLines;
    private int id;
    private String Name;
    private int cost;
    private int req_level;
    private int att_increase;
    private String att_affected;

    public static List<String> getAllLines() {
        return allLines;
    }

    // Getters and setters for all the potion attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getReq_level() {
        return req_level;
    }

    public void setReq_level(int req_level) {
        this.req_level = req_level;
    }

    public int getAtt_increase() {
        return att_increase;
    }

    public void setAtt_increase(int att_increase) {
        this.att_increase = att_increase;
    }

    public String getAtt_affected() {
        return att_affected;
    }

    public void setAtt_affected(String att_affected) {
        this.att_affected = att_affected;
    }

    public String getType() {
        return "Potions";
    }

    @Override
    public void display(){
        // Calls the displayPotions method for displaying the list of potions
        Display.displayPotions(potions);
    }

    @Override
    public void createList(){
        // Creates a list of potions
        List<String> list = this.getList();
        potions = new ArrayList<>();
        for(String str: list){
            String[] words = str.split("\\s+");
            Potions potion = new Potions();
            potion.setId(Integer.parseInt(words[0]));
            potion.setName(words[1]);
            potion.setCost(Integer.parseInt(words[2]));
            potion.setReq_level(Integer.parseInt(words[3]));
            potion.setAtt_increase(Integer.parseInt(words[4]));
            potion.setAtt_affected(words[5]);
            potions.add(potion);
        }
    }

    public List<String> getList() {
        // Returns a list of potions
        allLines = Parser.parser("Potions.txt"); // Parses the Potions.txt config file
        return this.getItemList(allLines); // Returns the list of items from the parsed config file
    }

    @Override
    public void buy(Market market, Heroes hero, int id) {
        // method to buy the potion
        hero.getPotions().add(market.getPotion().potions.get(id));
    }
}
