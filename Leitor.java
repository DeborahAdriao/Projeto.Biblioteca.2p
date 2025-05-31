package projeto.biblioteca;

import java.io.Serializable;

public class Leitor extends Pessoa{

    public Leitor (String nome, String cpf, String telefone){
        super(nome, cpf, telefone);
    }

    @Override
    public String exibirInfo() {
        return super.exibirInfo() + "\n<3";
    }
}
