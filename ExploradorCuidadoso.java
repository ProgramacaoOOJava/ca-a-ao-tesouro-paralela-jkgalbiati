import java.util.concurrent.Semaphore;

public class ExploradorCuidadoso extends Explorador implements Runnable {

    public ExploradorCuidadoso(String nome, int nivel, int prioridade, Tarefa tarefa, Semaphore semaforo) {
        super(nome, "Cuidadoso", nivel, prioridade, tarefa, semaforo);
    }

    @Override
    public void executarTarefa() {
        System.out.println(getNome() + " iniciou a tarefa: " + getTarefa().getDescricao() + " no terreno [" + getTarefa().getLocal() + "].");
        System.out.println("Status: Realizando tarefa com cautela!");
        
        try {
            // Exploradores cuidadosos demoram um pouco mais analisando o mapa
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            System.err.println(getNome() + " foi interrompido.");
        }
        
        System.out.println(">> " + getNome() + " finalizou o mapeamento seguro!");
    }

    @Override
    public void run() {
        exibirStatus();
        try {
            System.out.println(getNome() + " aguardando permissão do semáforo...");
            getSemaforo().acquire(); // Solicita vaga
            
            executarTarefa(); // Região Crítica
            
        } catch (InterruptedException e) {
            System.err.println("Erro na execução da thread cuidadosa: " + e.getMessage());
        } finally {
            System.out.println("<< " + getNome() + " liberando vaga no semáforo.");
            getSemaforo().release(); // Libera vaga
        }
    }
}