import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Warrior extends Heroes{
    // Class to create a warrior
    private List<String> allLines;
    private int n;

    public List<String> getAllLines() {
        return allLines;
    }

    public Warrior(){
        allLines = Parser.parser("Warriors.txt"); // Parses the config file
        this.n = allLines.size();
    }

    public int getN() {
        return this.n;
    }

    @Override
    public String getType() {
        return "Warrior";
    }

    @Override
    public void displayList() {
        // Displays the list of warriors
        this.displayItemList(allLines);
    }
}
