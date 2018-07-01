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

public class Operadora {
    private String nome;
    private List<Cliente> clientes;
    private List<Plano> planos;
    private List<Celular> celulares;

    public Operadora(String nome) {
        this.nome = nome;
        this.clientes = new Vector<Cliente>();
        this.planos = new Vector<Plano>();
        this.celulares = new Vector<Celular>();
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

    public void addPlano(String nome,
                         double valorPorMinuto) throws PlanoInvalidoException {
        Plano novoPlano = new Plano(nome, valorPorMinuto);
        if (planoJaExiste(novoPlano)) {
            throw new PlanoInvalidoException("ERRO Ja existe plano cadastrado com o nome: ",
                                             novoPlano);
        }
        planos.add(novoPlano);
    }

    private boolean planoJaExiste(Plano plano) {
        for (Plano p : planos) {
            if (p.equals(plano)) {
                return true;
            }
        }
        return false;
    }

    public String addCelular(boolean prePago,
                           String cpf_cnpj,
                           String nomeDoPlano,
                           GregorianCalendar dataFatura) throws ClienteInvalidoException,
                                                                PlanoInvalidoException {
        if (dataFatura == null) {
            dataFatura = new GregorianCalendar(); // hoje
        }
        Cliente cliente = new Cliente("", "", cpf_cnpj);
        if (!clienteJaExiste(cliente)) {
            throw new ClienteInvalidoException("ERRO Nao existe cliente cadastrado com o CPF/CNPJ: ",
                                               cliente);
        }
        Plano plano = new Plano(nomeDoPlano, 0);
        if (!planoJaExiste(plano)) {
            throw new PlanoInvalidoException("ERRO Nao existe plano cadastrado com o nome: ",
                                             plano);
        }
        Celular novoCelular;
        if (prePago) {
            novoCelular = new CelularPrePago(plano, cpf_cnpj);
        } else {
            novoCelular = new CelularPosPago(plano, dataFatura, cpf_cnpj);
        }
        celulares.add(novoCelular);
        cliente.addCelular(novoCelular);
        return (novoCelular.getNumero());
    }

    private Celular getCelular(String numeroDoCelular) {
        for (Celular c : celulares) {
            if (c.getNumero().equals(numeroDoCelular)) {
                return c;
            }
        }
        return null;
    }

    public void registrarLigacao(String numeroDoCelular,
                                 GregorianCalendar dataHora,
                                 double duracao) throws CelularInvalidoException {
        Celular celular = getCelular(numeroDoCelular);
        if (celular == null) {
            throw new CelularInvalidoException("ERRO Nao existe celular com o numero: ", numeroDoCelular);
        }
        celular.registrarLigacao(dataHora, duracao);
    }

    public int removeCelular(String numeroDoCelular){
        int contador=0;
        for(Celular c : celulares){
            if(numeroDoCelular.equals(c.getNumero())){
                System.out.println("yo");
                String cpf = c.getCpf();
                celulares.remove(contador);
                System.out.println("yo");
                int contador2=0;
                for (Cliente cl: clientes){
                   System.out.println("for cliente");
                    if(cpf.equals(cl.getCpfCnpj())){
                        System.out.println("achou o cpf igual");
                        if(cl.removeCelular(numeroDoCelular)==true){
                            System.out.println("operacao com sucesso");
                         return 1;
                        }
                        else{
                            System.out.println("operacao fracassou");
                           return -1; 
                        } 
                    }
                    contador2=contador2 + 1;
                }
                System.out.println("nao achou o cliente com o cpf");
                return -1;
            } 
            System.out.println("antes contador");
            contador=contador+1;
        }
        return -2;
    }

    public int adicionaCreditos(String numeroCelular, int valor){
        Celular C;
        if(C==null){
            return -2;
        }
        if(C.isPosPago()==false){
            ((CelularPrePago) C).addSaldo(valor);
            return 1;
        }else{
            return -1;
        }
        }
    }
        return-2;
    }
}
