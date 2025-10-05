import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GameController controller;
    private PalavraDigitadaComLinhasPanel palavraPanel;
    private BotoesLetrasEmbaralhadasPanel botoesPanel;
    private JButton botaoDesfazer, botaoDelete, botaoReiniciar;

    public void init() {
        setTitle("Anagrama");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        controller = new GameController();

        palavraPanel = new PalavraDigitadaComLinhasPanel(controller.getPalavraLength());
        botoesPanel = new BotoesLetrasEmbaralhadasPanel(controller.getTratamentoPalavra());
        botoesPanel.addBotaoLetraClickedListener((letra, pos) -> {
            controller.onBotaoLetraClicked(letra, pos);
            controller.atualizarUI(palavraPanel, botoesPanel);

            if (controller.checkIfFinished()) {
                boolean correct = controller.checkCorrect();
                String message = correct ? "Você acertou!!!" : "Você errou.";
                JOptionPane.showMessageDialog(null, message, "Anagrama", JOptionPane.INFORMATION_MESSAGE,
                        ResourceManager.getLogoIcon(15, 15));
            }
        });

        botaoDesfazer = ButtonFactory.create("Backspace");
        botaoDelete = ButtonFactory.create("Delete");
        botaoReiniciar = ButtonFactory.create("Reiniciar");

        botaoDesfazer.addActionListener(e -> controller.desfazer(palavraPanel, botoesPanel));
        botaoDelete.addActionListener(e -> controller.delete(palavraPanel, botoesPanel));
        botaoReiniciar.addActionListener(e -> restartGame());

        JPanel botoesExtras = new JPanel();
        botoesExtras.setOpaque(false);
        botoesExtras.add(botaoDesfazer);
        botoesExtras.add(botaoDelete);
        botoesExtras.add(botaoReiniciar);

        getContentPane().setBackground(Color.LIGHT_GRAY);
        add(botoesPanel);
        add(palavraPanel);
        add(botoesExtras);

        setVisible(true);
    }

    private void restartGame() {
        getContentPane().removeAll();
        repaint();
        init();
    }
}

