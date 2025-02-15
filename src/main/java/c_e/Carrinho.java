package c_e;

import java.sql.Blob;

public class Carrinho{

     int id;
    String user;
    String tipo;
    String nome;
    Blob imagem;

    public Carrinho(int id,String user, String tipo, String nome, Blob imagem)
    {
        this.id = id;
        this.user = user;
        this.tipo = tipo;
        this.nome = nome;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public Blob getImagem() {
        return imagem;
    }
}