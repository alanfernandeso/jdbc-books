package br.com.alandev;

import br.com.alandev.autores.Autor;
import br.com.alandev.autores.AutorDAO;
import br.com.alandev.titulos.TitulosDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BooksAplication {

    public static void main(String[] args) {



        boolean menu = true;

        while(menu) {

            Connection conn = new ConnectionFacttory().conectar();

            AutorDAO autorDao = new AutorDAO(conn);
            TitulosDAO titulosDao = new TitulosDAO(conn);

            System.out.println("\n==================================");
            System.out.println("1 - Listar autores");
            System.out.println("2 - Selecione um autor");
            System.out.println("3 - Selecione um titulo");
            System.out.println("4 - Adicionar um autor");
            System.out.println("5 - Adicionar um titulo");
            System.out.println("6 - Sair");

            int opcao = new Scanner(System.in).nextInt();

            switch (opcao) {

                case 1:
                    autorDao.listarAutores();
                    break;
                case 2:
                    System.out.println("Digite o ID do autor: ");
                    String idAutor = new Scanner(System.in).nextLine();
                    autorDao.selecionarAutor(idAutor);
                    break;
                case 3:
                    System.out.println("Digite o ISBN do título: ");
                    String isbnTitulo = new Scanner(System.in).nextLine();
                    titulosDao.selecionarTitulo(isbnTitulo);
                    break;
                case 4:
                    System.out.println("Digite o nome do autor");
                    String nomeDoAutor = new Scanner(System.in).nextLine();
                    System.out.println("Digite o sobrenome do autor");
                    String sobrenomeDoAutor = new Scanner(System.in).nextLine();
                    autorDao.adicionar(nomeDoAutor, sobrenomeDoAutor);
                    break;
                case 5:
                    System.out.println("Digite o ISBN");
                    String isbn = new Scanner(System.in).nextLine();
                    System.out.println("Digite o nome do título");
                    String titulo = new Scanner(System.in).nextLine();
                    System.out.println("Digite o numero de edição");
                    int numeroEdicao = new Scanner(System.in).nextInt();
                    System.out.println("Digite o ano de lançamento");
                    String copyright = new Scanner(System.in).nextLine();
                    System.out.println("Selecione o Autor para este título, digite o ID");
                    int autorId = new Scanner(System.in).nextInt();

                    List<Autor> autores;

                    autores = autorDao.listarAutores();

                    boolean autorExiste = false;

                    for (Autor listaDeAutores : autores) {
                        if (listaDeAutores.getAutorID() == autorId) {
                            autorExiste = true;
                        }
                    }

                    if (autorExiste) {
                        titulosDao.adicionar(isbn, titulo, numeroEdicao, copyright);
                        titulosDao.adicionarAutorParaTitulo(autorId, isbn);
                        System.out.println("Cadastro realizado com sucesso!");
                    } else {
                        System.out.println("Autor não cadastrado!");
                    }
                    break;

                case 6:
                    menu = false;
                    break;
            }

        }

    }

}
