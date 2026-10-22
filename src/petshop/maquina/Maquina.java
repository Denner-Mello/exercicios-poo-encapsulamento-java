package petshop.maquina;

public class Maquina {
    //Atributos
    private int nivelAgua;
    private int nivelShampoo;
    private boolean petBanhando;
    private boolean limpa;
    private Pet pet;

    //Construtor
    public Maquina() {
        this.setLimpa(true);
        this.setNivelAgua(0);
        this.setNivelShampoo(0);
    }

    //Métodos acessores
    public int getNivelAgua() {
        return nivelAgua;
    }

    private void setNivelAgua(int nivelAgua) {
        this.nivelAgua = nivelAgua;
    }

    public int getNivelShampoo() {
        return nivelShampoo;
    }

    private void setNivelShampoo(int nivelShampoo) {
        this.nivelShampoo = nivelShampoo;
    }

    public boolean isPetBanhando() {
        return petBanhando;
    }

    private void setPetBanhando(boolean petBanhando) {
        this.petBanhando = petBanhando;
    }

    public boolean isLimpa() {
        return limpa;
    }

    private void setLimpa(boolean limpa) {
        this.limpa = limpa;
    }

    //Métodos públicos

    public void abastecerAgua(){
        if (this.getNivelAgua() == 30) {
            System.out.println("Nível da água está no máximo!");
        }else if (this.getNivelAgua() == 29){
            this.setNivelAgua(this.getNivelAgua()+ 1);
            System.out.println("Abatecendo um litro de água...");
        }else {
            this.setNivelAgua(this.getNivelAgua()+ 2);
            System.out.println("Abastecendo dois litros de água...");
        }
    }
    public void abastecerShampoo(){
        if (this.getNivelShampoo() == 10) {
            System.out.println("Nível de Shampoo está no máximo!");
        }else if (this.getNivelShampoo() == 9){
            setNivelShampoo(this.getNivelShampoo()+ 1);
            System.out.println("Abatecendo um litro de Shampoo...");
        }else {
            setNivelShampoo(this.getNivelShampoo()+ 2);
            System.out.println("Abastecendo dois litros de Shampoo...");
        }
    }

    public void verificarNivelAgua(){

        System.out.println("Nível da Água: "+ this.getNivelAgua()+"L");
    }

    public void verificarNivelShampoo()
    {
        System.out.println("Nível de Shampoo: "+ this.getNivelShampoo()+"L");
    }

    public void verificarPetBanho(){
        if (this.isPetBanhando()){
            System.out.println("Há um pet na máquina!");
        }else {
            System.out.println("Não há nenhum pet na máquina.");
        }
    }

    public void colocarPet(Pet p1){
        if (!this.isLimpa()){
            System.out.println("A máquina precisa estar limpa para entrada de um pet!");
        } else if (this.isPetBanhando()) {
            System.out.println("A máquina precisa estar sem pet para outro entrar!");
        } else {
            this.setPetBanhando(true);
            System.out.println("Colocando animal...");
            this.pet = p1;
        }
    }

    public void retirarPet(){
        if (!this.isPetBanhando()){
            System.out.println("Não tem pet para retirar da máquina...");
        } else if (!this.pet.isLimpo()) {
            System.out.println("O pet não está limpo, lave a máquina para a entrada de outro animal!");
            this.setPetBanhando(false);
            this.setLimpa(false);
            this.pet = null;

        }else {
            System.out.println("Retirando pet da máquina...");
            this.setPetBanhando(false);
            this.pet = null;
        }
    }

    public void banharPet(){
        if (!this.isPetBanhando()){
            System.out.println("A máquina está sem pet!");
        } else if (!this.isLimpa()) {
            System.out.println("Limpe a máquina para colocar um pet!");
        } else if (this.getNivelAgua() < 10) {
        System.out.println("Nível de água insuficiente.");
    } else if (this.getNivelShampoo() < 2) {
        System.out.println("Nível de Shampoo insuficiente.");
    } else {
            this.pet.setLimpo(true);
            System.out.println("Limpando pet...");
            this.setNivelAgua(this.getNivelAgua() - 10);
            this.setNivelShampoo(this.getNivelShampoo() - 2);
        }
    }


    public void limparMaquina(){
        if (this.isLimpa()){
            System.out.println("A máquina já está limpa.");
        } else if (this.isPetBanhando()){
            System.out.println("A máquina está ocupada.");
        } else if (this.getNivelAgua() < 3) {
            System.out.println("Nível de água insuficiente.");
        } else if (this.getNivelShampoo() < 1) {
            System.out.println("Nível de Shampoo insuficiente.");
        }else {
            System.out.println("Limpando a máquina...");
            this.setNivelAgua(this.getNivelAgua() - 3);
            this.setNivelShampoo(this.getNivelShampoo() - 1);
            this.setLimpa(true);
        }
    }
}
