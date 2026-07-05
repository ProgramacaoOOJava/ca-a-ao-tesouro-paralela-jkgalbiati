public class Saqueador extends Explorador {

    public Saqueador(String nome, int nivel, int energia, Missao missao) {
        super(nome, "Saqueador", nivel, energia, missao);
    }

    @Override
    public Double executarMissao() {
        // Lógica de cálculo distinta para o Saqueador
        double pontos = (getNivel() * 12.0) + (getMissao().getDificuldade() * 7.0) + (getEnergia() * 0.5);
        System.out.println("Pontos obtidos por " + getNome() + ": " + pontos);
        System.out.println();
        return pontos;
    }
}