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

public class CelularInvalidoException extends Exception {
    // Isso deve ser somente algum identificador unico para a classe.
    // A escolha do 0XAA2 e' arbitraria
    private static final long serialVersionUID = 0xAA2;
    private String numeroDoCelular;

    public CelularInvalidoException(String mensagemDeErro, String numeroDoCelular) {
        super(mensagemDeErro);
        this.numeroDoCelular = numeroDoCelular;
    }

    public String getNumeroDoCelular() {
        String copia = new String(numeroDoCelular);
        return copia;
    }
}
