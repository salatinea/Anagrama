import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ResourceManager {
    private static Font customFont;
    private static ImageIcon logoIcon;

    public static Font getCustomFont() {
        if (customFont == null) {
            try {
                customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/Bungee-Regular.ttf"));
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
                e.printStackTrace();
                customFont = new Font("SansSerif", Font.PLAIN, 36);
            }
        }
        return customFont;
    }

    public static ImageIcon getLogoIcon(int width, int height) {
        if (logoIcon == null) {
            try {
                Image img = javax.imageio.ImageIO.read(new File("images/logo.png"));
                logoIcon = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
            } catch (Exception e) {
                e.printStackTrace();
                logoIcon = new ImageIcon();
            }
        }
        return logoIcon;
    }
}

