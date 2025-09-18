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
        SwingUtilities.invokeLater(() -> Screen());
    }

    private static void Screen() {

        // Importando fonte
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/Bungee-Regular.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);

        JFrame frame = new JFrame("Anagrama");

        // Pega a resolução da tela
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        BancoPalavras banco = new BancoPalavras(width, height);
        TratamentoPalavra palavra = new TratamentoPalavra(banco.getPalavraAleatoria());
        palavra.getPalavraEmbaralhada();
        palavra.getArraydeCharLength();

        // Painel onde ficará as letras embaralhadas
        JPanel panelSuperior = Panel(0.5);
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 100));
        panelSuperior.setOpaque(false);

        // Painel onde ficará a palavra digitada pelo usuário
        JPanel containerLetraPalavra = new JPanel();
        containerLetraPalavra.add(Box.createRigidArea(new Dimension(0, (int) getTamanhoFonte('A').getHeight())));
        containerLetraPalavra.setAlignmentX(Component.LEFT_ALIGNMENT);
        containerLetraPalavra.setLayout(new BoxLayout(containerLetraPalavra, BoxLayout.X_AXIS));
        containerLetraPalavra.setOpaque(false);

        JPanel panelLetraPalavra = new JPanel();
        panelLetraPalavra.setLayout(new BoxLayout(panelLetraPalavra, BoxLayout.X_AXIS));
        panelLetraPalavra.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLetraPalavra.setOpaque(false);
        containerLetraPalavra.add(panelLetraPalavra);

        // Painel onde ficará a linha da palavra
        JPanel panelLinha = new JPanel();
        panelLinha.setLayout(new FlowLayout());
        panelLinha.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLinha.setOpaque(false);

        // Painel onde ficarão os botões: Mudar palavra, delete e backspace
        JPanel panelInferior = Panel(0.4);
        panelInferior.setOpaque(false);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Cria as linhas abaixo de cada letra, de acordo com o tamanho da palavra
        panelLinha.setLayout(new BoxLayout(panelLinha, BoxLayout.X_AXIS));
        for (int i = 0; i < palavra.getArraydeCharLength(); i++) {
            JLabel backgroundImageLabel = new JLabel();
            ImageIcon backgroundImage = new ImageIcon("images/OutrosBotoes/Linha.png");
            backgroundImageLabel.setIcon(backgroundImage);
            backgroundImageLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
            panelLinha.add(backgroundImageLabel);
            if (i != palavra.getArraydeCharLength() - 1) {
                panelLinha.add(Box.createRigidArea(new Dimension(espacamentoEntreLinhas, 0)));
            }
        }

        palavra.getPalavraEmbaralhada();

        for (char c : palavra.getPalavraEmbaralhada()) {
            criarLetraBotao(c, panelSuperior, panelLetraPalavra);
        }


        JPanel letraLinhaContainer = new JPanel();
        letraLinhaContainer.setLayout(new BoxLayout(letraLinhaContainer, BoxLayout.Y_AXIS));
        letraLinhaContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        letraLinhaContainer.add(containerLetraPalavra);
        letraLinhaContainer.add(panelLinha);
        letraLinhaContainer.setOpaque(false);

        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.add(panelSuperior);
        frame.add(letraLinhaContainer);
        frame.add(panelInferior);
        frame.setVisible(true);
        System.out.println(panelLetraPalavra.getMinimumSize());
    }

    private static JPanel Panel(double heightPercentage) {
        JPanel panel = new JPanel();
        int frameHeight = 400; // Altura total do JFrame
        int panelHeight = (int) (frameHeight * heightPercentage); // Calcula a altura com base na porcentagem
        panel.setPreferredSize(new Dimension(400, panelHeight)); // Largura 100%, altura ajustada
        return panel;
    }

    private static Dimension getTamanhoFonte(char c) {
        JLabel label2 = new JLabel();
        label2.setText(String.valueOf(c));
        label2.setFont(new Font("Bungee", Font.PLAIN, tamanhoFonte));
        return label2.getPreferredSize();
    }
    private static void criarLetraBotao(char c, JPanel panelSuperior, JPanel panelLetraPalavra) {
        ImageIcon iconeLetra = new ImageIcon("images/Botões/Botão_"+c+".png");
        ImageIcon iconeLetraHover = new ImageIcon("images/Botões Hover/BotãoHover_"+c+".png");
        ImageIcon iconeLetraPress = new ImageIcon("images/Botões Pressionados/BotãoPress_"+c+".png");

        JToggleButton botaoLetra = new JToggleButton(iconeLetra);
        panelSuperior.add(botaoLetra);
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
                int largura = (larguraLinha - larguraFonte) / 2;
                if (panelLetraPalavra.getComponentCount() > 1) {
                    panelLetraPalavra.add(Box.createRigidArea(new Dimension(espacamentoEntreLinhas, 0)));
                }
                panelLetraPalavra.add(Box.createRigidArea(new Dimension(largura, 0)));
                panelLetraPalavra.add(label);
                panelLetraPalavra.add(Box.createRigidArea(new Dimension(largura, 0)));
                label.setText(String.valueOf(c));
                label.setFont(new Font("Bungee", Font.PLAIN, tamanhoFonte));
                botaoLetra.setIcon(iconeLetraPress);
                botaoLetra.setRolloverEnabled(false);
            }
        });
    }
}
