import java.util.concurrent.Semaphore;

public abstract class Explorador {
    private String nome;
    private String especialidade;
    private int nivel;
    private int prioridade;
    private Tarefa tarefa; // Agora aponta para o objeto imutável
    private Semaphore semaforo; // Mecanismo de controle de concorrência

    public Explorador(String nome, String especialidade, int nivel, int prioridade, Tarefa tarefa, Semaphore semaforo) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
        this.semaforo = semaforo;
    }

    public abstract void executarTarefa();

    public void exibirStatus() {
        System.out.println("Nome: " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Nível: " + nivel);
        System.out.println("Prioridade: " + prioridade);
        if (tarefa != null) {
            System.out.println("Tarefa: " + tarefa.getDescricao() + " (Dificuldade " + tarefa.getDificuldade() + ")");
        }
        System.out.println("Status: Iniciando exploração...");
        System.out.println();
    }

    // Getters seguros para as subclasses e gerenciador
    public String getNome() { return nome; }
    public String getEspecialidade() { return especialidade; }
    public Tarefa getTarefa() { return tarefa; }
    public Semaphore getSemaforo() { return semaforo; }
}