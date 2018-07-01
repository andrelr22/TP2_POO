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
import java.util.List;
import java.util.Vector;

public class CelularPrePago extends Celular {
    private double saldo;
    private GregorianCalendar dataDeValidadeDoSaldo;

    public CelularPrePago(Plano plano, String cpf) {
        super(plano, cpf);
        saldo = 0;
        dataDeValidadeDoSaldo = new GregorianCalendar(); // hoje
    }

    public boolean isPosPago(){
    	return false;
    }

    public void addSaldo(int valor){
        saldo=saldo+valor;
        dataDeValidadeDoSaldo.add(GregorianCalendar.DAY_OF_MONTH, 180);
    }

    public List<String> obterInfo() {
        List<String> info = new Vector<String>();
        info.add("saldo");
        info.add("" + saldo);
        info.add("data de validade");
        info.add("" + dataDeValidadeDoSaldo.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                      (dataDeValidadeDoSaldo.get(GregorianCalendar.MONTH) + 1) + "/" +
                      dataDeValidadeDoSaldo.get(GregorianCalendar.YEAR));
        return info;
    }
}
