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
        if (getCliente(cpf_cnpj) != null) {
            throw new ClienteInvalidoException("ERRO Ja existe cliente cadastrado com o CPF/CNPJ: ",
                                               nome);
        }
        clientes.add(novoCliente);
    }

    private Cliente getCliente(String cpfCnpj) {
        for (Cliente c : clientes) {
            if (c.getCpfCnpj().equals(cpfCnpj)) {
                return c;
            }
        }
        return null;
    }

    public void addPlano(String nome,
                         double valorPorMinuto) throws PlanoInvalidoException {
        Plano novoPlano = new Plano(nome, valorPorMinuto);
        if (getPlano(nome) != null) {
            throw new PlanoInvalidoException("ERRO Ja existe plano cadastrado com o nome: ",
                                             nome);
        }
        planos.add(novoPlano);
    }

    private Plano getPlano(String nome) {
        for (Plano p : planos) {
            if (p.getNome().equals(nome)) {
                return p;
            }
        }
        return null;
    }

    public String addCelular(boolean prePago,
                           String cpf_cnpj,
                           String nomeDoPlano,
                           GregorianCalendar dataFatura) throws ClienteInvalidoException,
                                                                PlanoInvalidoException {
        if (dataFatura == null) {
            dataFatura = new GregorianCalendar(); // hoje
        }
        Cliente cliente = getCliente(cpf_cnpj);
        if (cliente == null) {
            throw new ClienteInvalidoException("ERRO Nao existe cliente cadastrado com o CPF/CNPJ: ",
                                               cpf_cnpj);
        }
        Plano plano = getPlano(nomeDoPlano);
        if (plano == null) {
            throw new PlanoInvalidoException("ERRO Nao existe plano cadastrado com o nome: ",
                                             nomeDoPlano);
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
        if (dataHora == null) {
            dataHora = new GregorianCalendar(); // hoje
        }
        Celular celular = getCelular(numeroDoCelular);
        if (celular == null) {
            throw new CelularInvalidoException("ERRO Nao existe celular com o numero: ", numeroDoCelular);
        }
        celular.registrarLigacao(dataHora, duracao);
    }

    public void removeCelular(String numeroDoCelular) throws CelularInvalidoException,
                                                             ClienteInvalidoException {
        Celular celular = getCelular(numeroDoCelular);
        if (celular == null) {
            throw new CelularInvalidoException("ERRO Nao existe celular com o numero: ", numeroDoCelular);
        }
        Cliente cliente = getCliente(celular.getCpf());
        if (cliente == null) {
            throw new ClienteInvalidoException("ERRO O celular pertence a um CPF/CNPJ nao cadastrado: ", celular.getCpf());
        }
        if (!celular.podeExcluir()) {
            throw new CelularInvalidoException("ERRO Existem pendencias financeiras no celular: ", numeroDoCelular);
        }
        cliente.removeCelular(celular);
        celulares.remove(celular);
    }

    public int adicionaCreditos(String numeroCelular, double valor){
        Celular C = getCelular(numeroCelular);
        if (C == null) {
            return -2;
        }
        if (C.isPosPago() == false) {
            ((CelularPrePago) C).addSaldo(valor);
            return 1;
        } else {
            return -1;
        }
        //return-2;
    }

    public List<Ligacao> getExtrato(String numeroDoCelular,
                                    GregorianCalendar dataInicial) throws CelularInvalidoException {
        if (dataInicial == null) {
            dataInicial = new GregorianCalendar(); // hoje
        }
        Celular celular = getCelular(numeroDoCelular);
        if (celular == null) {
            throw new CelularInvalidoException("ERRO Nao existe celular com o numero: ", numeroDoCelular);
        }
        dataInicial.set(GregorianCalendar.HOUR_OF_DAY, 0);
        dataInicial.set(GregorianCalendar.MINUTE, 0);
        dataInicial.set(GregorianCalendar.SECOND, 0);
        return celular.getLigacoes(dataInicial);
    }

    public List<String> obterInfoDoCelular(String numeroDoCelular) throws CelularInvalidoException {
        Celular celular = getCelular(numeroDoCelular);
        if (celular == null) {
            throw new CelularInvalidoException("ERRO Nao existe celular com o numero: ", numeroDoCelular);
        }
        List<String> info = new Vector<String>();
        info.add("tipo");
        info.add(celular.isPosPago() ? "pos-pago" : "pre-pago");
        info.addAll(celular.obterInfo());
        return info;
    }



    public List<String> listarCelulares(){
        List<String> info = new Vector<String>();
        for (Celular c : celulares){
           info.add("Número: " + c.getNumero());
           info.add("CPF/CNPJ do cliente: " + c.getCpf());
           info.add(" ");
        }

        return info;
    }

        public List<String> listarPlanos(){
            List<String> info = new Vector<String>();
            for (Plano p : planos){
               info.add("Nome do plano: " + p.getNome());
               info.add("Valor por minuto: " + p.getValor());
               info.add(" ");
            }
        return info;
    }

    public List<String> listarClientes(){
        List<String> info = new Vector<String>();
        for (Cliente c : clientes){
           info.add("Nome: " + c.getNome());
           info.add("CPF/CNPJ: " + c.getCpfCnpj());
           info.add("Endereco: " + c.getEndereco());
           info.add("Celulares do Cliente: ");
           info.addAll(c.getCelulares());
           info.add(" ");
        }
        return info;
    }


}
