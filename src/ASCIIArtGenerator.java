import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ASCIIArtGenerator {

    public static final int ART_SIZE_MEDIUM = 18;

    public static final String DEFAULT_ART_SYMBOL = "*";

    public enum ASCIIArtFont {
        ART_FONT_DIALOG("Dialog");

        public String value;

        public String getValue() {
            return value;
        }

        private ASCIIArtFont(String value) {
            this.value = value;
        }
    }

    public void printTextArt(String artText, int textHeight, ASCIIArtFont fontType, String artSymbol) throws Exception {
        // Prints ASCII art for the specified text.
        String fontName = fontType.getValue();
        int imageWidth = findImageWidth(textHeight, artText, fontName);

        BufferedImage image = new BufferedImage(imageWidth, textHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Font font = new Font(fontName, Font.BOLD, textHeight);
        g.setFont(font);

        Graphics2D graphics = (Graphics2D) g;
        graphics.drawString(artText, 0, getBaselinePosition(g, font));

        for (int y = 0; y < textHeight; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < imageWidth; x++)
                sb.append(image.getRGB(x, y) == Color.WHITE.getRGB() ? artSymbol : " ");
            if (sb.toString().trim().isEmpty())
                continue;
            System.out.println(sb);
        }
    }

    public void printTextArt(String artText, int textHeight) throws Exception {
        // Convenience method for printing ascii text art
        printTextArt(artText, textHeight, ASCIIArtFont.ART_FONT_DIALOG, DEFAULT_ART_SYMBOL);
    }

    public int findImageWidth(int textHeight, String artText, String fontName) {
        // Using the Current font and current art text find the width of the full image
        BufferedImage im = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics g = im.getGraphics();
        g.setFont(new Font(fontName, Font.BOLD, textHeight));
        return g.getFontMetrics().stringWidth(artText);
    }

    public int getBaselinePosition(Graphics g, Font font) {
        // Find where the text baseline should be drawn so that the characters are within image
        FontMetrics metrics = g.getFontMetrics(font);
        int y = metrics.getAscent() - metrics.getDescent();
        return y;
    }
}