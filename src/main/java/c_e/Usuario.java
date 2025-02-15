package c_e;

public class Usuario {

    int id;
    String nome;
    String senha;

    public Usuario(int id, String nome, String senha)
    {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public String getSenha() {
        return senha;
    }
}