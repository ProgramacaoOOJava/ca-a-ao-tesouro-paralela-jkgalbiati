public final class Missao {
    private final String descricao;
    private final String local;
    private final int dificuldade;

    public Missao(String descricao, String local, int dificuldade) {
        this.descricao = descricao;
        this.local = local;
        this.dificuldade = dificuldade;
    }

    public String getDescricao() { return descricao; }
    public String getLocal() { return local; }
    public int getDificuldade() { return dificuldade; }
}