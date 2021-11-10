public class Main {
    // Main class
    public static void main(String[] args) throws Exception {
        ASCIIArtGenerator artGen = new ASCIIArtGenerator();
	// write your code here
        Game game = new LegendsGame();
        game.startGame();
        artGen.printTextArt("Thank you", ASCIIArtGenerator.ART_SIZE_MEDIUM);
    }
}
