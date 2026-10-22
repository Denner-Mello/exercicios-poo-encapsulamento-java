package petshop.maquina;

public class Pet {
    //Atributos
    private String nome;
    private String especie;
    private boolean limpo;
    //Construtor
    public Pet(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
    }
    //Metodos acessores

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    private void setEspecie(String especie) {
        this.especie = especie;
    }

    public boolean isLimpo() {
        return limpo;
    }

    protected void setLimpo(boolean limpo) {
        this.limpo = limpo;
    }

    //Métodos públicos
    public void status(){
        System.out.println("Nome do pet: "+ this.getNome());
        System.out.println("Espécie: "+ this.getEspecie());
        System.out.println("Está limpo: "+ this.isLimpo());
    }
}
