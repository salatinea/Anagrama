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
        JPanel linhaPanel = new JPanel();
        linhaPanel.setOpaque(false);
        linhaPanel.setLayout(new BoxLayout(linhaPanel, BoxLayout.X_AXIS));

        for (int i = 0; i < tamanhoPalavra; i++) {
            JLabel backgroundImageLabel = new JLabel(new ImageIcon("images/OutrosBotoes/Linha.png"));
            linhaPanel.add(backgroundImageLabel);
            if (i != tamanhoPalavra - 1) {
                linhaPanel.add(Box.createRigidArea(new Dimension(ESPACAMENTO_ENTRE_LINHAS, 0)));
            }
        }
        return linhaPanel;
    }

    private JPanel criarPalavraDigitadaPanel() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.X_AXIS));
        return painel;
    }

    private JPanel criarPalavraDigitadaContainer(JPanel palavraDigitadaPanel) {
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(palavraDigitadaPanel);
        return container;
    }

    private void adicionarLetra(char letra) {
        JLabel label = new JLabel();
        int larguraFonte = (int) getTamanhoFonte(letra).getWidth();
        int larguraPreenchimento = LARGURA_LINHA - larguraFonte;

        if (palavraDigitadaPanel.getComponentCount() > 0) {
            palavraDigitadaPanel.add(Box.createRigidArea(new Dimension(ESPACAMENTO_ENTRE_LINHAS, 0)));
        }

        palavraDigitadaPanel.add(Box.createRigidArea(new Dimension(larguraPreenchimento / 2, 0)));
        palavraDigitadaPanel.add(label);
        palavraDigitadaPanel.add(Box.createRigidArea(new Dimension(larguraPreenchimento / 2, 0)));

        label.setText(String.valueOf(letra));
        label.setFont(ResourceManager.getCustomFont().deriveFont((float) TAMANHO_FONTE));
    }

    private static Dimension getTamanhoFonte(char c) {
        JLabel label = new JLabel(String.valueOf(c));
        label.setFont(ResourceManager.getCustomFont().deriveFont((float) TAMANHO_FONTE));
        return label.getPreferredSize();
    }

    public void setPalavraDigitada(String palavra) {
        palavraDigitadaPanel.removeAll();
        for (char letra : palavra.toCharArray()) {
            adicionarLetra(letra);
        }
        palavraDigitadaPanel.revalidate();
        palavraDigitadaPanel.repaint();
    }

    public String getPalavraDigitada() {
        StringBuilder sb = new StringBuilder();
        for (Component c : palavraDigitadaPanel.getComponents()) {
            if (c instanceof JLabel) {
                JLabel label = (JLabel) c;
                sb.append(label.getText());
            }
        }
        return sb.toString();
    }
}
