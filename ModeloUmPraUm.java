/* TAREFA ESCOLHIDA: SOMAR NÚMEROS
Cada thread realiza a soma dos números de 1 até 1.000.000 e vai repetindo o cálculo 10 vezes para aumentar a carga de CPU */

import java.util.ArrayList;
import java.util.List;

public class ModeloUmPraUm {
    private static final int TAMANHO_TAREFA = 1_000_000; // Números somar
    private static final int REPETICOES = 10; // Vezes repetir a soma
    // Classe interna que representa uma tarefa/thread de usuário
    static class TarefaSoma implements Runnable {
        @Override
        public void run() {
            // Repete 10 vezes a soma dos números de 1 até 1.000.000
            for (int r = 0; r < REPETICOES; r++) {
                long soma = 0;
                for (int i = 1; i <= TAMANHO_TAREFA; i++) {
                    soma += i;
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        // Verifica se foi passado o número de threads
        if (args.length != 1) {
            System.out.println("Uso: java ModeloUmPraUm <quantidade_threads>");
            return;
        }
        int quantidade = Integer.parseInt(args[0]);
        List<Thread> threads = new ArrayList<>(); // Cria uma lista vazia para armazenar todas as threads criadas
        // Medição do tempo, usando System.nanoTime()
        long inicio = System.nanoTime();
        // ****Cria múltiplas threads reais (1:1)****
        for (int i = 0; i < quantidade; i++) {
            Thread t = new Thread(new TarefaSoma());  // API padrão Thread + Runnable
            threads.add(t);
            t.start();  // incia
        }
        // Usando join() em loop, assim aguarda conclusão de todas
        for (Thread t : threads) {
            t.join();
        }

        // Medição de tempo fim
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        /* RESULTADO
        %d = número inteiro, %.2f = número com 2 casas decimais, %n = pula linha
         */
        System.out.printf("Modelo 1:1 com %d threads: %.2f ms%n", quantidade, tempoMs);
    }
}