package contabancaria;


import java.util.Random;

public class Conta {

    private final Random random = new Random();

    //ATRIBUTOS
    private final int numConta;
    private double saldo;
    private double cheque;
    private boolean usandoCheque;
    private boolean contaAberta;

    //CONSTRUTOR
    public Conta() {
        this.numConta = random.nextInt(9000) + 1000;

    }

    //MÉTODOS ACESSORES

    public int getNumConta() {
        return numConta;
    }

    public double getSaldo() {

        return saldo;
    }

    private void setSaldo(double saldo) {

        this.saldo = saldo;
    }

    public double getCheque() {

        return cheque;
    }

    private void setCheque(double cheque) {

        this.cheque = cheque;
    }

    private double getLimiteDisponivel(){
        return this.getSaldo() + this.getCheque();
    }

    public boolean getUsandoCheque() {

        return usandoCheque;
    }

    private void setUsandoCheque(boolean usandoCheque) {

        this.usandoCheque = usandoCheque;
    }

    public boolean getContaAberta() {
        return contaAberta;
    }

    private void setContaAberta(boolean contaAberta) {
        this.contaAberta = contaAberta;
    }

    //MÉTODOS PÚBLICOS

    public void abrirConta(double s){
        this.setSaldo(s);
        if (this.getSaldo() < 0){
            System.out.println("Impossível abrir conta com saldo negativo.");
        } else if (this.getSaldo() <= 500) {
            setCheque(50);
            System.out.println("PARABÉNS, CONTA "+ getNumConta() +" ABERTA!!!");
            System.out.printf("\nSaldo: %.2f R$", this.getSaldo());
            System.out.printf("\nCheque especial: %.2f R$", this.getCheque());
            this.setContaAberta(true);
        }else {
            setCheque(getSaldo() / 2);
            System.out.println("PARABÉNS, CONTA ABERTA!!!");
            System.out.printf("\nSaldo: %.2f R$", this.getSaldo());
            System.out.printf("\nCheque especial: %.2f R$", this.getCheque());
            this.setContaAberta(true);
        }

    }
    public void consultarSaldo(){
        if (this.getContaAberta()){
            System.out.printf("Saldo: %.2f R$", this.getSaldo());
        }else {
            System.out.println("Impossível consultar saldo em conta!");
        }
    }
    public void consultarCheque(){
        if (this.getContaAberta()){
            System.out.printf("\nCheque especial: %.2f R$\n", this.getCheque());
        }else {
            System.out.println("Impossível consultar cheque especial!");
        }
    }
    public void depositar(double deposito){
        if (deposito > 0 && this.getContaAberta()){
            this.setSaldo(this.getSaldo() + deposito);
            System.out.printf("\nDeposito de %.2f R$ efetuado com sucesso.\n", deposito);
        }else {
            System.out.println("Impossível efetuar depósito.");
        }
        if (this.getSaldo() >= 0){
            this.setUsandoCheque(false);
        }
    }
    public void sacar(double saque){
        if (saque > 0 && this.getContaAberta() && saque <= this.getSaldo()){
            this.setSaldo(this.getSaldo() - saque);
            System.out.printf("Saque de %.2f R$ efetuado com sucesso.\n", saque);

        } else if (saque > 0 && this.getContaAberta() && saque <= this.getLimiteDisponivel() ) {
            this.setSaldo(this.getSaldo() - saque);
            System.out.printf("Saque de %.2f R$ efetuado com sucesso.\n", saque);

            double taxa = (this.getSaldo() * 0.2);
            setSaldo(this.getSaldo() + taxa);
            System.out.println("Taxa de 20% do valor de cheque especial utilizado: "+ Math.abs(taxa));
            this.setUsandoCheque(true);
        }else {
            System.out.println("Impossível efetuar saque.");
        }
    }
    public void pagarBoleto(double boleto){
        if (boleto > 0 && this.getContaAberta() && boleto <= this.getSaldo()){
            this.setSaldo(this.getSaldo() - boleto);
            System.out.printf("boleto de %.2f R$ pago com sucesso.\n", boleto);

        } else if (boleto > 0 && this.getContaAberta() && boleto <= this.getLimiteDisponivel() ) {
            this.setSaldo(this.getSaldo() - boleto);
            System.out.printf("boleto de %.2f R$ pago com sucesso.\n", boleto);

            double taxa = (this.getSaldo() * 0.2);
            setSaldo(this.getSaldo() + taxa);
            System.out.println("Taxa de 20% do valor de cheque especial utilizado: "+ Math.abs(taxa));
            this.setUsandoCheque(true);
        }else {
            System.out.println("Impossível efetuar pagamento.");
        }
    }


}
