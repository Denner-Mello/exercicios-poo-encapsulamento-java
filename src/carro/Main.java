package carro;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== CRIANDO O VEÍCULO ===");
        Carro meuCarro = new Carro("Toyota", "Corolla");
        meuCarro.status();
        System.out.println();

        // 1. TESTE DE AÇÕES COM O CARRO DESLIGADO
        System.out.println("=== 1. BATERIA DE TESTES: CARRO DESLIGADO ===");
        meuCarro.acelerarCarro();
        meuCarro.desacelerarCarro();
        meuCarro.passarMarcha();
        meuCarro.descerMarcha();
        meuCarro.virarParaEsquerda();
        meuCarro.desligarCarro();
        System.out.println();

        // 2. TESTE DE PARTIDA E PONTO MORTO
        System.out.println("=== 2. BATERIA DE TESTES: LIGAR E PONTO MORTO ===");
        meuCarro.ligarCarro();
        meuCarro.ligarCarro(); // Tentando ligar novamente
        meuCarro.acelerarCarro();
        meuCarro.virarParaDireita();
        meuCarro.status();
        System.out.println();

        // 3. TESTE DE SUBIDA DE MARCHAS, FRONTEIRAS DE ACELERAÇÃO E CURVAS
        System.out.println("=== 3. BATERIA DE TESTES: SUBIDA DE MARCHAS E ACELERAÇÃO ===");
        meuCarro.passarMarcha(); // Marcha 1 (vel: 0 km/h)
        meuCarro.status();

        System.out.println("\n-- Acelerando até o limite da 1ª marcha (20 km/h) --");
        for (int i = 0; i < 20; i++) {
            meuCarro.acelerarCarro();
        }
        meuCarro.status();
        meuCarro.acelerarCarro(); // Tentando ultrapassar 20 km/h na 1ª marcha

        System.out.println("\n-- Testando manobra em velocidade permitida (20 km/h) --");
        meuCarro.virarParaEsquerda();

        System.out.println("\n-- Subindo até a 6ª marcha --");
        meuCarro.passarMarcha(); // Marcha 2 (vel: 21 km/h)
        meuCarro.passarMarcha(); // Marcha 3 (vel: 41 km/h)

        System.out.println("\n-- Tentando virar acima do limite (41 km/h) --");
        meuCarro.virarParaDireita();

        meuCarro.passarMarcha(); // Marcha 4 (vel: 61 km/h)
        meuCarro.passarMarcha(); // Marcha 5 (vel: 81 km/h)
        meuCarro.passarMarcha(); // Marcha 6 (vel: 101 km/h)
        meuCarro.passarMarcha(); // Tentando passar da 6ª marcha

        System.out.println("\n-- Acelerando até o limite máximo absoluto (120 km/h) --");
        for (int i = 0; i < 19; i++) {
            meuCarro.acelerarCarro();
        }
        meuCarro.status();
        meuCarro.acelerarCarro(); // Tentando ultrapassar 120 km/h na 6ª marcha
        System.out.println();

        // 4. TESTE DE REDUÇÃO DE MARCHAS E DESACELERAÇÃO
        System.out.println("=== 4. BATERIA DE TESTES: DESACELERAÇÃO E REDUÇÃO ===");
        meuCarro.descerMarcha(); // Marcha 5 (vel: 100 km/h)
        meuCarro.status();

        meuCarro.descerMarcha(); // Marcha 4 (vel: 80 km/h)
        meuCarro.descerMarcha(); // Marcha 3 (vel: 60 km/h)
        meuCarro.descerMarcha(); // Marcha 2 (vel: 40 km/h)
        meuCarro.descerMarcha(); // Marcha 1 (vel: 20 km/h)

        System.out.println("\n-- Desacelerando até 0 km/h na 1ª marcha --");
        for (int i = 0; i < 20; i++) {
            meuCarro.desacelerarCarro();
        }
        meuCarro.status();
        meuCarro.desacelerarCarro(); // Tentando desacelerar abaixo de 0 km/h

        meuCarro.descerMarcha(); // Marcha 0 (vel: 0 km/h)
        meuCarro.descerMarcha(); // Tentando descer abaixo do ponto morto
        meuCarro.status();
        System.out.println();

        // 5. TESTE DE CONDIÇÕES DE DESLIGAMENTO
        System.out.println("=== 5. BATERIA DE TESTES: DESLIGAMENTO ===");
        meuCarro.passarMarcha(); // Marcha 1
        meuCarro.desligarCarro(); // Tentando desligar engatado

        meuCarro.descerMarcha(); // Marcha 0 (vel: 0 km/h)
        meuCarro.desligarCarro(); // Desligamento correto
        meuCarro.desligarCarro(); // Tentando desligar novamente
        meuCarro.status();
    }
}
