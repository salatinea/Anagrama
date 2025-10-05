import javax.swing.*;
import java.awt.*;

public class ButtonFactory {
    public static JButton create(String name) {
        return create(
                "images/OutrosBotoes/" + name + ".png",
                "images/OutrosBotoes/" + name + " Hover.png",
                "images/OutrosBotoes/" + name + " Press.png"
        );
    }

    private static JButton create(String icon, String iconHover, String iconPress) {
        JButton button = new JButton(new ImageIcon(icon));
        button.setRolloverIcon(new ImageIcon(iconHover));
        button.setPressedIcon(new ImageIcon(iconPress));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setSize(new Dimension(100, 100));
        return button;
    }
}

