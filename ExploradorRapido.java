import java.util.concurrent.Semaphore;

public class ExploradorRapido extends Explorador implements Runnable {

    public ExploradorRapido(String nome, int nivel, int prioridade, Tarefa tarefa, Semaphore semaforo) {
        super(nome, "Rápido", nivel, prioridade, tarefa, semaforo);
    }

    @Override
    public void executarTarefa() {
        System.out.println(getNome() + " iniciou tarefa: " + getTarefa().getDescricao() + " no local [" + getTarefa().getLocal() + "].");
        System.out.println("Status: Executando tarefa rapidamente!");
        
        try {
            // Simula o tempo gasto executando a tarefa em paralelo
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            System.err.println(getNome() + " foi interrompido.");
        }
        
        System.out.println(">> " + getNome() + " concluiu a varredura da área!");
    }

    @Override
    public void run() {
        exibirStatus();
        try {
            System.out.println(getNome() + " aguardando permissão do semáforo...");
            getSemaforo().acquire(); // Solicita uma das 2 vagas livres
            
            executarTarefa(); // Região Crítica
            
        } catch (InterruptedException e) {
            System.err.println("Erro na execução da thread rápida: " + e.getMessage());
        } finally {
            System.out.println("<< " + getNome() + " liberando vaga no semáforo.");
            getSemaforo().release(); // Libera a permissão para o próximo explorador
        }
    }
}