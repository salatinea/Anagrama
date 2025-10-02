import javax.swing.*;
import java.awt.*;

public class PalavraDigitadaComLinhasPanel extends JPanel {
    private final JPanel palavraDigitadaPanel;
    private final JPanel linhaPanel;
    private final JPanel palavraDigitadaContainer;
    private final int tamanhoPalavra;
    private static final int ESPACAMENTO_ENTRE_LINHAS = 42;
    private static final int TAMANHO_FONTE = 100;
    private static final int LARGURA_LINHA = 100;
    public PalavraDigitadaComLinhasPanel(int tamanhoPalavra) {
        this.tamanhoPalavra = tamanhoPalavra;
        this.linhaPanel = criarLinhaPanel();
        this.palavraDigitadaPanel = criarPalavraDigitadaPanel();
        this.palavraDigitadaContainer = criarPalavraDigitadaContainer(palavraDigitadaPanel);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        add(palavraDigitadaContainer);
        add(linhaPanel);
        setOpaque(false);
    }

    private JPanel criarLinhaPanel() {
        // Painel onde ficará a linha da palavra
        JPanel linhaPanel = new JPanel();
        linhaPanel.setLayout(new FlowLayout());
        linhaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        linhaPanel.setOpaque(false);
        linhaPanel.setLayout(new BoxLayout(linhaPanel, BoxLayout.X_AXIS));

        for (int i = 0; i < tamanhoPalavra; i++) {
            JLabel backgroundImageLabel = new JLabel();
            ImageIcon backgroundImage = new ImageIcon("images/OutrosBotoes/Linha.png");
            backgroundImageLabel.setIcon(backgroundImage);
            backgroundImageLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
            linhaPanel.add(backgroundImageLabel);
            if (i != tamanhoPalavra - 1) {
                linhaPanel.add(Box.createRigidArea(new Dimension(ESPACAMENTO_ENTRE_LINHAS, 0)));
            }
        }

        return linhaPanel;
    }
    private JPanel criarPalavraDigitadaPanel() {
        // Painel onde ficará a palavra digitada pelo usuário
        JPanel palavraDigitadaPanel = new JPanel();
        palavraDigitadaPanel.setLayout(new BoxLayout(palavraDigitadaPanel, BoxLayout.X_AXIS));
        palavraDigitadaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        palavraDigitadaPanel.setOpaque(false);
        return palavraDigitadaPanel;
    }

    private JPanel criarPalavraDigitadaContainer(JPanel palavraDigitadaPanel) {
        JPanel containerPalavraDigitada = new JPanel();
        containerPalavraDigitada.add(Box.createRigidArea(new Dimension(0, (int) getTamanhoFonte('A').getHeight())));
        containerPalavraDigitada.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPalavraDigitada.setLayout(new BoxLayout(containerPalavraDigitada, BoxLayout.X_AXIS));
        containerPalavraDigitada.setOpaque(false);
        containerPalavraDigitada.add(palavraDigitadaPanel);
        return containerPalavraDigitada;
    }

    private static Dimension getTamanhoFonte(char c) {
        JLabel label = new JLabel();
        label.setText(String.valueOf(c));
        label.setFont(new Font("Bungee", Font.PLAIN, TAMANHO_FONTE));
        return label.getPreferredSize();
    }

    private void adicionarLetra(char letra) {
        JLabel label = new JLabel();

        int larguraFonte = (int) getTamanhoFonte(letra).getWidth();

        // quantidade de preenchimento necessária para a letra e a linha terem o mesmo tamanho
        int larguraPreenchimento = LARGURA_LINHA - larguraFonte;

        // adicionar a rigid area para alinhar a letra com as linhas
        if (palavraDigitadaPanel.getComponentCount() > 0) {
            palavraDigitadaPanel.add(Box.createRigidArea(new Dimension(ESPACAMENTO_ENTRE_LINHAS, 0)));
        }

        // adicionar metade da larguraPreenchimento para centralizar a letra em cima da linha
        palavraDigitadaPanel.add(Box.createRigidArea(new Dimension(larguraPreenchimento / 2, 0)));
        palavraDigitadaPanel.add(label);
        palavraDigitadaPanel.add(Box.createRigidArea(new Dimension(larguraPreenchimento / 2, 0)));
        label.setText(String.valueOf(letra));
        label.setFont(new Font("Bungee", Font.PLAIN, TAMANHO_FONTE));
    }

    public void setPalavraDigitada(String palavra) {
        palavraDigitadaPanel.removeAll();
        for (char letra : palavra.toCharArray()) {
            adicionarLetra(letra);
        }
        palavraDigitadaPanel.revalidate();
    }
}
