package projeto.biblioteca;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = Arquivo.carregarBiblioteca();
        Menu menu = new Menu(biblioteca);
        menu.exibirMenu();

        Arquivo.salvarBiblioteca(biblioteca); // salva no arquivo
    }
}
