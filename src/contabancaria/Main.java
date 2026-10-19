package contabancaria;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   BATERIA DE TESTES DE ESTRESSE - CONTA BANCÁRIA ");
        System.out.println("==================================================\n");

        // ----------------------------------------------------
        // TESTE 1: Operações com Conta Fechada (Bloqueios de Estado)
        // ----------------------------------------------------
        System.out.println("--- [TESTE 1] Tentativa de operações sem abrir a conta ---");
        Conta c1 = new Conta();
        c1.consultarSaldo();
        c1.consultarCheque();
        c1.depositar(100);
        c1.sacar(50);
        c1.pagarBoleto(30);

        // ----------------------------------------------------
        // TESTE 2: Abertura Inválida (Saldo Inicial Negativo)
        // ----------------------------------------------------
        System.out.println("\n--- [TESTE 2] Tentativa de abertura com saldo negativo ---");
        c1.abrirConta(-50);
        System.out.println("Conta está aberta? " + c1.getContaAberta());

        // ----------------------------------------------------
        // TESTE 3: Regra de Fronteira do Cheque Especial (<= 500 vs > 500)
        // ----------------------------------------------------
        System.out.println("\n--- [TESTE 3] Abertura no limite exato de R$ 500,00 ---");
        c1.abrirConta(500);
        System.out.println("Usando cheque especial? " + c1.getUsandoCheque());

        Conta c2 = new Conta();
        System.out.println("\n--- Abertura acima de R$ 500,00 (R$ 1000,00) ---");
        c2.abrirConta(1000);

        // ----------------------------------------------------
        // TESTE 4: Operações com Valores Inválidos (<= 0)
        // ----------------------------------------------------
        System.out.println("\n--- [TESTE 4] Tentativas de transações com valores <= 0 ---");
        c1.depositar(0);
        c1.depositar(-100);
        c1.sacar(0);
        c1.sacar(-50);
        c1.pagarBoleto(0);
        c1.pagarBoleto(-20);

        // ----------------------------------------------------
        // TESTE 5: Saque do Saldo Total Disponível (Sem usar cheque)
        // ----------------------------------------------------
        System.out.println("\n--- [TESTE 5] Saque do saldo total disponível ---");
        c1.sacar(500);
        c1.consultarSaldo();
        System.out.println("Usando cheque especial? " + c1.getUsandoCheque());

        // ----------------------------------------------------
        // TESTE 6: Entrada no Cheque Especial + Taxa de 20%
        // ----------------------------------------------------
        System.out.println("\n--- [TESTE 6] Saque utilizando Cheque Especial ---");
        c1.sacar(40);
        c1.consultarSaldo();
        System.out.println("Usando cheque especial? " + c1.getUsandoCheque());

        // ----------------------------------------------------
        // TESTE 7: Pagamento de Boleto com Conta Negativada
        // ----------------------------------------------------
        System.out.println("\n--- [TESTE 7] Pagamento de boleto com saldo negativo ---");
        c1.pagarBoleto(1.50);
        c1.consultarSaldo();

        // ----------------------------------------------------
        // TESTE 8: Recusa de Transação Além do Limite Total
        // ----------------------------------------------------
        System.out.println("\n--- [TESTE 8] Tentativa de ultrapassar limite total ---");
        c1.sacar(50);

        // ----------------------------------------------------
        // TESTE 9: Depósito Parcial vs Quitação Total do Cheque
        // ----------------------------------------------------
        System.out.println("\n--- [TESTE 9A] Depósito Parcial (Reduz a dívida, mas não quita) ---");
        c1.depositar(20);
        c1.consultarSaldo();
        System.out.println("Usando cheque especial? " + c1.getUsandoCheque());

        System.out.println("\n--- [TESTE 9B] Depósito Total (Quitação da dívida) ---");
        c1.depositar(100);
        c1.consultarSaldo();
        System.out.println("Usando cheque especial? " + c1.getUsandoCheque());
    }
}