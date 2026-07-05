public class ExploradorCuidadoso extends Explorador implements Runnable {

    public ExploradorCuidadoso(String nome, int prioridade, String tarefa) {
        super(nome, "Cuidadoso", prioridade, tarefa);
    }

    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        // Validação obrigatória da tarefa
        if (getTarefa() == null || getTarefa().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }
        System.out.println("Explorador: " + getNome() + " | Tipo: Cuidadoso | Status: Mapeando a floresta com cautela e registrando: '" + getTarefa() + "'.");
    }

    @Override
    public void run() {
        exibirStatus();
        try {
            executarTarefa();
        } catch (TarefaInvalidaException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}