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

public class Operadora {
    private String nome;
    private List<Cliente> clientes;

    public Operadora(String nome) {
        this.nome = nome;
        this.clientes = new Vector<Cliente>();
    }

    public void addCliente(String nome,
                           String endereco,
                           String cpf_cnpj) throws ClienteInvalidoException {
        Cliente novoCliente = new Cliente(nome, endereco, cpf_cnpj);
        if (clientes.contains(novoCliente)) {
            throw new ClienteInvalidoException("ERRO Ja existe cliente cadastrado com o CPF/CNPJ",
                                               novoCliente);
        }
        clientes.add(novoCliente);
    }
}

class ClienteInvalidoException extends Exception {
    // Isso deve ser somente algum identificador unico para a classe.
    // A escolha do 0XAA0 e' arbitraria
    private static final long serialVersionUID = 0xAA0;
    private Cliente cliente;

    public ClienteInvalidoException(String mensagemDeErro, Cliente cliente) {
        super(mensagemDeErro);
        this.cliente = cliente;
    }
}
