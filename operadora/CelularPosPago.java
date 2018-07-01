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

public class CelularPosPago extends Celular {
    private GregorianCalendar dataDaFatura;

    public CelularPosPago(Plano plano, GregorianCalendar dataDaFatura, String cpf) {
        super(plano, cpf);
        this.dataDaFatura = dataDaFatura;
    }

    public boolean isPosPago(){
    	return true;
    }
}
