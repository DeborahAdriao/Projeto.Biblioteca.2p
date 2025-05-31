package projeto.biblioteca;

import java.io.Serializable;

public class Livro implements Serializable {
    private static int contadorLivros = 0; // atributo static

    private String autor;
    private String titulo;
    private String isbn;
    private int estoque;

    public Livro () {
        contadorLivros++;
    }

    public Livro(String autor, String titulo, String isbn, int estoque){
        // construtor
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estoque = estoque;
        contadorLivros++;
    }
    // get static
    public static int getContadorLivros(){
        return contadorLivros;
    }

    // AUTOR
    public void setAutor(String autor) {

        this.autor = autor;
    }

    public String getAutor() {

        return autor;
    }
    // TITULO
    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getTitulo() {

        return titulo;
    }
    // ID
    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }

    public String getIsbn() {

        return isbn;
    }
    // ESTOQUE
    public void setEstoque(int estoque) {

        this.estoque = estoque;
    }

    public int getEstoque() {

        return estoque;
    }

    public void devolver() {
        this.estoque++;
        System.out.println("Livro devolvido com sucesso!\n");
    }

    // EXIBIR DETALHES - metodo
    public String exibirDetalhes() {
        return "----------------------------\n" +
                "Autor: " + autor +
                "\nTítulo: " + titulo +
                "\nISBN: " + isbn +
                "\n----------------------------";
    }
    //sobrecarga
    public String exibirDetalhes(boolean mostrarEstoque) {
        String detalhes = "--------------------------\n" +
                "Autor: " + autor +
                "\nTítulo: " + titulo +
                "\nISBN: " + isbn;

        if (mostrarEstoque) {
            detalhes += "\nQuantidade em estoque: " + estoque;
        }
        return detalhes + "\n---------------------------";
    }
}
