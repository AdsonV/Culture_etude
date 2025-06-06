package c_e;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static c_e.Consulta.*;
import static c_e.Conexao.*;
import static c_e.Novo_acesso.*;

public class Cadastrar implements ActionListener {

    String sql = ("INSERT INTO usuario (id,usuario,senha)" + " VALUES (?,?,?)");
    static String sqlfilme = ("INSERT INTO filme (id,nome,diretor,data,descricao,genero,duracao,imagem)" + " VALUES (?,?,?,?,?,?,?,?)");
    static String sqllivro = ("INSERT INTO livro (id,nome,autor,paginas,data,descricao,genero,imagem)" + " VALUES (?,?,?,?,?,?,?,?)");
    static String sqlcarrinho = ("INSERT INTO carrinho (id,user,tipo,nome,imagem)" + " VALUES (?,?,?,?,?)");
    static int index;
    static List<Filme> lista_filmes = new ArrayList<>();
    static List<Livro> lista_livros = new ArrayList<>();
    static List<Carrinho> lista_carrinho = new ArrayList<>();
    static List<Usuario> lista_usuario = new ArrayList<>();
    static FileInputStream fis;


    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            ConsultaUsuario(novo_usuario.getText().trim(), nova_senha.getText().trim());

            if (!nova_senha.getText().equals(confirma_senha.getText())) {
                JOptionPane.showMessageDialog(null, "CONFIRME A SENHA CORRETAMENTE.", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            else if (novo_usuario.getText().trim().isEmpty() || nova_senha.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA TODOS OS DADOS.", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            else if (novo_usuario.getText().equals("administrador") || ConsultaUsuario(novo_usuario.getText().trim(), nova_senha.getText().trim())) {
                JOptionPane.showMessageDialog(null, "NOME OU SENHA JÁ EXISTENTES, UTILIZE OUTRO!", "ERRO", JOptionPane.ERROR_MESSAGE);
            } else {

                InserirUsuario();

                JOptionPane.showMessageDialog(null, "CADASTRO FEITO COM SUCESSO!", "Login", JOptionPane.INFORMATION_MESSAGE);
                b = new JButton();
                n_janela.dispose();
                n_janela = new JFrame();
                Login.janela.setVisible(true);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void InserirUsuario() {
        try {
            ConsultaUsuario(novo_usuario.getText().trim(), nova_senha.getText().trim());

            if (!lista_usuario.isEmpty()) {
                index = lista_usuario.indexOf(lista_usuario.getLast());
                PreparedStatement stt = Conexao.conn3.prepareStatement(sql);

                stt.setInt(1, index + 1);
                stt.setString(2, novo_usuario.getText().trim());
                stt.setString(3, nova_senha.getText().trim());
                stt.execute();
            } else {
                PreparedStatement stt = Conexao.conn3.prepareStatement(sql);

                stt.setInt(1, 0);
                stt.setString(2, novo_usuario.getText().trim());
                stt.setString(3, nova_senha.getText().trim());
                stt.execute();
            }

            b = new JButton();
            lista_usuario.clear();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "VOCÊ INSERIU ALGUM DADO INCORRETO.", "ERRO.", JOptionPane.INFORMATION_MESSAGE);
            lista_usuario.clear();
        }
        Conexao.desconectar3();
    }
    public static void CadastrarAdmin() {

        String filmeoulivro = Admin.filme_livro.getText().trim();
        String diretorouautor = Admin.diretor_autor.getText().trim();
        String data = Admin.data.getText().trim();
        String genero = Admin.genero.getText().trim();
        String paginas = Admin.paginas.getText().trim();
        String duracao = Admin.duracao.getText().trim();
        String descricao = Admin.descricao.getText().trim();

        try {

            if (filmeoulivro.isEmpty() || diretorouautor.isEmpty() || data.isEmpty() || genero.isEmpty() || descricao.isEmpty() || duracao.isEmpty() && paginas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "POR FAVOR INSIRA TODOS OS DADOS!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (Admin.fil.isSelected() && !ConsultaFilme(filmeoulivro, diretorouautor)) {
                    InserirFilme();
                } else if (Admin.fil.isSelected() && ConsultaFilme(filmeoulivro, diretorouautor)) {
                    JOptionPane.showMessageDialog(null, "FILME JÁ CONSTA NA BASE DE DADOS.", "REPETIDO", JOptionPane.ERROR_MESSAGE);
                } else if (Admin.liv.isSelected() && !ConsultaLivro(filmeoulivro, diretorouautor)) {
                    Inserirlivro();
                } else if (Admin.liv.isSelected() && ConsultaLivro(filmeoulivro, diretorouautor)) {
                    JOptionPane.showMessageDialog(null, "LIVRO JÁ CONSTA NA BASE DE DADOS.", "REPETIDO", JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "INSIRA UM FILME OU LIVRO", "CULTURE_ETUDE", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "NÃO FOI POSSÍVEL ESTABELECER A CONEXÃO", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

        b = new JButton();
        lista_usuario.clear();
        lista_filmes.clear();
        lista_livros.clear();
        lista_carrinho.clear();
        Conexao.desconectar2();
    }
    public static void InserirFilme() {
        try {

            String filmeoulivro = Admin.filme_livro.getText().trim();
            String diretorouautor = Admin.diretor_autor.getText().trim();
            String genero = Admin.genero.getText().trim();
            String duracao = Admin.duracao.getText().trim();
            String descricao = Admin.descricao.getText().trim();

            int tamanho;
            tamanho = (int) Admin.arquivo.getSelectedFile().length();
            fis = new FileInputStream(Admin.arquivo.getSelectedFile());

            String s = Admin.data.getText().trim().replaceAll("/", "-");

            if (!lista_filmes.isEmpty()) {
                index = lista_filmes.indexOf(lista_filmes.getLast());

                PreparedStatement stt = conn2.prepareStatement(sqlfilme);
                stt.setInt(1, index + 2);
                stt.setString(2, filmeoulivro);
                stt.setString(3, diretorouautor);
                stt.setString(4, s);
                stt.setString(5, descricao);
                stt.setString(6, genero);
                stt.setString(7, duracao);
                stt.setBlob(8, fis, tamanho);
                stt.execute();
                JOptionPane.showMessageDialog(null, "FILME INSERIDO COM SUCESSO!");
            } else {
                PreparedStatement stt = Conexao.conn2.prepareStatement(sqlfilme);
                stt.setInt(1, 0);
                stt.setString(2, filmeoulivro);
                stt.setString(3, diretorouautor);
                stt.setString(4, s);
                stt.setString(5, descricao);
                stt.setString(6, genero);
                stt.setString(7, duracao);
                stt.setBlob(8, fis, tamanho);
                stt.execute();

                JOptionPane.showMessageDialog(null, "FILME INSERIDO COM SUCESSO!");
            }

            Admin.a_janela.dispose();
            Listar.janela.setVisible(true);
            Admin.a_janela = new JFrame();
            b = new JButton();
            lista_filmes.clear();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "VOCÊ INSERIU ALGUM DADO INCORRETO.", "ERRO.", JOptionPane.INFORMATION_MESSAGE);
            lista_filmes.clear();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Conexao.desconectar2();
    }
    public static void Inserirlivro() {
        try {

            String filmeoulivro = Admin.filme_livro.getText().trim();
            String diretorouautor = Admin.diretor_autor.getText().trim();
            String genero = Admin.genero.getText().trim();
            String paginas = Admin.paginas.getText().trim();
            String descricao = Admin.descricao.getText().trim();

            int tamanho;
            tamanho = (int) Admin.arquivo.getSelectedFile().length();
            fis = new FileInputStream(Admin.arquivo.getSelectedFile());

            String s = Admin.data.getText().trim().replaceAll("/", "-");

            if (!Cadastrar.lista_livros.isEmpty()) {
                Cadastrar.index = Cadastrar.lista_livros.indexOf(Cadastrar.lista_livros.getLast());

                PreparedStatement stt = conn2.prepareStatement(sqllivro);
                stt.setInt(1, Cadastrar.index + 1);
                stt.setString(2, filmeoulivro);
                stt.setString(3, diretorouautor);
                stt.setString(4, paginas);
                stt.setString(5, s);
                stt.setString(6, descricao);
                stt.setString(7, genero);
                stt.setBlob(8, fis, tamanho);
                stt.execute();
                JOptionPane.showMessageDialog(null, "LIVRO INSERIDO COM SUCESSO!");
            } else {
                PreparedStatement stt = Conexao.conn2.prepareStatement(sqllivro);
                stt.setInt(1, 0);
                stt.setString(2, filmeoulivro);
                stt.setString(3, diretorouautor);
                stt.setString(4, paginas);
                stt.setString(5, s);
                stt.setString(6, descricao);
                stt.setString(7, genero);
                stt.setBlob(8, fis, tamanho);
                stt.execute();

                JOptionPane.showMessageDialog(null, "LIVRO INSERIDO COM SUCESSO!");
            }
        } catch(SQLException e)
        {

            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "VOCÊ INSERIU ALGUM DADO INCORRETO.", "ERRO.", JOptionPane.INFORMATION_MESSAGE);
            lista_livros.clear();

        }
        catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        Admin.a_janela.dispose();
        Listar.janela.setVisible(true);
    Admin.a_janela = new JFrame();
    b = new JButton();
        lista_livros.clear();


        Conexao.desconectar2();
}
    public static void AddCarFilme(ActionEvent e) {

        try {
            if (ConsultaCarrinho(Login.Login.getText(),"filme",Listar.lblnf.getText())){
                JOptionPane.showMessageDialog(null,"FILME JÁ CONSTA NO SEU CARRINHO DE COMPRAS.");
            }
            else {

                Conexao.conectar();

                Statement st1 = conn.createStatement();
                st1.executeQuery("SELECT * FROM filme WHERE nome LIKE " + "'%" + Listar.jtable.getValueAt(Listar.jtable.getSelectedRow(), 1) + "%'");
                ResultSet r1 = st1.getResultSet();

                while (r1.next()) {

                    if (!lista_carrinho.isEmpty()) {
                        index = lista_carrinho.indexOf(lista_carrinho.getLast());
                        java.sql.Blob blob = (java.sql.Blob) r1.getBlob("imagem");

                        PreparedStatement stt = Conexao.conn.prepareStatement(sqlcarrinho);
                        stt.setInt(1, index +2);
                        stt.setString(2, Login.Login.getText());
                        stt.setString(3, "filme");
                        stt.setString(4, Listar.lblnf.getText());
                        stt.setBlob(5, blob.getBinaryStream(), blob.length());
                        stt.execute();

                        JOptionPane.showMessageDialog(null, "FILME ADICIONADO COM SUCESSO!");
                    }
                    else
                    {
                        index = lista_carrinho.indexOf(lista_carrinho.getLast());
                        java.sql.Blob blob = (java.sql.Blob) r1.getBlob("imagem");

                        PreparedStatement stt = Conexao.conn.prepareStatement(sqlcarrinho);
                        stt.setInt(1, index+1);
                        stt.setString(2, Login.Login.getText());
                        stt.setString(3, "filme");
                        stt.setString(4, Listar.lblnf.getText());
                        stt.setBlob(5, blob.getBinaryStream(), blob.length());
                        stt.execute();

                        JOptionPane.showMessageDialog(null, "FILME ADICIONADO COM SUCESSO!");
                    }
                }

                Listar.janela2.dispose();
                Listar.b2 = new JButton();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        Listar.b2 = new JButton();
        Listar.bcar = new JButton();
        Listar.janela3 = new JFrame();
        Conexao.desconectar();
    }
    public static void AddCarLivro(ActionEvent e) throws SQLException
    {
        try {

            if (ConsultaCarrinho(Login.Login.getText(),"livro",Listar.lblnf.getText())){
                JOptionPane.showMessageDialog(null,"LIVRO JÁ CONSTA NO SEU CARRINHO DE COMPRAS.");
            }
            else {

                Conexao.conectar();

                Statement st1 = conn.createStatement();
                st1.executeQuery("SELECT * FROM livro WHERE nome LIKE " + "'%" + Listar.jtable.getValueAt(Listar.jtable.getSelectedRow(), 1) + "%'");
                ResultSet r1 = st1.getResultSet();

                while (r1.next()) {

                    if (!lista_carrinho.isEmpty()) {
                        index = lista_carrinho.indexOf(lista_carrinho.getLast());

                        java.sql.Blob blob = (java.sql.Blob) r1.getBlob("imagem");

                        PreparedStatement stt = Conexao.conn.prepareStatement(sqlcarrinho);
                        stt.setInt(1, index+2);
                        stt.setString(2, Login.Login.getText());
                        stt.setString(3, "livro");
                        stt.setString(4, Listar.lblnf.getText());
                        stt.setBlob(5, blob.getBinaryStream(), blob.length());
                        stt.execute();
                        JOptionPane.showMessageDialog(null, "LIVRO ADICIONADO COM SUCESSO!");
                    }
                    else
                    {
                        index = lista_carrinho.indexOf(lista_carrinho.getLast());

                        java.sql.Blob blob = (java.sql.Blob) r1.getBlob("imagem");

                        PreparedStatement stt = Conexao.conn.prepareStatement(sqlcarrinho);
                        stt.setInt(1, index+1);
                        stt.setString(2, Login.Login.getText());
                        stt.setString(3, "livro");
                        stt.setString(4, Listar.lblnf.getText());
                        stt.setBlob(5, blob.getBinaryStream(), blob.length());
                        stt.execute();
                        JOptionPane.showMessageDialog(null, "LIVRO ADICIONADO COM SUCESSO!");
                    }
                }

                Listar.janela2.dispose();
                Listar.b2 = new JButton();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        Conexao.desconectar();
    }
    }