import java.util.ArrayList;

public class CacaAoTesouroParalela { // Nome igual ao do arquivo
    public static void main(String[] args) {
        System.out.println("=== INICIANDO CAÇA AO TESOURO PARALELA ===");
        System.out.println();

        // Lista dinâmica para gerenciar o ciclo de execução paralela
        ArrayList<Thread> linhaDeExploracao = new ArrayList<>();

        // Instanciando os exploradores conforme as regras do desafio
        ExploradorRapido ex1 = new ExploradorRapido("Alice", Thread.MAX_PRIORITY, "Vasculhar a caverna");
        ExploradorRapido ex2 = new ExploradorRapido("Clara", Thread.MAX_PRIORITY, ""); // Gerará exceção controlada
        ExploradorCuidadoso ex3 = new ExploradorCuidadoso("Bob", Thread.MIN_PRIORITY, "Mapear a floresta");
        ExploradorCuidadoso ex4 = new ExploradorCuidadoso("Diego", Thread.MIN_PRIORITY, "Analisar runas antigas");

        // Construindo e encapsulando as instâncias em Workers do tipo Thread
        Thread t1 = new Thread(ex1);
        Thread t2 = new Thread(ex2);
        Thread t3 = new Thread(ex3);
        Thread t4 = new Thread(ex4);

        // Ajustando os níveis de prioridade do scheduler do Java
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);
        t4.setPriority(Thread.MIN_PRIORITY);

        // Configurando a thread do Diego como daemon (tarefa em segundo plano)
        t4.setDaemon(true);

        // Armazenando na lista encadeada obrigatória
        linhaDeExploracao.add(t1);
        linhaDeExploracao.add(t2);
        linhaDeExploracao.add(t3);
        linhaDeExploracao.add(t4);

        // Disparando a busca síncrona/concorrente na matriz da ilha misteriosa
        for (Thread thread : linhaDeExploracao) {
            thread.start();
        }
    }
}