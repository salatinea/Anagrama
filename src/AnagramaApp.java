import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;


public class AnagramaApp {
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
        int tamanhoFonte = 100;

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
        palavra.getPalavraEbaralhada();
        palavra.getArraydeCharLength();

        DetectarResolucaoTela resolucao = new DetectarResolucaoTela(width, height);

        //Painel onde ficará as letras embaralhadas
        JPanel panelSuperior = Panel(0.5);
        panelSuperior.setBackground(Color.RED);
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 1, resolucao.LayoutPanelSuperior()));

        //Painel onde ficará a palavra digitada pelo usuário
        JPanel panelLetraPalavra = Panel(0.1);
        panelLetraPalavra.setBackground(Color.CYAN);
        panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.CENTER, 70, resolucao.LayoutPanelLetraPalavra()));

        //Modificar Layout de acordo do tamanho de cada palavra, para as letras alinharem nas linhas
        switch (palavra.getArraydeCharLength()) {
            case 2:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label2 = new JLabel();
                label2.setFont(new Font("Bungee", Font.PLAIN, 10));
                label2.setText(resolucao.LayoutPalavra2());
                panelLetraPalavra.add(label2);
                break;
            case 3:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label3 = new JLabel();
                label3.setFont(new Font("Bungee", Font.PLAIN, 10));
                label3.setText(resolucao.LayoutPalavra3());
                panelLetraPalavra.add(label3);
                break;
            case 4:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label4 = new JLabel();
                label4.setFont(new Font("Bungee", Font.PLAIN, 10));
                label4.setText(resolucao.LayoutPalavra4());
                panelLetraPalavra.add(label4);
                break;
            case 5:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label5 = new JLabel();
                label5.setFont(new Font("Bungee", Font.PLAIN, 10));
                label5.setText(resolucao.LayoutPalavra5());
                panelLetraPalavra.add(label5);
                break;
            case 6:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label6 = new JLabel();
                label6.setFont(new Font("Bungee", Font.PLAIN, 10));
                label6.setText(resolucao.LayoutPalavra6());
                panelLetraPalavra.add(label6);
                break;
            case 7:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label7 = new JLabel();
                label7.setFont(new Font("Bungee", Font.PLAIN, 10));
                label7.setText(resolucao.LayoutPalavra7());
                panelLetraPalavra.add(label7);
                break;
            case 8:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label8 = new JLabel();
                label8.setFont(new Font("Bungee", Font.PLAIN, 10));
                label8.setText(resolucao.LayoutPalavra8());
                panelLetraPalavra.add(label8);
                break;
            case 9:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label9 = new JLabel();
                label9.setFont(new Font("Bungee", Font.PLAIN, 10));
                label9.setText(resolucao.LayoutPalavra9());
                panelLetraPalavra.add(label9);
                break;
            case 10:
                panelLetraPalavra.setLayout(new FlowLayout(FlowLayout.LEFT, 70, resolucao.LayoutPanelLetraPalavra()));
                JLabel label10 = new JLabel();
                label10.setFont(new Font("Bungee", Font.PLAIN, 10));
                label10.setText(resolucao.LayoutPalavra10());
                panelLetraPalavra.add(label10);
                break;
        }

        //Painel onde ficará a linha da palavra
        JPanel panelLinha = Panel(0.01);
        panelLinha.setBackground(Color.ORANGE);
        panelLinha.setLayout(new FlowLayout());

        //Painel onde ficarão os botões: Mudar palavra, delete e backspace
        JPanel panelInferior = Panel(0.4);
        panelInferior.setBackground(Color.GREEN);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(panelSuperior);
        frame.add(panelLetraPalavra);
        frame.add(panelLinha);
        frame.add(panelInferior);

        ///Acho que não serve para nada kkk, depois vou da uma olhada
        String[] palavras = new String[palavra.getArraydeCharLength()];

        //Cria as linhas abaixo de cada letra, de acordo com o tamanho da palavra
        for (int i = 0; i < palavra.getArraydeCharLength(); i++) {
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(110, 1));

            JLabel backgroundImageLabel = new JLabel();
            ImageIcon backgroundImage = new ImageIcon("images/OutrosBotoes/Linha.png");
            backgroundImageLabel.setIcon(backgroundImage);
            backgroundImageLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
            layeredPane.add(backgroundImageLabel, (0));
            panelLinha.add(backgroundImageLabel);
            panelLinha.setLayout(new FlowLayout(FlowLayout.CENTER, 42, 1));
        }

        //JToggleButton
        //Criando botões de acordo com cada letra
        palavra.getPalavraEbaralhada();                    //Recebe palavra embaralhada

        for (char c : palavra.getPalavraEbaralhada()) {//iteração que passa por cada char, da array de char. 
            //recebe a char e procura o "case" relacionado a ela
            criarLetraBotao(c, panelSuperior, panelLetraPalavra, tamanhoFonte);
        }

        //Final

        //Adiciona os JPanels na JFrame
        frame.add(panelSuperior);
        frame.add(panelLetraPalavra);
        frame.add(panelLinha);
        frame.add(panelInferior);
        frame.setVisible(true);
    }

    //Método para criar a JPanel de acordo com uma porcentagem total da JFrame.
    private static JPanel Panel(double heightPercentage) {
        JPanel panel = new JPanel();
        int frameHeight = 400; // Altura total do JFrame
        int panelHeight = (int) (frameHeight * heightPercentage); // Calcula a altura com base na porcentagem
        panel.setPreferredSize(new Dimension(400, panelHeight)); // Largura 100%, altura ajustada
        return panel;
    }

    private static void criarLetraBotao(char c, JPanel panelSuperior, JPanel panelLetraPalavra, int tamanhoFonte) {
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
                // garantir que essa função seja executada apenas no primeiro clique no botão
                botaoLetra.removeActionListener(this);

                JLabel label = new JLabel();
                panelLetraPalavra.add(label);
                label.setText(String.valueOf(c));
                label.setFont(new Font("Bungee", Font.PLAIN, tamanhoFonte));
                botaoLetra.setIcon(iconeLetraPress);
                botaoLetra.setRolloverEnabled(false);
            }
        });
    }
}
