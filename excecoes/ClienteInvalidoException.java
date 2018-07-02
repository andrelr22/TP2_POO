// Universidade Federal de Minas Gerais
// Programacao Orientada a Objetos
// 2018-1
//
// Trabalho Pratico 2
// Sistema de Operadora de Celular
//
// Andre Lage
// Augusto Mafra

package excecoes;

public class ClienteInvalidoException extends Exception {
    // Isso deve ser somente algum identificador unico para a classe.
    // A escolha do 0XAA0 e' arbitraria
    private static final long serialVersionUID = 0xAA0;
    private String cpfCnpjDoCliente;

    public ClienteInvalidoException(String mensagemDeErro, String cpfCnpj) {
        super(mensagemDeErro);
        this.cpfCnpjDoCliente = cpfCnpj;
    }

    public String getCpfCnpj() {
        String copia = new String(cpfCnpjDoCliente);
        return copia;
    }
}
