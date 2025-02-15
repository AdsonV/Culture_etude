package c_e;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class Filme {

    int id;
    String nome;
    String diretor;
    String descricao;
    String genero;
    static String duracao;
    static SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat duracao2 = new SimpleDateFormat("hh/mm/ss");
    static String data;

    public Filme(int id, String nome, String diretor, String data, String descricao, String genero, String duracao)
    {
        this.id = id;
        this.nome = nome;
        this.diretor = diretor;
        Filme.data = data;
        this.descricao = descricao;
        this.genero = genero;
        Filme.duracao = duracao;
    }

    public Filme(int id, String nome, String diretor, String data, String descricao, String genero, String duracao, JLabel imagem)
    {
        this.id = id;
        this.nome = nome;
        this.diretor = diretor;
        Filme.data = data;
        this.descricao = descricao;
        this.genero = genero;
        Filme.duracao = duracao;
        Admin.l_imagem2 = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDuracao() {
        return duracao;
    }
    public void setDuracao(String duracao) {
        Filme.duracao = duracao;
    }
    public String getData() {
        return data;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}