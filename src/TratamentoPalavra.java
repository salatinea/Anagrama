import java.util.Random;

public class TratamentoPalavra {

    String palavra;

    //Recebe uma palavra aleatório da Classe "BancoPalavras"
    public TratamentoPalavra(String palavra){
        this.palavra = palavra;
    }

    //Transforma a String recebida em uma Array de Char, e embaralha utilizando o método embaralharArray.
    public void Embaralhar()
    {
        char[] arrayDeChar = palavra.toCharArray();
        embaralharArray(arrayDeChar);
        display(arrayDeChar);

    }
    //Método para receber a Array de char, será utilizado no código main.
    public char[] getPalavraEmbaralhada()
    {
        char[] arrayDeChar = palavra.toCharArray();
        embaralharArray(arrayDeChar);
        return arrayDeChar;
    }
    //Método para receber o tamanho da Array de char, será utilizado no código main.
    public int getArraydeCharLength()
    {
        char[] arrayDeChar = palavra.toCharArray();
        embaralharArray(arrayDeChar);
        return arrayDeChar.length;
    }
    //Recebe a Array de Char e troca aleatóriamente a posição de cada Char na Array
    public static void embaralharArray(char[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    //Imprime a Array de char embaralhada (na real eu não usei isso pra nada, sei nem pq tá aquikkkkkk)
    public static void display(char[] array){

        for(char c: array)
        {
            System.out.print(c + " ");
        }
    }
}
