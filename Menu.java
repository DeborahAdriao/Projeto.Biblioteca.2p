package projeto.biblioteca;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import static projeto.biblioteca.Util.*;

// trocar isbn!!!!!!!!!

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final Biblioteca biblioteca;

    public Menu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n---- MENU LIVRARIA ----");
                System.out.println("1. Cadastrar Livro");
                System.out.println("2. Cadastrar Leitor");
                System.out.println("3. Listar livros");
                System.out.println("4. Listar leitores");
                System.out.println("5. Realizar Empréstimo");
                System.out.println("6. Registrar Devolução");
                System.out.println("7. Excluir ou Atualizar cadastro de Livro");
                System.out.println("8. Excluir ou Atualizar cadstro de Leitor");
                System.out.println("0. Sair");

                System.out.println("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarLivro();
                        break;
                    case 2:
                        cadastrarLeitor();
                        break;
                    case 3:
                        listarLivros();
                        break;
                    case 4:
                        listarLeitores();
                        break;
                    case 5:
                        realizarEmprestimo();
                        break;
                    case 6:
                        registrarDevolucao();
                        break;
                    case 7:
                        excluirAtuLivro();
                        break;
                    case 8:
                        excluirAtuLeitor();
                        break;
                    case 0:
                        Arquivo.salvarBiblioteca(biblioteca);
                        System.out.println("\nAté a próxima! <3");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.nextLine(); // Limpa a entrada inválida
            }
        } while (opcao != 0);
        System.out.println("Menu finalizado.");
    }

    private void cadastrarLivro() {
        try {
            System.out.println("Autor: ");
            String autor = scanner.nextLine().trim().toUpperCase();
            System.out.println("Título: ");
            String titulo = scanner.nextLine().trim().toUpperCase();
            System.out.println("ISBN: ");
            String isbn = scanner.nextLine().trim();
            System.out.println("Estoque: ");
            int estoque = scanner.nextInt();

            while (!validarEstoque(estoque)) {
                System.out.println("Só pode adicionar 1 ou mais livros. Tente novamente: ");
                estoque = scanner.nextInt();
            }
            scanner.nextLine();

            System.out.println("\nConfirma os dados abaixo para o cadastro? (S/N)");
            System.out.println("Autor: " + autor);
            System.out.println("Título: " + titulo);
            System.out.println("ISBN: " + isbn);
            System.out.println("Estoque: " + estoque);
            System.out.print("-> ");
            String confirmacao = scanner.nextLine().trim().toUpperCase();

            if (confirmacao.equals("S")) {
                Livro livro = new Livro(autor, titulo, isbn, estoque);
                biblioteca.adicionarLivro(livro);
                System.out.println("Livro cadastrado com sucesso!");
            } else {
                System.out.println("Deseja tentar novamente? (S/N)");
                String tentarDeNovo = scanner.nextLine().trim().toUpperCase();
                if (tentarDeNovo.equals("S")){
                    cadastrarLivro(); //Chama p metodo dnv
                } else {
                    System.out.println("Cadastro cancelado.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Estoque deve ser um número inteiro.");
            scanner.nextLine(); // Limpa a entrada inválida
        }
    }

    private void cadastrarLeitor() {
        System.out.print("Nome do leitor: ");
        String nome = scanner.nextLine().trim().toUpperCase();
        System.out.print("CPF do leitor: ");
        String cpf = scanner.nextLine().trim();

        while (!validarCPF(cpf)) {
            System.out.println("CPF inválido! Digite novamente: ");
            cpf = scanner.nextLine().trim();
        }

        System.out.println("Telefone do leitor: ");
        String telefone = scanner.nextLine().trim();

        while (!validarTelefone(telefone)) {
            System.out.println("Telefone inválido! Digite novamente: ");
            telefone = scanner.nextLine().trim();
        }

        System.out.println("\nConfirma os dados abaixo para o cadastro? (S/N)");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.print("-> ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();

        if (confirmacao.equals("S")) {
            Leitor leitor = new Leitor(nome, cpf, telefone);
            biblioteca.adicionarLeitor(leitor);
            System.out.println("Leitor cadastrado com sucesso!");
        } else {
            System.out.print("Deseja tentar novamente? (S/N): ");
            String tentarDeNovo = scanner.nextLine().trim().toUpperCase();
            if (tentarDeNovo.equals("S")) {
                cadastrarLeitor(); // Chama o metodo de novo
            } else {
                System.out.println("Cadastro cancelado.");
            }
        }
    }

    private void listarLivros() {
        System.out.println("--- LIVROS CADASTRADOS ---");
        for (Livro livro : biblioteca.getLivros()) {
            System.out.println(livro.exibirDetalhes(true));
        }
        System.out.println("Total de livros que foram cadastrados hoje: " + Livro.getContadorLivros());
    }

    private void listarLeitores() {
        System.out.println("--- LEITORES CADASTRADOS ---");
        for (Leitor leitor : biblioteca.getLeitores()){
            System.out.println(leitor.exibirInfo());
        }
    }

    private void realizarEmprestimo() {
        System.out.print("Informe o CPF do leitor: ");
        String cpf = scanner.nextLine().trim();
        // Procura o leitor
        Leitor leitorSelecionado = null;
        for (Leitor leitor : biblioteca.getLeitores()) {
            if (leitor.getCpf().equals(cpf)) {
                leitorSelecionado = leitor;
                break;
            }
        }
        if (leitorSelecionado == null) {
            System.out.println("Leitor não encontrado. Volte ao início e faça o cadastro.");
            return;
        }
        listarLivros();

        System.out.print("\nInforme o ISBN do livro: ");
        String isbnLivro = scanner.nextLine().trim();
        // Procura o livro
        Livro livroSelecionado = null;
        for (Livro livro : biblioteca.getLivros()) {
            if (livro.getIsbn().equals(isbnLivro)) {
                livroSelecionado = livro;
                break;
            }
        }
        if (livroSelecionado == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        // Verifica se tem no estoque
        if (livroSelecionado.getEstoque() <= 0) {
            System.out.println("Livro fora de estoque.");
            return;
        }
        // Realiza o empréstimo
        Emprestimo emprestimo = new Emprestimo(leitorSelecionado, livroSelecionado);
        biblioteca.getEmprestimos().add(emprestimo); // Adiciona à lista de empréstimos
        livroSelecionado.setEstoque(livroSelecionado.getEstoque() - 1); // Decrementa estoque

        System.out.println("\nEmpréstimo realizado com sucesso! ");
        System.out.println("Data do empréstimo: " + emprestimo.getDataEmprestimo());
    }

    private void registrarDevolucao() {
        System.out.print("Digite o ISBN do livro: ");
        String ISBN = scanner.nextLine().trim();
        boolean devolvido = false;

        //percorre todos os emprestimos
        for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
            // verifica se o empréstimo ainda não foi devolvido(!isDevolvido()).
            if (emprestimo.getLivro().getIsbn().equals(ISBN) && !emprestimo.isDevolvido()) {
                emprestimo.setDevolvido(true); //marca o empréstimo como "devolvido"
                emprestimo.getLivro().devolver(); // aumenta o estoque
                System.out.println("Devolução registrada com sucesso!");
                devolvido = true;
                //muda a variável devolvido para true
                break;
            }
        }
        if (!devolvido) {
            System.out.println("Nenhum empréstimo ativo encontrado para este livro.");
        }
    }
    private void excluirAtuLivro() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine().trim();

        Livro livro = biblioteca.buscarLivro(isbn);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("Livro encontrado:");
        System.out.println(livro.exibirDetalhes());

        System.out.print("Deseja [1] Excluir ou [2] Atualizar o livro? ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        if (opcao == 1) {
            biblioteca.getLivros().remove(livro);
            System.out.println("Livro removido com sucesso!");
        } else if (opcao == 2) {
            System.out.print("Novo título: ");
            String novoTitulo = scanner.nextLine().trim().toUpperCase();
            System.out.print("Novo autor: ");
            String novoAutor = scanner.nextLine().trim().toUpperCase();
            // trocar o isbn
            System.out.print("Novo estoque: ");
            int novoEstoque = scanner.nextInt();
            scanner.nextLine();

            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setEstoque(novoEstoque);

            System.out.println("Livro atualizado com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void excluirAtuLeitor() {
        System.out.print("Digite o CPF do leitor: ");
        String cpf = scanner.nextLine().trim().toUpperCase();

        while (!validarCPF(cpf)) {
            System.out.println("CPF inválido! Digite novamente: ");
            cpf = scanner.nextLine().trim();
        }

        Leitor leitorEncontrado = null;
        for (Leitor leitor : biblioteca.getLeitores()) {
            if (leitor.getCpf().equals(cpf)) {
                leitorEncontrado = leitor;
                break;
            }
        }

        if (leitorEncontrado == null) {
            System.out.println("Leitor não encontrado.");
            return;
        }

        System.out.println("Leitor encontrado:");
        System.out.println(leitorEncontrado.exibirInfo());

        System.out.print("Deseja [1] Excluir ou [2] Atualizar o leitor? ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            biblioteca.getLeitores().remove(leitorEncontrado);
            System.out.println("Leitor removido com sucesso!");
        } else if (opcao == 2) {
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine().trim().toUpperCase();
            System.out.print("Novo telefone: ");
            String novoTelefone = scanner.nextLine().trim();

            while (!validarTelefone(novoTelefone)) {
                System.out.println("Telefone inválido! Digite novamente: ");
                novoTelefone = scanner.nextLine().trim();
            }

            leitorEncontrado.setNome(novoNome);
            leitorEncontrado.setTelefone(novoTelefone);

            System.out.println("Leitor atualizado com sucesso!");
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
