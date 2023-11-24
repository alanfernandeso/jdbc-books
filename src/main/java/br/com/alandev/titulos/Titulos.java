package br.com.alandev.titulos;

import br.com.alandev.autores.Autor;

public class Titulos {
    private Autor autor;
    private String isbn;
    private String titulo;
    private int numeroEdicao;
    private String copyright;

    public Titulos(Autor autor, String isbn, String titulo, String copyright) {
        this.autor = autor;
        this.isbn = isbn;
        this.titulo = titulo;
        this.copyright = copyright;
    }

    public Titulos(String isbn, String titulo, int numeroEdicao, String copyright, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.numeroEdicao = numeroEdicao;
        this.copyright = copyright;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public void setNumeroEdicao(int numeroEdicao) {
        this.numeroEdicao = numeroEdicao;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        return "AutorID: " + this.autor.getAutorID() + " Nome: " + this.autor.getNome() + " Sobrenome: " + this.autor.getSobrenome() +
                " ISBN: " + this.isbn + " Título: " + this.titulo + " Ano: " + this.copyright + "\n";
    }
}
