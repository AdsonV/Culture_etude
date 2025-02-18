package c_e;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import static c_e.Cadastrar.*;
import static c_e.Conexao.*;

public class Consulta {

    public static boolean ConsultaFilme(String nome, String diretor) throws SQLException {

        Conexao.conectar2();
        Statement st = conn2.createStatement();
        st.executeQuery("SELECT * FROM filme");
        ResultSet r = st.getResultSet();

        while (r.next()) {

            Filme f = new Filme((r.getInt("id")),
                    (r.getString("nome")),
                    (r.getString("diretor")),
                    (r.getString("data")),
                    (r.getString("descricao")),
                    (r.getString("genero")),
                    (r.getString("duracao")));

            lista_filmes.add(f);
        }
        Optional<Filme> filmes = lista_filmes.stream()
                .filter(nomes -> nomes.getNome().trim()
                        .equals(nome))
                .filter(diretores -> diretores.getDiretor().trim()
                        .equals(diretor))
                .findAny();

        return filmes.isPresent();
    }

    public static boolean ConsultaLivro(String nome, String autor) throws SQLException {

        Conexao.conectar2();
        Statement st = conn2.createStatement();
        st.executeQuery("SELECT * FROM livro");
        ResultSet r = st.getResultSet();

        while (r.next()) {

            Livro l = new Livro((r.getInt("id")),
                    (r.getString("nome")),
                    (r.getString("autor")),
                    (r.getInt("paginas")),
                    (r.getDate("data")),
                    (r.getString("descricao")),
                    (r.getString("genero")));

            lista_livros.add(l);
        }
        Optional<Livro> livros = lista_livros.stream()
                .filter(nomes -> nomes.getNome().trim()
                        .equals(nome))
                .filter(autores -> autores.getAutor().trim()
                        .equals(autor))
                .findAny();

        return livros.isPresent();
    }

    public static boolean ConsultaUsuario(String usuario, String senha) throws SQLException {

        conectar3();
        Statement st = conn3.createStatement();
        st.executeQuery("SELECT * FROM usuario");
        ResultSet r = st.getResultSet();

        while (r.next()) {

            Usuario u = new Usuario((r.getInt("id")),
                    (r.getString("usuario")),
                    (r.getString("senha")));

            lista_usuario.add(u);
        }
        Optional<Usuario> usuarios = lista_usuario.stream()
                .filter(nomes -> nomes.getNome().trim()
                        .equals(usuario))
                .filter(senhas -> senhas.getSenha().trim()
                        .equals(senha))
                .findAny();

        return usuarios.isPresent();
    }

    public static boolean ConsultaCarrinho(String usuarios, String tipo, String nome) throws SQLException {

        conectar();
        Statement st = conn.createStatement();
        st.executeQuery("SELECT * FROM carrinho");
        ResultSet r = st.getResultSet();

        while (r.next()) {

            Carrinho c = new Carrinho((r.getInt("id")),
                    (r.getString("user")),
                    (r.getString("tipo")),
                    (r.getString("nome")),
                    (r.getBlob("imagem")));

            lista_carrinho.add(c);
        }
        Optional<Carrinho> carrinho = lista_carrinho.stream()
                .filter(user -> user.getUser().trim()
                        .equals(usuarios))
                .filter(tipos -> tipos.getTipo().trim()
                        .equals(tipo))
                .filter(nomes -> nomes.getNome().trim()
                        .equals(nome))
                .findAny();

        return carrinho.isPresent();
    }
}
