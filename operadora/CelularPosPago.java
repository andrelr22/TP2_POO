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

public class CelularPosPago extends Celular {
    private GregorianCalendar dataDaFatura;

    public CelularPosPago(Plano plano, GregorianCalendar dataDaFatura, String cpf) {
        super(plano, cpf);
        this.dataDaFatura = dataDaFatura;
    }

    public boolean isPosPago(){
    	return true;
    }

    public List<String> obterInfo() {
        int diaDaFatura = dataDaFatura.get(GregorianCalendar.DAY_OF_MONTH);

        GregorianCalendar mesAnterior = new GregorianCalendar();
        mesAnterior.add(GregorianCalendar.MONTH, -1);
        mesAnterior.set(GregorianCalendar.DAY_OF_MONTH, diaDaFatura);

        GregorianCalendar mesAtual = new GregorianCalendar();
        mesAtual.set(GregorianCalendar.DAY_OF_MONTH, diaDaFatura);

        List<Ligacao> ligacoesCobradas = getLigacoes(mesAnterior, mesAtual);
        double totalDaConta = 0;
        for (Ligacao l : ligacoesCobradas) {
            totalDaConta += l.getValorCobrado();
        }
        List<String> info = new Vector<String>();
        info.add("valor da conta");
        info.add("" + totalDaConta);
        return info;
    }

    public boolean podeExcluir() {
        GregorianCalendar hoje = new GregorianCalendar();
        GregorianCalendar faturaAtual = new GregorianCalendar();
        faturaAtual.set(GregorianCalendar.DAY_OF_MONTH,
                        dataDaFatura.get(GregorianCalendar.DAY_OF_MONTH));
        return hoje.after(faturaAtual);
    }
}
