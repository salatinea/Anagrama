import javax.swing.*;

public class TestSuite {

    public static void main(String[] args) {
        testAdicionarLetra();
        testDesfazer();
        testDelete();
        testPalavraCorreta();
        testPalavraErrada();
        testBotaoLetraApertado();
    }

    private static void testAdicionarLetra() {
        GameController controller = new GameController();
        char letra = controller.getTratamentoPalavra().getPalavra().charAt(0);

        controller.onBotaoLetraClicked(letra, 0);
        PalavraDigitadaComLinhasPanel panel = new PalavraDigitadaComLinhasPanel(controller.getPalavraLength());
        BotoesLetrasEmbaralhadasPanel botoesPanel = new BotoesLetrasEmbaralhadasPanel(controller.getTratamentoPalavra());
        controller.atualizarUI(panel, botoesPanel);

        boolean passed = panel.getPalavraDigitada().equals(String.valueOf(letra));
        System.out.println("Teste adicionar letra: " + (passed ? "OK" : "FAIL"));
    }

    private static void testDesfazer() {
        GameController controller = new GameController();
        char letra = controller.getTratamentoPalavra().getPalavra().charAt(0);
        controller.onBotaoLetraClicked(letra, 0);

        PalavraDigitadaComLinhasPanel panel = new PalavraDigitadaComLinhasPanel(controller.getPalavraLength());
        BotoesLetrasEmbaralhadasPanel botoesPanel = new BotoesLetrasEmbaralhadasPanel(controller.getTratamentoPalavra());

        controller.desfazer(panel, botoesPanel);

        boolean passed = panel.getPalavraDigitada().isEmpty();
        System.out.println("Teste desfazer letra: " + (passed ? "OK" : "FAIL"));
    }

    private static void testDelete() {
        GameController controller = new GameController();
        String palavra = controller.getTratamentoPalavra().getPalavra();
        char letra = palavra.charAt(0);
        controller.onBotaoLetraClicked(letra, 0);

        PalavraDigitadaComLinhasPanel panel = new PalavraDigitadaComLinhasPanel(controller.getPalavraLength());
        BotoesLetrasEmbaralhadasPanel botoesPanel = new BotoesLetrasEmbaralhadasPanel(controller.getTratamentoPalavra());

        controller.delete(panel, botoesPanel);

        boolean passed = panel.getPalavraDigitada().isEmpty();
        System.out.println("Teste delete letra: " + (passed ? "OK" : "FAIL"));
    }

    private static void testPalavraCorreta() {
        GameController controller = new GameController();
        String palavra = controller.getTratamentoPalavra().getPalavra();

        PalavraDigitadaComLinhasPanel panel = new PalavraDigitadaComLinhasPanel(controller.getPalavraLength());
        BotoesLetrasEmbaralhadasPanel botoesPanel = new BotoesLetrasEmbaralhadasPanel(controller.getTratamentoPalavra());

        for (int i = 0; i < palavra.length(); i++) {
            controller.onBotaoLetraClicked(palavra.charAt(i), i);
            controller.atualizarUI(panel, botoesPanel);
        }

        boolean finished = controller.checkIfFinished();
        boolean correct = controller.checkCorrect();
        System.out.println("Teste palavra completa: " + (finished ? "OK" : "FAIL"));
        System.out.println("Teste palavra correta: " + (correct ? "OK" : "FAIL"));
    }

    private static void testPalavraErrada() {
        GameController controller = new GameController();
        String palavra = controller.getTratamentoPalavra().getPalavra();
        char letraErrada = palavra.charAt(0) == 'A' ? 'B' : 'A';

        controller.onBotaoLetraClicked(letraErrada, 0);

        boolean correct = controller.checkCorrect();
        System.out.println("Teste palavra errada: " + (!correct ? "OK" : "FAIL"));
    }

    private static void testBotaoLetraApertado() {
        GameController controller = new GameController();
        PalavraDigitadaComLinhasPanel panel = new PalavraDigitadaComLinhasPanel(controller.getPalavraLength());
        BotoesLetrasEmbaralhadasPanel botoesPanel = new BotoesLetrasEmbaralhadasPanel(controller.getTratamentoPalavra());

        char letra = controller.getTratamentoPalavra().getPalavra().charAt(0);
        controller.onBotaoLetraClicked(letra, 0);
        controller.atualizarUI(panel, botoesPanel);

        boolean estadoBotao = botoesPanel.getEstadoBotao(0);
        System.out.println("Teste botão letra apertado: " + (estadoBotao ? "OK" : "FAIL"));
    }
}

