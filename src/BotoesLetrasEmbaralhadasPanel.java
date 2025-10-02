import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotoesLetrasEmbaralhadasPanel extends JPanel {
    private final int width = 400;
    private final int height = 200;

    private final TratamentoPalavra palavra;
    private BotaoLetraClickListener listener;
    public BotoesLetrasEmbaralhadasPanel(TratamentoPalavra palavra) {
        setPreferredSize(new Dimension(width, height));
        setLayout(new FlowLayout(FlowLayout.CENTER, 1, 100));
        setOpaque(false);
        this.palavra = palavra;
        setBotoesLetraApertados(new boolean[palavra.getLength()]);
    }

    private JToggleButton criarLetraBotao(char letra, int posicao, boolean apertado) {
        ImageIcon iconeLetra = new ImageIcon("images/Botões/Botão_"+letra+".png");
        ImageIcon iconeLetraHover = new ImageIcon("images/Botões Hover/BotãoHover_"+letra+".png");
        ImageIcon iconeLetraPress = new ImageIcon("images/Botões Pressionados/BotãoPress_"+letra+".png");

        JToggleButton botaoLetra = new JToggleButton(iconeLetra);
        botaoLetra.setVisible(true);
        botaoLetra.setRolloverIcon(iconeLetraHover);
        botaoLetra.setPressedIcon(iconeLetraPress);
        botaoLetra.setSize(new Dimension(100, 100));
        botaoLetra.setContentAreaFilled(false);
        botaoLetra.setBorderPainted(false);
        botaoLetra.setFocusPainted(false);

        if (!apertado) {
            botaoLetra.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (BotoesLetrasEmbaralhadasPanel.this.listener != null) {
                        listener.botaoLetraClicked(letra, posicao);
                    }

                    // garantir que essa função seja executado apenas no primeiro clique no botão
                    botaoLetra.setIcon(iconeLetraPress);
                    botaoLetra.setRolloverEnabled(false);
                }
            });
        } else {
            botaoLetra.setIcon(iconeLetraPress);
            botaoLetra.setRolloverEnabled(false);
        }

        return botaoLetra;
    }

    public void setBotoesLetraApertados(boolean[] estadoBotoes) {
        removeAll();
        for (int i = 0; i < palavra.getLength(); i++) {
            // Iteração que passa por cada char, da array de char!
            char c = palavra.getPalavraEmbaralhada()[i];
            JToggleButton letraBotao = criarLetraBotao(c, i, estadoBotoes[i]);
            add(letraBotao);
        }
        revalidate();
    }

    public void addBotaoLetraClickedListener(BotaoLetraClickListener listener) {
        this.listener = listener;
    }
}
