import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dragon extends Monsters{
    // Class to create a dragon
    private static List<String> allLines;
    private int n;

    public List<String> getAllLines() {
        return allLines;
    }

    public int getN() {
        return this.n;
    }

    @Override
    public String getType() {
        return "Dragon";
    }

    public static List<String> getList() {
        // Returns a list of dragons
        allLines = Parser.parser("Dragons.txt"); // Parses the Dragons.txt file
        return Monsters.getItemList(allLines); // Returns the list of dragons from the parsed file
    }
}
