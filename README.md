# Atividade: Comparação 1:1 vs N:M
Comparação de Desempenho entre Modelos de Threads N:M e 1:1
## Tarefa
Somar números de 1 até 1.000.000, 10 vezes por thread/tarefa.

## Tabela de Resultados

| Quantidade | Modelo 1:1 (ms) | Modelo N:M (ms) |
|------------|------------------|------------------|
| 10         | 30,77           | 29,11           |
| 100        | 60,09           | 27,20           |
| 500        | 98,90           | 28,90           |
| 1000       | 156,29          | 28,17           |

## Relatório Simples

**O que fizemos:**  
Criamos duas versões da mesma tarefa (somar de 1 a 1.000.000, 10 vezes por thread):

- **1:1**: cada thread de usuário = 1 thread real do sistema  
- **N:M**: muitas tarefas → executadas em apenas 4 threads reais (pool)

**Resultados principais:**

- Com **poucas tarefas (10–100)**: os dois modelos são parecidos.
- Com **muitas tarefas (500–1000)**:
  - **1:1** fica mais lento (156 ms)
  - **N:M** continua rápido (~28 ms)

**Conclusão:**  
O modelo **N:M com pool fixo** é **muito melhor** quando temos muitas tarefas, porque evita criar milhares de threads reais (que gastam memória e CPU).  
O **1:1** é bom só com poucas threads.

**Vantagem do N:M:** escalabilidade com alta concorrência.
