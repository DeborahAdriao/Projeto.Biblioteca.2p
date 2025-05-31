package projeto.biblioteca;
import java.io.Serializable; //permite que objetos dessa classe possam ser convertidos em uma sequência de bytes para serem salvos em arquivo
import java.time.LocalDate; //representa uma data (ano, mês, dia)

public class Emprestimo implements Serializable{
    //composição: Emprestimo depende diretamente de objetos de outras classes.
    private final Pessoa leitor; //composição
    private final Livro livro;
    private final LocalDate dataEmprestimo; //data do empréstimo
    private boolean devolvido;

    public Emprestimo(Pessoa leitor, Livro livro) {
        this.leitor = leitor;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now(); // data atual
        this.devolvido = false; // começa como "não devolvido"
    }

    public Pessoa getLeitor() {
        return leitor;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    //Permite alterar o estado do empréstimo para indicar se o livro foi devolvido ou não.
    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public String exibirDetalhes() {
        return "----------------------------\n" +
                "Leitor: " + leitor.getNome() +
                "\nLivro: " + livro.getTitulo() +
                "\nData do Empréstimo: " + dataEmprestimo +
                "\n----------------------------";
    }
}
