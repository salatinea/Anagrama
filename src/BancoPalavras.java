import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BancoPalavras {

    private String palavraAleatoria;
    public int largura, altura;
    //Recebe como parâmetro o tamanho da tela, utilizando o código da Classe Main
    public BancoPalavras(int width, int height) {

        this.largura = width;
        this.altura = height;

            String[] animais = {"LEÃO", "TIGRE", "ELEFANTE", "GIRAFA", "RINOCERONTE", "HIPOPÓTAMO",
                    "CROCODILO", "GOLFINHO", "ORCA", "ÁGUIA", "CORUJA", "GALO", "CACHORRO", "GATO", "PEIXE",
                    "TARTARUGA", "COBRA", "LAGARTO", "URSO", "LOBO", "ZEBRA", "JACARÉ", "PANDA", "RATO", "CAMELO",
                    "FOCA", "GORILA", "HIENA", "CARANGUEJO", "TUBARÃO", "POLVO", "SERPENTE", "URUBU", "BÚFALO",
                    "COALA", "PUMA", "BISÃO", "LEOPARDO", "ALCE", "GALINHA", "CISNE", "FORMIGA", "CANGURU",
                    "PINGUIM", "TUCANO", "GAMBÁ", "CARNEIRO","GUEPARDO", "IGUANA", "FLAMINGO", "ABELHA", "RAPTOR",
                    "OURIÇO", "ESCORPIÃO", "MACACO", "BÚFALO", "SALMÃO", "MORSA", "BODE", "MARRECO", "RAPOSA", "CAVALO",
                    "SALAMANDRA", "CARPA", "SAPO", "HAMSTER", "LEBRÃO","LINCE", "CISNE-NEGRO", "SURICATO", "GAVIÃO",
                    "ARARA", "ALBATROZ", "BEIJA-FLOR", "URSO-POLAR", "LAGOSTA", "MORCEGO", "POLVO", "PATO", "JAGUATIRICA"};


        //Resolução 1920x1080 suporta palavras com até X letras
        //Ainda não está configurado, os valores estão incorretos
        if (largura == 1920 && altura == 1080) {
            for (String animal : animais) {
                if (animal.length() <= 100) {
                    Random random = new Random();
                    int NumPalavraAleatoria = random.nextInt(animais.length);
                    palavraAleatoria = animais[NumPalavraAleatoria];
                }
            }
        }
        //Resolução 1600x900 suporta palavras com até 10 letras, acima disso, a visualização fica bugada, por falta de espaço na tela.
        //Passa por cada String da Array de Animais, e seleciona somente as palavras que contém 10 ou menos letras.
        else if (largura == 1600 && altura == 900) {
            List<String> animaisComMenosDe10Letras = new ArrayList<>();
            for (String animal : animais) {
                if (animal.length() <= 10) {
                    animaisComMenosDe10Letras.add(animal);
                }
            }
            Random random = new Random();
            int NumPalavraAleatoria = random.nextInt(animaisComMenosDe10Letras.size());
            palavraAleatoria = animaisComMenosDe10Letras.get(NumPalavraAleatoria);
        }
        //Resolução 1600x900 suporta palavras com até 8 letras, acima disso, a visualização fica bugada, por falta de espaço na tela.
        //Passa por cada String da Array de Animais, e seleciona somente as palavras que contém 8 ou menos letras.
        else if (largura == 1366 && altura == 768) {
            List<String> animaisComMenosDe8Letras = new ArrayList<>();
            for (String animal : animais) {
                if (animal.length() <= 8) {
                    animaisComMenosDe8Letras.add(animal);
                }
            }
            Random random = new Random();
            int NumPalavraAleatoria = random.nextInt(animaisComMenosDe8Letras.size());
            palavraAleatoria = animaisComMenosDe8Letras.get(NumPalavraAleatoria);
        }
        else if (largura == 1280 && altura == 720) {
            List<String> animaisComMenosDe8Letras = new ArrayList<>();
            for (String animal : animais) {
                if (animal.length() <= 8) {
                    animaisComMenosDe8Letras.add(animal);
                }
            }
            Random random = new Random();
            int NumPalavraAleatoria = random.nextInt(animaisComMenosDe8Letras.size());
            palavraAleatoria = animaisComMenosDe8Letras.get(NumPalavraAleatoria);
        }
        else{
            List<String> animaisComMenosDe10Letras = new ArrayList<>();
            for (String animal : animais) {
                if (animal.length() <= 10) {
                    animaisComMenosDe10Letras.add(animal);
                }
            }
            Random random = new Random();
            int NumPalavraAleatoria = random.nextInt(animaisComMenosDe10Letras.size());
            palavraAleatoria = animaisComMenosDe10Letras.get(NumPalavraAleatoria);
        }
    }


    //Retorna as palavras de acordo com a resolução da tela.
    public String getPalavraAleatoria() {
        return palavraAleatoria;
    }

}
