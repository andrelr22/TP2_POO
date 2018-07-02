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

public class PlanoInvalidoException extends Exception {
    // Isso deve ser somente algum identificador unico para a classe.
    // A escolha do 0XAA1 e' arbitraria
    private static final long serialVersionUID = 0xAA1;
    private String nomeDoPlano;

    public PlanoInvalidoException(String mensagemDeErro, String nomeDoPlano) {
        super(mensagemDeErro);
        this.nomeDoPlano = nomeDoPlano;
    }

    public String getNome() {
        String copia = new String(nomeDoPlano);
        return copia;
    }
}
