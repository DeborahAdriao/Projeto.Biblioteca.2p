package projeto.biblioteca;

import java.io.*; //Importa todas as classes do pacote java.io

//salvar e carregar arquivos
public class Arquivo {
    public static void salvarBiblioteca(Biblioteca biblioteca) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("biblioteca.ser"))) {
            oos.writeObject(biblioteca); // serializa
            System.out.println("Biblioteca salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar a biblioteca: " + e.getMessage());
        }
    }

    // Carregar a biblioteca do disco
    public static Biblioteca carregarBiblioteca() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("biblioteca.ser"))) {
            return (Biblioteca) ois.readObject(); // desserializa
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" ");
            return new Biblioteca(); // caso não tenha arquivo ainda
        }
    }
}
