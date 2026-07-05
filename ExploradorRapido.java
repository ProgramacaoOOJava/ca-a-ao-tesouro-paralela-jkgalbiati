public class ExploradorRapido extends Explorador implements Runnable {

    public ExploradorRapido(String nome, int prioridade, String tarefa) {
        super(nome, "Rápido", prioridade, tarefa);
    }

    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        // Validação obrigatória da tarefa
        if (getTarefa() == null || getTarefa().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }
        System.out.println("Explorador: " + getNome() + " | Tipo: Rápido | Status: Vasculando a caverna rapidamente para concluir: '" + getTarefa() + "'!");
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