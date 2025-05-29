package projeto.biblioteca;
import java.io.Serializable;

public class Pessoa implements Serializable{
    private String nome;
    private String cpf;

    public Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    // NOME
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    //CPF
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
    public String exibirInfo() {
        return "Nome: " + nome + "\nCPF: " + cpf;
    }
}
