import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ForkJoinPool;

public class CacaAoTesouroParalela {
    public static void main(String[] args) {
        System.out.println("=== DESAFIO CAÇA AO TESOURO PARALELA: NÍVEL MESTRE ===");
        System.out.println();

        // Criando as Missões imutáveis
        Missao m1 = new Missao("Mapear cavernas", "Gruta Profunda", 6);
        Missao m2 = new Missao("Recuperar artefatos", "Templo Perdido", 8);
        Missao m3 = new Missao("Investigar ruínas", "Cidades de Pedra", 5);
        Missao m4 = new Missao("Decifrar glifos", "Altar Ancestral", 7);

        // Lista polimórfica de exploradores
        ArrayList<Explorador> exploradores = new ArrayList<>();
        exploradores.add(new Rastreador("Lina", 5, 40, m1));
        exploradores.add(new Saqueador("Drogan", 6, 64, m2));
        exploradores.add(new Rastreador("Arin", 4, 50, m3));
        exploradores.add(new Saqueador("Kael", 5, 30, m4));

        // Criando o Pool de Threads fixo em tamanho 2
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ArrayList<Future<Double>> resultadosFuturos = new ArrayList<>();

        // Submetendo os Callables ao serviço concorrente
        for (Explorador exp : exploradores) {
            resultadosFuturos.add(executor.submit(exp));
        }

        // Vetor para consolidar as notas posteriormente
        Double[] pontosColetados = new Double[resultadosFuturos.size()];

        try {
            for (int i = 0; i < resultadosFuturos.size(); i++) {
                // Bloqueia até que a thread termine e traz o resultado matemático
                pontosColetados[i] = resultadosFuturos.get(i).get();
            }
        } catch (Exception e) {
            System.err.println("Erro na coleta de resultados: " + e.getMessage());
        } finally {
            // Desligamento obrigatório do pool de threads
            executor.shutdown();
        }

        // Consolidando os dados usando o ForkJoinPool paralelo escalável
        ForkJoinPool poolForkJoin = new ForkJoinPool();
        SomaPontos tarefaPrincipal = new SomaPontos(pontosColetados, 0, pontosColetados.length);
        
        double somaTotal = poolForkJoin.invoke(tarefaPrincipal);

        System.out.println("----------------------------------------");
        System.out.println("Soma total dos pontos: " + somaTotal);
    }
}