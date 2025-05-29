package projeto.biblioteca;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = Arquivo.carregarBiblioteca();
        Menu menu = new Menu(biblioteca); //instância da classe Menu e passa biblioteca como argumento
        menu.exibirMenu(); //permite que a Menu consiga chamar os métodos da Biblioteca

        // Ao sair do menu, salva a biblioteca
        Arquivo.salvarBiblioteca(biblioteca); // salva no arquivo
    }
}
