import java.util.concurrent.Callable;

public abstract class Explorador implements Callable<Double> {
    private String nome;
    private String especialidade;
    private int nivel;
    private int energia;
    private Missao missao;

    public Explorador(String nome, String especialidade, int nivel, int energia, Missao missao) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.energia = energia;
        this.missao = missao;
    }

    public abstract Double executarMissao();

    @Override
    public Double call() throws Exception {
        exibirStatus();
        return executarMissao();
    }

    public void exibirStatus() {
        System.out.println("Explorador: " + nome);
        System.out.println("Especialidade: " + especialidade);
        if (missao != null) {
            System.out.println("Missão: " + missao.getDescricao());
        }
    }

    // Getters
    public String getNome() { return nome; }
    public String getEspecialidade() { return especialidade; }
    public int getNivel() { return nivel; }
    public int getEnergia() { return energia; }
    public Missao getMissao() { return missao; }
}