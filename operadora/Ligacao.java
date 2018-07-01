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
    private double valorCobrado;

    public Ligacao(GregorianCalendar dataHora, double duracao, double valorCobrado) {
        this.dataHora = dataHora;
        this.duracao = duracao;
        this.valorCobrado = valorCobrado;
    }

    public GregorianCalendar getDataHora() {
        return dataHora;
    }

    public String toString() {
        String result = new String();
        result += "\t" + dataHora.get(GregorianCalendar.DAY_OF_MONTH);
        result += "/" + (dataHora.get(GregorianCalendar.MONTH) + 1);
        result += "/" + dataHora.get(GregorianCalendar.YEAR);
        result += "\t" + duracao + " minutos";
        result += "\tR$" + valorCobrado;
        result += "\n";
        return result;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }
}
