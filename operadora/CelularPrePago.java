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

public class CelularPrePago extends Celular {
    private double saldo;
    private GregorianCalendar dataDeValidadeDoSaldo;

    public CelularPrePago() {
        super();
        saldo = 0;
        dataDeValidadeDoSaldo = new GregorianCalendar(); // hoje
    }
}
