import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Exoskeleton extends Monsters{
    // Class to create an exoskeleton

    private static List<String> allLines;
    private int n;

    public static List<String> getAllLines() {
        return allLines;
    }

    public static List<String> getList() {
        // Returns a list of exoskeletons
        allLines = Parser.parser("Exoskeletons.txt"); // Parses the Exoskeletons.txt file
        return Monsters.getItemList(allLines); // Returns a list of exoskeletons from the parsed file
    }

    public int getN() {
        return this.n;
    }

    @Override
    public String getType() {
        return "Exoskeleton";
    }
}
