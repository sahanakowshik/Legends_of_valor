import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Spirit extends Monsters{
    // Class to create a spirit
    private static List<String> allLines;
    private int n;

    public static List<String> getAllLines() {
        return allLines;
    }

    public static List<String> getList() {
        // Returns a list of spirits
        allLines = Parser.parser("Spirits.txt"); // Parses the Spirits.txt file
        return Monsters.getItemList(allLines); // Returns the list of spirits from the parsed file
    }

    public int getN() {
        return this.n;
    }

    @Override
    public String getType() {
        return "Spirit";
    }

}
