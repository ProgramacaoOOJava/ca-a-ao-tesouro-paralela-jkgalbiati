public abstract class Explorador {
    private String nome;
    private String tipo;
    private int prioridade;
    private String tarefa;

    public Explorador(String nome, String tipo, int prioridade, String tarefa) {
        this.nome = nome;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
    }

    public abstract void executarTarefa() throws TarefaInvalidaException;

    public void exibirStatus() {
        System.out.println("Explorador: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Tarefa: " + (tarefa == null || tarefa.isEmpty() ? "Nenhuma" : tarefa));
        System.out.println("Status: Iniciando exploração...");
    }

    // Getters para acesso seguro externos à classe
    public String getNome() { return nome; }
    public String getTarefa() { return tarefa; }
}