import java.util.Random;

public class TratamentoPalavra {
    final private String palavra;
    final private String palavraEmbaralhada;

    // Recebe uma palavra aleatório da Classe "BancoPalavras"
    public TratamentoPalavra(String palavra) {
        this.palavra = palavra;
        this.palavraEmbaralhada = embaralharString(palavra);
    }

    // Método para receber a Array de char, será utilizado no código main.

    // Recebe a Array de Char e troca aleatóriamente a posição de cada Char na Array
    public static void embaralharArray(char[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static String embaralharString(String str) {
        char[] array = str.toCharArray();
        embaralharArray(array);
        return String.valueOf(array);
    }

    // Método para receber o tamanho da Array de char, será utilizado no código main.
    public String getPalavra() {
        return palavra;
    }

    public char[] getPalavraEmbaralhada() {
        return palavraEmbaralhada.toCharArray();
    }

    public int getLength() {
        return palavra.length();
    }
}
