import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;


public class AnagramaApp {
    static final int espacamentoEntreLinhas = 42;
    static final int larguraLinha = 100;
    static final int tamanhoFonte = 100;
    public static void main(String[] args) {
        // Garante que a Interface Screen seja atualizada de forma segura e responsiva
        SwingUtilities.invokeLater(AnagramaApp::Screen);
    }

    private static void Screen() {

        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/Bungee-Regular.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        JFrame frame = new JFrame("Anagrama");

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        BancoPalavras banco = new BancoPalavras(width, height);
        TratamentoPalavra palavra = new TratamentoPalavra(banco.getPalavraAleatoria());

        JPanel panelPalavraDigitada = criarPanelPalavraDigitada();
        JPanel panelLetrasEmbaralhadas = criarPanelLetrasEmbaralhadas(palavra, panelPalavraDigitada);
        JPanel containerPalavraDigitada = criarContainerPalavraDigitada(panelPalavraDigitada);

        JPanel panelBotoes = Panel(0.4);
        panelBotoes.setOpaque(false);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panelPalavraDigitadaComLinhas = criarPanelPalavraDigitadaComLinhas(containerPalavraDigitada, palavra);

        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.add(panelLetrasEmbaralhadas);
        frame.add(panelPalavraDigitadaComLinhas);
        frame.add(panelBotoes);
        frame.setVisible(true);
    }

    private static JPanel criarPanelPalavraDigitadaComLinhas(JPanel containerPalavraDigitada, TratamentoPalavra palavra) {
        JPanel panelLinha = criarPanelLinha(palavra);
        JPanel panelPalavraDigitadaComLinhas = new JPanel();
        panelPalavraDigitadaComLinhas.setLayout(new BoxLayout(panelPalavraDigitadaComLinhas, BoxLayout.Y_AXIS));
        panelPalavraDigitadaComLinhas.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPalavraDigitadaComLinhas.add(containerPalavraDigitada);
        panelPalavraDigitadaComLinhas.add(panelLinha);
        panelPalavraDigitadaComLinhas.setOpaque(false);
        return panelPalavraDigitadaComLinhas;
    }

    private static JPanel criarPanelLinha(TratamentoPalavra palavra) {
        // Painel onde ficará a linha da palavra
        JPanel panelLinha = new JPanel();
        panelLinha.setLayout(new FlowLayout());
        panelLinha.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLinha.setOpaque(false);
        panelLinha.setLayout(new BoxLayout(panelLinha, BoxLayout.X_AXIS));

        int length = palavra.getLength();
        for (int i = 0; i < length; i++) {
            JLabel backgroundImageLabel = new JLabel();
            ImageIcon backgroundImage = new ImageIcon("images/OutrosBotoes/Linha.png");
            backgroundImageLabel.setIcon(backgroundImage);
            backgroundImageLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
            panelLinha.add(backgroundImageLabel);
            if (i != length - 1) {
                panelLinha.add(Box.createRigidArea(new Dimension(espacamentoEntreLinhas, 0)));
            }
        }

        return panelLinha;
    }

    private static JPanel criarPanelLetrasEmbaralhadas(TratamentoPalavra palavra, JPanel panelPalavraDigitada) {
        JPanel panelLetrasEmbaralhadas = Panel(0.5);
        panelLetrasEmbaralhadas.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 100));
        panelLetrasEmbaralhadas.setOpaque(false);

        for (int i = 0; i < palavra.getLength(); i++) {
            char c = palavra.getPalavraEmbaralhada()[i];
            JToggleButton letraBotao = criarLetraBotao(c, panelPalavraDigitada);
            panelLetrasEmbaralhadas.add(letraBotao);
        }

        return panelLetrasEmbaralhadas;
    }

    private static JPanel criarContainerPalavraDigitada(JPanel panelPalavraDigitada) {
        JPanel containerPalavraDigitada = new JPanel();
        containerPalavraDigitada.add(Box.createRigidArea(new Dimension(0, (int) getTamanhoFonte('A').getHeight())));
        containerPalavraDigitada.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerPalavraDigitada.setLayout(new BoxLayout(containerPalavraDigitada, BoxLayout.X_AXIS));
        containerPalavraDigitada.setOpaque(false);
        containerPalavraDigitada.add(panelPalavraDigitada);
        return containerPalavraDigitada;
    }

    private static JPanel criarPanelPalavraDigitada() {
        JPanel panelPalavraDigitada = new JPanel();
        panelPalavraDigitada.setLayout(new BoxLayout(panelPalavraDigitada, BoxLayout.X_AXIS));
        panelPalavraDigitada.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelPalavraDigitada.setOpaque(false);
        return panelPalavraDigitada;
    }

    private static JPanel Panel(double heightPercentage) {
        JPanel panel = new JPanel();
        int frameHeight = 400; // Altura total do JFrame
        int panelHeight = (int) (frameHeight * heightPercentage); // Calcula a altura com base na porcentagem
        panel.setPreferredSize(new Dimension(400, panelHeight)); // Largura 100%, altura ajustada
        return panel;
    }

    private static Dimension getTamanhoFonte(char c) {
        JLabel label = new JLabel();
        label.setText(String.valueOf(c));
        label.setFont(new Font("Bungee", Font.PLAIN, tamanhoFonte));
        return label.getPreferredSize();
    }

    private static JToggleButton criarLetraBotao(char c, JPanel panelPalavraDigitada) {
        ImageIcon iconeLetra = new ImageIcon("images/Botões/Botão_"+c+".png");
        ImageIcon iconeLetraHover = new ImageIcon("images/Botões Hover/BotãoHover_"+c+".png");
        ImageIcon iconeLetraPress = new ImageIcon("images/Botões Pressionados/BotãoPress_"+c+".png");

        JToggleButton botaoLetra = new JToggleButton(iconeLetra);
        botaoLetra.setVisible(true);
        botaoLetra.setRolloverIcon(iconeLetraHover);
        botaoLetra.setPressedIcon(iconeLetraPress);
        botaoLetra.setSize(new Dimension(100, 100));
        botaoLetra.setContentAreaFilled(false);
        botaoLetra.setBorderPainted(false);
        botaoLetra.setFocusPainted(false);

        botaoLetra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoLetra.removeActionListener(this);
                JLabel label = new JLabel();

                int larguraFonte = (int) getTamanhoFonte(c).getWidth();
                
                int larguraPreenchimento = larguraLinha - larguraFonte;
                
                if (panelPalavraDigitada.getComponentCount() > 1) {
                    panelPalavraDigitada.add(Box.createRigidArea(new Dimension(espacamentoEntreLinhas, 0)));
                }
                
                panelPalavraDigitada.add(Box.createRigidArea(new Dimension(larguraPreenchimento / 2, 0)));
                panelPalavraDigitada.add(label);
                panelPalavraDigitada.add(Box.createRigidArea(new Dimension(larguraPreenchimento / 2, 0)));
                label.setText(String.valueOf(c));
                label.setFont(new Font("Bungee", Font.PLAIN, tamanhoFonte));
                botaoLetra.setIcon(iconeLetraPress);
                botaoLetra.setRolloverEnabled(false);
            }
        });
        return botaoLetra;
    }
}
