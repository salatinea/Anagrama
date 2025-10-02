import java.util.Stack;

public class EstadoJogo {
    private TratamentoPalavra palavra;
    private String palavraDigitada;
    private Stack<Integer> posicaoBotoesApertados;
    private boolean[] estadoBotoes;

    public EstadoJogo(TratamentoPalavra palavra) {
        this.palavra = palavra;
        esvaziar();
    }

    private void esvaziar() {
        this.posicaoBotoesApertados = new Stack<Integer>();
        this.palavraDigitada = "";
        this.estadoBotoes = new boolean[palavra.getLength()];
    }

    public TratamentoPalavra getTratamentoPalavra() {
        return palavra;
    }

    public String getPalavraDigitada() {
        return palavraDigitada;
    }

    public boolean[] getEstadoBotoes() {
        return estadoBotoes;
    }

    public void apertarBotao(int posicao) {
        posicaoBotoesApertados.push(posicao);
        estadoBotoes[posicao] = true;
    }

    public void desfazer() {
        if (!posicaoBotoesApertados.empty()) {
            estadoBotoes[posicaoBotoesApertados.pop()] = false;
        }
        if (!palavraDigitada.isEmpty()) {
            this.palavraDigitada = palavraDigitada.substring(0, palavraDigitada.length() - 1);
        }
    }

    public void delete() {
        esvaziar();
    }

    public void adicionarLetraPalavraDigitada(char letra) {
        this.palavraDigitada = palavraDigitada + letra;
    }
}
