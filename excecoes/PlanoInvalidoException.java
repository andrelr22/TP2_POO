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

import operadora.Plano;

public class PlanoInvalidoException extends Exception {
    // Isso deve ser somente algum identificador unico para a classe.
    // A escolha do 0XAA1 e' arbitraria
    private static final long serialVersionUID = 0xAA1;
    private Plano plano;

    public PlanoInvalidoException(String mensagemDeErro, Plano plano) {
        super(mensagemDeErro);
        this.plano = new Plano(plano);
    }

    public String getNome() {
        return plano.getNome();
    }
}
