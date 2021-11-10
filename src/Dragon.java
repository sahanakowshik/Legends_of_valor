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
        List<String> list = new ArrayList<>();
        allLines = Parser.parser("Dragons.txt");
        for (int i=1;i<allLines.size();i++) {
            String str = i + "   " + allLines.get(i);
            list.add(str);
        }
        return list;
    }
}
