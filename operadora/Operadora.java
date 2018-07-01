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

import excecoes.ClienteInvalidoException;

public class Operadora {
    private String nome;
    private List<Cliente> clientes;
    private List<Plano> planos;

    public Operadora(String nome) {
        this.nome = nome;
        this.clientes = new Vector<Cliente>();
        this.planos = new Vector<Plano>();
    }

    public void addCliente(String nome,
                           String endereco,
                           String cpf_cnpj) throws ClienteInvalidoException {
        Cliente novoCliente = new Cliente(nome, endereco, cpf_cnpj);
        if (clienteJaExiste(novoCliente)) {
            throw new ClienteInvalidoException("ERRO Ja existe cliente cadastrado com o CPF/CNPJ: ",
                                               novoCliente);
        }
        clientes.add(novoCliente);
    }

    private boolean clienteJaExiste(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.equals(cliente)) {
                return true;
            }
        }
        return false;
    }
}
