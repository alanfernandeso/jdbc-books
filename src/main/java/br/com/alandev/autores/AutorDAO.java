package br.com.alandev.autores;

import br.com.alandev.ConnectionFacttory;
import br.com.alandev.titulos.Titulos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private Connection conn = null;

    public AutorDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Autor> listarAutores(){

        String sql = "SELECT * FROM autores;";

        PreparedStatement ps;
        ResultSet rs;

        List<Autor> autores = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                int autorId = rs.getInt(1);
                String nome = rs.getString(2);
                String sobrenome = rs.getString(3);

                Autor autor = new Autor(autorId, nome, sobrenome);

                autores.add(autor);
            }

            System.out.println(autores);

            ps.close();
            rs.close();
            conn.close();

            return autores;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void selecionarAutor(String autorSelecionado){

        String sql = """
                SELECT autorisbn.autorid, autores.nome, autores.sobrenome, autorisbn.ISBN, titulos.titulo, titulos.copyright  FROM titulos
                JOIN autorisbn on titulos.isbn = autorisbn.isbn
                JOIN autores on autores.autorID = autorisbn.autorID
                WHERE autorisbn.autorID = ?
                ORDER BY autores.nome, autores.sobrenome, titulos.titulo;
                """;

        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, autorSelecionado);

            rs = ps.executeQuery();

            List<Titulos> titulos = new ArrayList<>();

            while(rs.next()){
                int autorID = rs.getInt(1);
                String nome = rs.getString(2);
                String sobrenome = rs.getString(3);
                String isbn = rs.getString(4);
                String titulo = rs.getString(5);
                String copyright = rs.getString(6);

                Autor autor = new Autor(autorID, nome, sobrenome);

                Titulos tituloSelecionado = new Titulos(autor, isbn, titulo, copyright);

                titulos.add(tituloSelecionado);
            }

            System.out.println(titulos);

            ps.close();
            rs.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void adicionar(String nome, String sobrenome){

        PreparedStatement ps;

        String sql = """
                INSERT INTO autores (nome, sobrenome) values (?, ?);
                """;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, sobrenome);
            ps.execute();

            conn.close();
            ps.close();

            System.out.println("Autor adicionado!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
