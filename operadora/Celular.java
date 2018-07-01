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

    public void registrarLigacao(GregorianCalendar dataHora, double duracao) {
        ligacoes.add(new Ligacao(dataHora, duracao));
    }

    public abstract boolean isPosPago();
}
