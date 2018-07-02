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

import excecoes.*;

public abstract class Celular {
    static private int proximoNumero = 0;

    private String cpf_cliente;
    private String numero;
    private List<Ligacao> ligacoes;
    private Plano plano;


    public Celular(Plano plano, String  cpf) {
        // Formato 0 0000 0000 para os numeros de telefone
        cpf_cliente = cpf;
        this.numero = String.format("%09d", proximoNumero++);
        this.ligacoes = new Vector<Ligacao>();
        this.plano = plano;
    }

    public String getCpf(){
        String copia = new String(cpf_cliente);
        return copia;
    }

    public String getNumero() {
        String copia = new String(numero);
        return copia;
    }

    public double cobrar(double duracao) {
        return plano.cobrar(duracao);
    }

    public void registrarLigacao(GregorianCalendar dataHora, double duracao) throws CelularInvalidoException {
        ligacoes.add(new Ligacao(dataHora, duracao, cobrar(duracao)));
    }

    public List<Ligacao> getLigacoes(GregorianCalendar dataInicial) {
        GregorianCalendar hoje = new GregorianCalendar();
        hoje.set(GregorianCalendar.HOUR_OF_DAY, GregorianCalendar.getInstance().getMaximum(GregorianCalendar.HOUR_OF_DAY));
        hoje.set(GregorianCalendar.MINUTE, GregorianCalendar.getInstance().getMaximum(GregorianCalendar.MINUTE));
        hoje.set(GregorianCalendar.SECOND, GregorianCalendar.getInstance().getMaximum(GregorianCalendar.SECOND));
        return getLigacoes(dataInicial, hoje);
    }

    protected List<Ligacao> getLigacoes(GregorianCalendar dataInicial, GregorianCalendar dataFinal) {
        List<Ligacao> extrato = new Vector<Ligacao>();
        for (Ligacao l : ligacoes) {
            GregorianCalendar dataHora = l.getDataHora();
            if ((dataHora.after(dataInicial) ||
                 dataHora.equals(dataInicial)) &&
                (dataHora.before(dataFinal) ||
                 dataHora.equals(dataFinal))) {
                extrato.add(l);
            }
        }
        return extrato;
    }

    public abstract boolean isPosPago();
    public abstract List<String> obterInfo();
}
