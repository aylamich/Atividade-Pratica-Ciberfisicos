/* TAREFA ESCOLHIDA: SOMAR NÚMEROS
Cada thread realiza a soma dos números de 1 até 1.000.000 e vai repetindo o cálculo 10 vezes para aumentar a carga de CPU */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ModeloMuitosPraPoucos {
    private static final int TAMANHO_TAREFA = 1_000_000;  //  números de cada tarefa soma
    private static final int REPETICOES = 10; // cada tarefa repete a soma 10 vezes
    private static final int THREADS_SISTEMA = 4; // M = número fixo de threads do SO
    // Classe interna que representa uma tarefa leve/thread de usuário
    static class TarefaSoma implements Runnable {
        @Override
        public void run() {
            for (int r = 0; r < REPETICOES; r++) {
                long soma = 0;
                for (int i = 1; i <= TAMANHO_TAREFA; i++) {
                    soma += i;
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Verifica se foi passado o número de tarefas
        if (args.length != 1) {
            System.out.println("Uso: java ModeloMuitosPraPoucos <quantidade_tarefas>");
            return;
        }
        int quantidadeTarefas = Integer.parseInt(args[0]);

        /* Muitas tarefas (N) mapeadas em poucas threads reais (M = 4)
           Usando pool fixo */
        ExecutorService pool = Executors.newFixedThreadPool(THREADS_SISTEMA);
        List<Future<?>> futures = new ArrayList<>();
        // Inicio medição do tempo com System.nanoTime()
        long inicio = System.nanoTime();
        // ***N tarefas são distribuídas entre M threads reais
        for (int i = 0; i < quantidadeTarefas; i++) {
            futures.add(pool.submit(new TarefaSoma()));
        }
        // Espera todas as tarefas terminarem f.get() bloqueia até a tarefa concluir, equivalente a join()
        for (Future<?> f : futures) {
            f.get();
        }
        // Encerra o pool de threads
        pool.shutdown();
        // Medição do tempo fim
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;
        // RESULTADO
        System.out.printf("Modelo N:M (%d tarefas em %d threads): %.2f ms%n",
                quantidadeTarefas, THREADS_SISTEMA, tempoMs);
    }
}