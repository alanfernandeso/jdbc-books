package br.com.alandev.titulos;
import br.com.alandev.autores.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TitulosDAO {

    private Connection conn = null;

    public TitulosDAO(Connection conn) {
        this.conn = conn;
    }

    public void selecionarTitulo(String tituloSelecionado){

        String sql = """
                SELECT ai.autorid,  a.sobrenome, a.nome, ai.ISBN, t.titulo, t.copyright FROM titulos t
                join autorisbn ai on t.isbn = ai.isbn
                join autores a on a.autorID = ai.autorID
                where ai.ISBN = ?
                ORDER BY  a.nome, a.sobrenome;
                """;

        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tituloSelecionado);

            rs = ps.executeQuery();

            List<Titulos> titulos = new ArrayList<>();

            while(rs.next()){
                int autorID = rs.getInt(1);
                String sobrenome = rs.getString(2);
                String nome = rs.getString(3);
                String isbn = rs.getString(4);
                String titulo = rs.getString(5);
                String copyright = rs.getString(6);

                Autor autor = new Autor(autorID, nome, sobrenome);

                Titulos listaTitulos = new Titulos(autor, isbn, titulo, copyright);

                titulos.add(listaTitulos);
            }

            System.out.println(titulos);

            ps.close();
            rs.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void adicionar(String isbn, String titulo, int numeroEdicao, String copyright){

        PreparedStatement ps;

        String sql = """
                INSERT INTO titulos (isbn, titulo, numero_edicao, copyright) values (?, ?, ?, ?);
                """;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, isbn);
            ps.setString(2, titulo);
            ps.setInt(3, numeroEdicao);
            ps.setString(4, copyright);
            ps.execute();

            conn.close();
            ps.close();

            System.out.println("Titulo adicionado!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void adicionarAutorParaTitulo(int autorId, String isbn){

        PreparedStatement ps;

        String sql = """
                INSERT INTO autorisbn (autorID, ISBN) values (?, ?);
                """;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, autorId);
            ps.setString(2, isbn);
            ps.execute();

            conn.close();
            ps.close();

            System.out.println("Autor adicionado a título!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
