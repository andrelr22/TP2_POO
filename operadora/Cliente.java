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

import java.util.List;
import java.util.Vector;

public class Cliente {
    private String nome;
    private String endereco;
    private String cpf_cnpj;
    private List<Celular> celulares;

    public Cliente(String nome, String endereco, String cpf_cnpj) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf_cnpj = cpf_cnpj;
        this.celulares = new Vector<Celular>();
    }

    public Cliente(Cliente outro) {
        this.nome = outro.nome;
        this.endereco = outro.endereco;
        this.cpf_cnpj = outro.cpf_cnpj;
    }

    public boolean equals(Cliente outro) {
        return this.cpf_cnpj.equals(outro.cpf_cnpj);
    }

    public String getCpfCnpj() {
        String copia = new String(cpf_cnpj);
        return copia;
    }

    public void addCelular(Celular celular) {
        celulares.add(celular);
    }

    public void removeCelular(Celular celular){
        celulares.remove(celular);
    }
}
