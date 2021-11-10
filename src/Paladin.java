import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Paladin extends Heroes{
    // Class to create a paladin
    private List<String> allLines;
    private int n;

    public List<String> getAllLines() {
        return allLines;
    }

    public Paladin(){
        allLines = Parser.parser("Paladins.txt");
        this.n = allLines.size();
    }

    public int getN() {
        return this.n;
    }

    @Override
    public String getType() {
        return "Paladin";
    }

    @Override
    public void displayList() {
        // Displays the list of paladins
        String[] words = allLines.get(0).split("/");
        String line = String.join("   ", words);
        System.out.println("Id   " +line);
        for (int i=1;i<allLines.size();i++) {
            System.out.print(i + "   ");
            System.out.println(allLines.get(i));
        }
    }
}
