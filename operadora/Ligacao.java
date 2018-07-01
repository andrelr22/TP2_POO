// Universidade Federal de Minas Gerais
// Programacao Orientada a Objetos
// 2018-1
//
// Trabalho Pratico 2
// Sistema de Operadora de Celular
//
// Andre Lage
// Augusto Mafra

package operadora;

import java.util.GregorianCalendar;

public class Ligacao {
    private GregorianCalendar dataHora;
    private double duracao;

    public Ligacao(GregorianCalendar dataHora, double duracao) {
        this.dataHora = dataHora;
        this.duracao = duracao;
    }
}
