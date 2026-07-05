import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CacaAoTesouroParalela {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO MISSÃO COOPERATIVA: NÍVEL AVENTUREIRO ===");
        System.out.println();

        // 1. Instanciando o Semáforo limitador (Apenas 2 exploradores ativos simultaneamente)
        Semaphore semaforoCompartilhado = new Semaphore(2);

        // 2. Criando os 4 objetos de Tarefa imutáveis
        Tarefa t1 = new Tarefa("Mapear Caverna", "Caverna dos Eco", 7);
        Tarefa t2 = new Tarefa("Explorar Ruínas", "Ruínas Sagradas", 6);
        Tarefa t3 = new Tarefa("Desarmar Armadilhas", "Templo Antigo", 8);
        Tarefa t4 = new Tarefa("Coletar Artefato", "Altar de Eldoria", 5);

        // 3. Criando exploradores vinculados às suas tarefas e ao semáforo
        ExploradorRapido ex1 = new ExploradorRapido("Alice", 5, Thread.MAX_PRIORITY, t1, semaforoCompartilhado);
        ExploradorRapido ex2 = new ExploradorRapido("Clara", 3, Thread.MAX_PRIORITY, t3, semaforoCompartilhado);
        ExploradorCuidadoso ex3 = new ExploradorCuidadoso("Bob", 4, Thread.MIN_PRIORITY, t2, semaforoCompartilhado);
        ExploradorCuidadoso ex4 = new ExploradorCuidadoso("Diego", 6, Thread.MIN_PRIORITY, t4, semaforoCompartilhado);

        // ArrayList para gerenciar as threads em execução
        ArrayList<Thread> linhaDeExploracao = new ArrayList<>();

        Thread tr1 = new Thread(ex1);
        Thread tr2 = new Thread(ex2);
        Thread tr3 = new Thread(ex3);
        Thread tr4 = new Thread(ex4);

        // Configurando prioridades distintas
        tr1.setPriority(Thread.MAX_PRIORITY);
        tr2.setPriority(Thread.MAX_PRIORITY);
        tr3.setPriority(Thread.MIN_PRIORITY);
        tr4.setPriority(Thread.MIN_PRIORITY);

        // Marcando uma das threads como daemon secundária
        tr4.setDaemon(true);

        // Guardando na lista
        linhaDeExploracao.add(tr1);
        linhaDeExploracao.add(tr2);
        linhaDeExploracao.add(tr3);
        linhaDeExploracao.add(tr4);

        // Inicializando todos os exploradores em paralelo
        for (Thread thread : linhaDeExploracao) {
            thread.start();
        }
    }
}