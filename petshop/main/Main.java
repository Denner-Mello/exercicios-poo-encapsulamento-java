package petshop.main;

import petshop.maquina.Maquina;
import petshop.maquina.Pet;

public class Main {
    public static void main(String[] args) {
        Maquina maquina = new Maquina();

        System.out.println("=== 1. TESTE DE ABASTECIMENTO E NÍVEIS INICIAIS ===");
        maquina.verificarNivelAgua();
        maquina.verificarNivelShampoo();
        maquina.verificarPetBanho();

        System.out.println("\n-- Abastecendo a máquina até a capacidade máxima --");
        for (int i = 0; i < 15; i++) {
            maquina.abastecerAgua(); // +2L por chamada até atingir 30L
        }
        for (int i = 0; i < 5; i++) {
            maquina.abastecerShampoo(); // +2L por chamada até atingir 10L
        }

        // Tentativa de transbordo (deve indicar nível máximo)
        maquina.abastecerAgua();
        maquina.abastecerShampoo();

        maquina.verificarNivelAgua();
        maquina.verificarNivelShampoo();

        System.out.println("\n=== 2. FLUXO FELIZ: BANHO COMPLETO (APOLLO) ===");
        Pet apollo = new Pet("Apollo", "Cachorro");
        System.out.println("Status inicial do pet - Nome: " + apollo.getNome() + " | Limpo: " + apollo.isLimpo());

        maquina.colocarPet(apollo);
        maquina.verificarPetBanho();

        maquina.banharPet();
        System.out.println("Status após banho - Nome: " + apollo.getNome() + " | Limpo: " + apollo.isLimpo());

        maquina.retirarPet();
        maquina.verificarPetBanho();

        // Deve ter consumido 10L de água e 2L de shampoo
        maquina.verificarNivelAgua(); // Restam 20L
        maquina.verificarNivelShampoo(); // Restam 8L

        System.out.println("\n=== 3. FLUXO DE ALERTA: RETIRADA SEM BANHO (THOR) ===");
        Pet thor = new Pet("Thor", "Gato");
        maquina.colocarPet(thor);

        System.out.println("-- Retirando Thor antes de dar banho --");
        maquina.retirarPet(); // Marca a máquina como limpa = false

        System.out.println("\n-- Tentando colocar Belinha na máquina suja --");
        Pet belinha = new Pet("Belinha", "Cachorro");
        maquina.colocarPet(belinha); // Entrada deve ser bloqueada!

        System.out.println("\n=== 4. HIGIENIZAÇÃO DA MÁQUINA E NOVO ATENDIMENTO ===");
        maquina.limparMaquina(); // Consome 3L de água e 1L de shampoo (Restam 17L e 7L)
        maquina.verificarNivelAgua();
        maquina.verificarNivelShampoo();

        System.out.println("\n-- Tentando colocar Belinha após a higienização --");
        maquina.colocarPet(belinha);
        maquina.banharPet();
        maquina.retirarPet();
        System.out.println("Status final - Nome: " + belinha.getNome() + " | Limpo: " + belinha.isLimpo());
    }
}