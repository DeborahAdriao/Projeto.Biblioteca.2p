package projeto.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;

public class Biblioteca implements Serializable {
    //declarando - atributos
    // ArrayList <TIPO> nome;
    private final ArrayList<Livro> livros;
    private final ArrayList<Pessoa> leitores;
    private final ArrayList<Emprestimo> emprestimos;

    public Biblioteca(){
        //inicializando as listas vazias - construtor
        livros = new ArrayList<>();
        leitores = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    //MÉTODOS
    public Livro buscarLivro(String id) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equals(id)) {
                return livro;
            }
        }
        return null; // se não achar
    }
    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }
    public void adicionarLeitor(Pessoa leitor) {
        leitores.add(leitor);
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<Pessoa> getLeitores() {
        return leitores;
    }
}
