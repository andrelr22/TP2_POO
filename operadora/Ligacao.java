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

    public GregorianCalendar getDataHora() {
        return dataHora;
    }

    public String toString() {
        String result = new String();
        result += "\t" + dataHora.get(GregorianCalendar.DAY_OF_MONTH);
        result += "/" + (dataHora.get(GregorianCalendar.MONTH) + 1);
        result += "/" + dataHora.get(GregorianCalendar.YEAR);
        result += "\t" + duracao;
        // TODO Valor cobrado na ligacao
        result += "\n";
        return result;
    }
}
