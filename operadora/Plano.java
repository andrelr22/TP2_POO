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

public class Plano {
    private String nome;
    private double valorPorMinuto;

    public Plano(String nome, double valorPorMinuto) {
        this.nome = nome;
        this.valorPorMinuto = valorPorMinuto;
    }

    public Plano(Plano outro) {
        this.nome = outro.nome;
        this.valorPorMinuto = outro.valorPorMinuto;
    }

    public boolean equals(Plano outro) {
        return this.nome.equals(outro.nome);
    }

    public String getNome() {
        String copia = new String(nome);
        return copia;
    }

    public double cobrar(double duracao) {
        return valorPorMinuto * duracao;
    }
}
