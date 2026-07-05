public class Rastreador extends Explorador {

    public Rastreador(String nome, int nivel, int energia, Missao missao) {
        super(nome, "Rastreador", nivel, energia, missao);
    }

    @Override
    public Double executarMissao() {
        // Cálculo de pontuação baseado na missão sem estruturas condicionais internas
        double pontos = (getNivel() * 10.0) + (getMissao().getDificuldade() * 5.0) + getEnergia();
        System.out.println("Pontos obtidos por " + getNome() + ": " + pontos);
        System.out.println();
        return pontos;
    }
}