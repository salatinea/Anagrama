import javax.swing.SwingUtilities;

public class AnagramaApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame().init());
    }
}
