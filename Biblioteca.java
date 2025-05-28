package projeto.biblioteca;
import java.util.ArrayList;

public class Biblioteca {
    //declarando - atributos
    // ArrayList <TIPO> nome;
    private final ArrayList<Livro> livros;
    private final ArrayList<Leitor> leitores;
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
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null; // se não achar
    }
    public void adicionarLivro(Livro livro){
        livros.add(livro);
    }
    public void adicionarLeitor(Leitor leitor){
        leitores.add(leitor);
    }


    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }
    public ArrayList<Leitor> getLeitores() {
        return leitores;
    }
}
