package br.com.alandev.autores;

public class Autor {

    private int autorID;
    private String nome;
    private String sobrenome;

    public Autor(int autorID, String nome, String sobrenome){
        this.autorID = autorID;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }

    public int getAutorID() {
        return autorID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return "ID: " + this.autorID + " Nome: " + this.nome + " Sobrenome: " + this.sobrenome + "\n";
    }
}
