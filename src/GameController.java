import javax.swing.*;

public class GameController {
    private EstadoJogo estadoJogo;

    public GameController() {
        BancoPalavras banco = new BancoPalavras(
                java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                        .getDisplayMode().getWidth(),
                java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                        .getDisplayMode().getHeight()
        );
        TratamentoPalavra palavra = new TratamentoPalavra(banco.getPalavraAleatoria());
        estadoJogo = new EstadoJogo(palavra);
    }

    public int getPalavraLength() {
        return estadoJogo.getTratamentoPalavra().getLength();
    }

    public String getPalavra() {
        return estadoJogo.getTratamentoPalavra().getPalavra();
    }

    public TratamentoPalavra getTratamentoPalavra() {
        return estadoJogo.getTratamentoPalavra();
    }

    public void onBotaoLetraClicked(char letra, int posicao) {
        estadoJogo.adicionarLetraPalavraDigitada(letra);
        estadoJogo.apertarBotao(posicao);
    }

    public void desfazer(PalavraDigitadaComLinhasPanel palavraPanel, BotoesLetrasEmbaralhadasPanel botoesPanel) {
        estadoJogo.desfazer();
        atualizarUI(palavraPanel, botoesPanel);
    }

    public void delete(PalavraDigitadaComLinhasPanel palavraPanel, BotoesLetrasEmbaralhadasPanel botoesPanel) {
        estadoJogo.delete();
        atualizarUI(palavraPanel, botoesPanel);
    }

    public void atualizarUI(PalavraDigitadaComLinhasPanel palavraPanel, BotoesLetrasEmbaralhadasPanel botoesPanel) {
        botoesPanel.setBotoesLetraApertados(estadoJogo.getEstadoBotoes());
        palavraPanel.setPalavraDigitada(estadoJogo.getPalavraDigitada());
    }

    public boolean checkIfFinished() {
        String palavra = estadoJogo.getTratamentoPalavra().getPalavra();
        String digitada = estadoJogo.getPalavraDigitada();
        return palavra.length() == digitada.length();
    }

    public boolean checkCorrect() {
        return estadoJogo.getTratamentoPalavra().getPalavra().equals(estadoJogo.getPalavraDigitada());
    }
}

