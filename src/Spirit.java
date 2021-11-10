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
        List<String> list = new ArrayList<>();
        allLines = Parser.parser("Spirits.txt");
        for (int i=1;i<allLines.size();i++) {
            String str = i + "   " + allLines.get(i);
            list.add(str);
        }
        return list;
    }

    public int getN() {
        return this.n;
    }

    @Override
    public String getType() {
        return "Spirit";
    }

}
