package c_e;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Livro {

    int id;
    String nome;
    String autor;
    int paginas;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    Date data;
    String dataFormatada = date.format(data);
    String descricao;
    String genero;
    public Livro(int id, String nome, String autor, int paginas, Date data, String descricao, String genero)
    {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.paginas = paginas;
        this.data = data;
        this.descricao = descricao;
        this.genero = genero;
    }

    public Livro(int id, String nome, String autor, int paginas, Date data, String descricao, String genero,  JLabel imagem)
    {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.paginas = paginas;
        this.data = data;
        this.descricao = descricao;
        this.genero = genero;
        Admin.l_imagem2 = imagem;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getPaginas() {
        return paginas;
    }
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    public Date getData() {
        return data;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
}