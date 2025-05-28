package projeto.biblioteca;

public class Leitor extends Pessoa{
    private String telefone;

    public Leitor (String nome, String cpf, String telefone){
        super(nome, cpf);
        this.telefone = telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String exibirInfo() {
        return super.exibirInfo() + "\nTelefone: " + telefone;
    }
}
