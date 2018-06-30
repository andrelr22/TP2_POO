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

class Cliente {
    private String nome;
    private String endereco;
    private String cpf_cnpj;

    public Cliente(String nome, String endereco, String cpf_cnpj) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf_cnpj = cpf_cnpj;
    }

    public boolean equals(Cliente outro) {
        return this.cpf_cnpj == outro.cpf_cnpj;
    }
}
