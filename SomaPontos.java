import java.util.concurrent.RecursiveTask;

public class SomaPontos extends RecursiveTask<Double> {
    private final Double[] pontos;
    private final int inicio;
    private final int fim;

    public SomaPontos(Double[] pontos, int inicio, int fim) {
        this.pontos = pontos;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    protected Double compute() {
        // Se o intervalo for menor ou igual a 2 elementos, faz a soma direta
        if ((fim - inicio) <= 2) {
            double soma = 0;
            for (int i = inicio; i < fim; i++) {
                soma += pontos[i];
            }
            return soma;
        } else {
            // Divide o intervalo em duas subtarefas (Divide and Conquer)
            int meio = inicio + (fim - inicio) / 2;
            SomaPontos tarefaEsquerda = new SomaPontos(pontos, inicio, meio);
            SomaPontos tarefaDireita = new SomaPontos(pontos, meio, fim);

            tarefaEsquerda.fork(); // Executa em paralelo de forma assíncrona
            double resultadoDireita = tarefaDireita.compute(); // Executa a segunda metade na thread atual
            double resultadoEsquerda = tarefaEsquerda.join();  // Aguarda e junta o resultado

            return resultadoEsquerda + resultadoDireita;
        }
    }
}