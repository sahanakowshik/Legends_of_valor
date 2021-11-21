import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Parser {
    // Class to parse the configuration files
    private static List<String> allLines;

    public static List<String> parser(String name){
        try {
            String file = System.getProperty("user.dir") + "/Legends_Monsters_and_Heroes/" + name;
            allLines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLines;
    }

    public static void parseMusic(String name){
        String file = System.getProperty("user.dir") + "/music/" + name;
        Music.playSound(file);
    }

}
