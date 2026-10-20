package carro;

public class Carro {

    //ATRIBUTOS
    private String marca;
    private String modelo;
    private int marcha;
    private int velocidade;
    private boolean ligado;
    private int velocidadeMinima;
    private int velocidadeMaxima;

    //CONSTRUTOR


    public Carro(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.setLigado(false);
        this.setMarcha(0);
        this.setVelocidade(0);
    }

    //METÓDOS ACESSORES
    public String getMarca() {
        return marca;
    }

     public int getVelocidadeMinima(){
        return velocidadeMinima;
    }

    private void setVelocidadeMinima(int velocidadeMinima) {
        this.velocidadeMinima = velocidadeMinima;
    }

    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    private void setVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    private void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    private void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getMarcha() {
        return marcha;
    }

    private void setMarcha(int marcha) {
        this.marcha = marcha;
    }

    public int getVelocidade() {
        return velocidade;
    }

    private void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public boolean isLigado() {
        return ligado;
    }

    private void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    //MÉTODOS PÚBLICOS
    public void virarParaEsquerda(){
        if (!this.isLigado()){
            System.out.println("Carro desligado!");
        } else if (this.getMarcha() == 0) {
            System.out.println("O carro está em ponto morto!");
        }else if (this.getVelocidade() == 0){
            System.out.println("O carro está em velocidade mínima!");
        } else if (this.getVelocidade() > 40) {
            System.out.println("O carro deve estar entre 1 e 40 km/h");
        }else {
            System.out.println("Virando o carro para esquerda!");
        }
    }

    public void virarParaDireita(){
        if (!this.isLigado()){
            System.out.println("Carro desligado!");
        } else if (this.getMarcha() == 0) {
            System.out.println("O carro está em ponto morto!");
        }else if (this.getVelocidade() == 0){
            System.out.println("O carro está em velocidade mínima!");
        } else if (this.getVelocidade() > 40) {
            System.out.println("O carro deve estar entre 1 e 40 km/h");
        }else {
            System.out.println("Virando o carro para direita!");
        }
    }

    public void acelerarCarro(){

        if(!this.isLigado()){
            System.out.println("O carro não está ligado!");
        } else if (this.getMarcha() == 0) {
            System.out.println("O carro está em ponto morto!");
        } else {
            switch (this.getMarcha()){
                case 1 -> setVelocidadeMaxima(20);
                case 2 -> setVelocidadeMaxima(40);
                case 3 -> setVelocidadeMaxima(60);
                case 4 -> setVelocidadeMaxima(80);
                case 5 -> setVelocidadeMaxima(100);
                case 6 -> setVelocidadeMaxima(120);
            }if (this.getMarcha() == 6 && this.getVelocidade() == 120){
                System.out.println("carro está em velocidade máxima");
            }else if (this.getVelocidade() == getVelocidadeMaxima()){
                System.out.println("aumente a marcha para acelerar o carro!");
            }else {
                System.out.println("acelerando o carro!");
                this.setVelocidade(getVelocidade() + 1);
            }
        }
    }

    public void desacelerarCarro(){

        if(!this.isLigado()){
            System.out.println("O carro não está ligado!");
        } else if (this.getMarcha() == 0) {
            System.out.println("O carro está em ponto morto!");
        } else {
            switch (this.getMarcha()){
                case 1 -> setVelocidadeMinima(0);
                case 2 -> setVelocidadeMinima(21);
                case 3 -> setVelocidadeMinima(41);
                case 4 -> setVelocidadeMinima(61);
                case 5 -> setVelocidadeMinima(81);
                case 6 -> setVelocidadeMinima(101);
            }if (this.getMarcha() == 1 && this.getVelocidade() == 0){
                System.out.println("carro está em velocidade mínima");
            }else if (this.getVelocidade() == getVelocidadeMinima()){
                System.out.println("Diminua a marcha para desacelerar o carro!");
            }else {
                System.out.println("Desacelerando o carro!");
                this.setVelocidade(getVelocidade() - 1);
            }
        }
    }

    public void descerMarcha(){
        if (!this.isLigado()) {
            System.out.println("O carro deve está ligado!");
        } else if (this.getMarcha() == 0) {
            System.out.println("O carro está na marcha mais baixa!");
        }else {
            this.setMarcha(getMarcha() - 1);
            System.out.println("Descendo uma marcha...");
            switch (this.getMarcha()) {
                case 0 -> setVelocidade(0);
                case 1 -> setVelocidade(20);
                case 2 -> setVelocidade(40);
                case 3 -> setVelocidade(60);
                case 4 -> setVelocidade(80);
                case 5 -> setVelocidade(100);
            }
        }
    }

    public void passarMarcha() {
        if (!this.isLigado()) {
            System.out.println("O carro deve estar ligado para passar a marcha!");
        } else if (this.getMarcha() == 6) {
            System.out.println("O carro está na marcha mais alta!");
        } else {
            this.setMarcha(getMarcha() + 1);
            System.out.println("Passando a marcha...");
            switch (this.getMarcha()) {
                case 1 -> setVelocidade(0);
                case 2 -> setVelocidade(21);
                case 3 -> setVelocidade(41);
                case 4 -> setVelocidade(61);
                case 5 -> setVelocidade(81);
                case 6 -> setVelocidade(101);
            }
        }
    }

    public void status(){
        System.out.println("Modelo: "+ this.getModelo());
        System.out.println("Marca: "+ this.getMarca());
        System.out.println("Velocidade: "+ this.getVelocidade()+"km/h");
        System.out.println("Marcha: "+ this.getMarcha()+"ª");
    }

    public void ligarCarro(){
        if (this.isLigado()){
            System.out.println("O carro já está ligado!");
        }else {
            this.setLigado(true);
            System.out.println("Ligando o carro...");
        }

    }

    public void desligarCarro(){
        if (!this.isLigado()){
            System.out.println("O carro já está desligado!");
        }else if (this.getMarcha() != 0){
            System.out.println("O carro deve estar em ponto morto para ser desligado!");
        }else if (this.getVelocidade() != 0){
            System.out.println("O carro deve estar parado!");
        } else {
            this.setLigado(false);
        }
    }


}
