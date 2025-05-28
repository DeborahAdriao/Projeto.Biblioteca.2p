package projeto.biblioteca;

public class Util {
    // classe de condições

    // Valida se o CPF e TELEFONE tem 11 dígitos numéricos

    public static boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }
    public static boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches("\\d{11}");
    }

    // Verifica se o estoque é positivo
    public static boolean validarEstoque(int estoque) {
        return estoque > 0;
    }
}
