// Universidade Federal de Minas Gerais
// Programacao Orientada a Objetos
// 2018-1
//
// Trabalho Pratico 2
// Sistema de Operadora de Celular
//
// Andre Lage
// Augusto Mafra

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import excecoes.*;

import operadora.Operadora;
import operadora.Ligacao;

public class Console {

/************************* Criar Comandos aqui dentro *************************/
    static String[] comandos = {"ajuda",
                                "cadastrar",
                                "criar_celular",
                                "criar_plano",
                                "excluir_celular",
                                "extrato",
                                "telefonar",
                                "add_creditos",
                                "sair"};

    private static boolean executarLinhaDeComando() {
        System.out.print("> ");
        String input = scan.nextLine();
        boolean status = true;
        if (input.equals("ajuda")) {
            mostrarListaDeComandos();
        } else if (input.equals("cadastrar")) {
            cadastrarCliente();
        } else if (input.equals("criar_celular")) {
            criarCelular();
        } else if (input.equals("criar_plano")) {
            criarPlano();
        } else if (input.equals("add_creditos")){
        	adicionaCreditos();
        } else if (input.equals("cobrar_tarifa")) {
            cobrarTarifa();
        } else if (input.equals("cobrar_cpmf")) {
            cobrarCPMF();
        } else if (input.equals("deposito")) {
            deposito();
        } else if (input.equals("excluir_celular")) {
            excluirCelular();
        } else if (input.equals("excluir_conta")) {
            excluirConta();
        } else if (input.equals("extrato")) {
            extrato();
        } else if (input.equals("listar_clientes")) {
            listarClientes();
        } else if (input.equals("listar_contas")) {
            listarContas();
        } else if (input.equals("saldo")) {
            saldo();
        } else if (input.equals("saque")) {
            saque();
        } else if (input.equals("telefonar")) {
            telefonar();
        } else if (input.equals("sair")) {
            status = false;
        } else {
            System.out.println("ERRO: Comando desconhecido");
        }
        return status;
    }

    // Funcoes auxiliares para os comandos
    private static void mostrarListaDeComandos() {
        System.out.println("Lista de comandos:");
        for (int i = 0; i < comandos.length; i++){
            System.out.println(comandos[i]);
        }
    }

    private static void cadastrarCliente() {
        System.out.println("Insira informacoes para cadastro do cliente:");
        String nome = promptString("Nome");
        String cpf_cnpj = promptString("CPF/CNPJ");
        String endereco = promptString("Endereco");
        String confirmacao = promptString("Confirmar cadastro do cliente? [s/n]");
        if (!confirmacao.equals("s")) {
            System.out.println("Cadastro do cliente foi cancelado pelo usuario");
            return;
        }
        try {
            operadora.addCliente(nome, endereco, cpf_cnpj);
        } catch (ClienteInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getCpfCnpj());
            return;
        }
        System.out.println(nome + " cadastrado com sucesso");
    }

    private static void criarCelular() {
        System.out.println("Insira informacoes para criacao do celular:");
        String tipo = promptString("Tipo: cartao [c] ou assinatura [a]");
        boolean prePago = tipo.equals("c") ? true : false;
        String cpf_cnpj = promptString("CPF/CNPJ do cliente");
        String plano = promptString("Plano");
        GregorianCalendar dataFatura = null;
        if (!prePago) {
            dataFatura = promptCalendar("Data de vencimento da fatura (dd/mm/aaaa ou default = hoje)");
        }
        String confirmacao = promptString("Confirmar criacao do celular? [s/n]");
        if (!confirmacao.equals("s")) {
            System.out.println("Criacao do celular foi cancelada pelo usuario");
            return;
        }
        String numero;
        try {
             numero = operadora.addCelular(prePago, cpf_cnpj, plano, dataFatura);
        } catch (ClienteInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getCpfCnpj());
            return;
        } catch (PlanoInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNome());
            return;
        }
        System.out.println("Celular de numero " + numero + " cadastrado com sucesso");
    }

    private static void criarPlano() {
        System.out.println("Insira informacoes para cadastro do plano:");
        String nome = promptString("Nome do plano");
        double valorPorMinuto = promptDouble("Valor por minuto");
        String confirmacao = promptString("Confirmar cadastro do plano? [s/n]");
        if (!confirmacao.equals("s")) {
            System.out.println("Cadastro do plano foi cancelado pelo usuario");
            return;
        }
        try {
            operadora.addPlano(nome, valorPorMinuto);
        } catch (PlanoInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNome());
            return;
        }
        System.out.println("Plano " + nome + " cadastrado com sucesso");
    }

    private static void excluirCelular() {
       System.out.println("Insira o numero do celular a ser excluido");
       String numero = promptString("Numero do Celular");
       if(operadora.removeCelular(numero)==1){
       	System.out.println("celular removido com sucesso");
       }else if (operadora.removeCelular(numero)==-1){
       	System.out.println("erro interno");
       } else if (operadora.removeCelular(numero)==-2){
       	System.out.println("numero de celular não encontrado");
       }
       //TODO corrigir o erro e adicionar situações condicionais que impeçam a exclusao

    }

    private static void adicionaCreditos(){
    	System.out.println("Insira as informações necessárias para a adiçao de creditos");
    	String numero = promptString("Numero do Celular");
    	int creditos = promptInt("Quantidade de creditos a ser adicionados");
    	int retorno = operadora.adicionaCreditos(numero, creditos);
    	if (retorno==1){
    		System.out.println("creditos adicionados com sucesso");
    	} else if(retorno==-1) {
    		System.out.println("ERRO: O celular não é do tipo pré pago");
    	} else if (retorno==-2){
    		System.out.println("ERRO: Número de celular não encontrado");
    	}
    }

    private static void excluirConta() {
        /*int numConta = promptInt("Numero da conta");
        boolean retorno = banco.removeConta(numConta);
        if(retorno){
            System.out.println("Conta removida com sucesso");
        }else{
             System.out.println("ERRO: ID de conta não corresponte a um valor cadastrado");
        }*/
    }

    private static void deposito() {
        System.out.println("Insira informacoes para o deposito:");
        /*int numConta = promptInt("Numero da conta");
        double valor = promptDouble("Valor");
        String confirmacao = promptString("Confirmar deposito de R$" + valor + " na conta " + numConta + "? [s/n]");
        if (confirmacao.equals("s")) {
            boolean status = banco.deposito(numConta, valor);
            if (status) {
                System.out.println("Deposito concluido com sucesso");
            } else {
                System.out.println("ERRO: Nenhuma conta com numero " + numConta + " encontrada");
            }
        } else {
            System.out.println("Deposito cancelado pelo usuario");
        }*/
    }

    private static void saque() {
        System.out.println("Insira informacoes para o saque:");
        /*int numConta = promptInt("Numero da conta");
        double valor = promptDouble("Valor");
        String confirmacao = promptString("Confirmar saque de R$" + valor + " da conta " + numConta + "? [s/n]");
        if (confirmacao.equals("s")) {
            boolean status = banco.saque(numConta, valor);
            if (status) {
                System.out.println("Saque concluido com sucesso");
            } else {
                System.out.println("ERRO: Saldo da conta insuficiente para saque");
            }
        } else {
            System.out.println("Saque cancelado pelo usuario");
        }*/
    }

    private static void telefonar() {
        System.out.println("Insira informacoes para o registro da ligacao:");
        String numeroDoCelular = promptString("Numero do celular");
        // TODO Registrar tambem hora e minuto da ligacao
        GregorianCalendar dataLigacao = promptCalendar("Data da ligacao(dd/mm/aaaa ou default = hoje)");
        double duracao = promptDouble("Duracao da chamada (minutos)");
        String confirmacao = promptString("Confirmar ligacao? [s/n]");
        if (!confirmacao.equals("s")) {
            System.out.println("Ligacao cancelada pelo usuario");
            return;
        }
        try {
            operadora.registrarLigacao(numeroDoCelular, dataLigacao, duracao);
        } catch (CelularInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNumeroDoCelular());
            return;
        }
        System.out.println("Ligacao concluida com sucesso");
    }

    private static void cobrarTarifa() {
        System.out.println("Tarifa cobrada com sucesso");
    }

    private static void cobrarCPMF() {
        System.out.println("CPMF cobrada com sucesso");
    }

    private static void saldo() {
        System.out.println("Insira o número da conta cujo saldo se deseja consutar:");
        /*int numConta = promptInt("Numero da conta");
        double saldo = banco.saldo(numConta);
        if (saldo == -1){
            System.out.println("ERRO: Nenhuma conta com o número " + numConta);
            return;
        }
        System.out.println("O saldo da conta " + numConta  + " é " + saldo);*/
    }

    private static void extrato() {
        System.out.println("Insira informacoes para o extrato:");
        String numeroDoCelular = promptString("Numero do celular");
        GregorianCalendar dataInicial = promptCalendar("Data da inicial(dd/mm/aaaa ou default = hoje)");
        List<Ligacao> ligacoes;
        try {
            ligacoes = operadora.getExtrato(numeroDoCelular, dataInicial);
        } catch (CelularInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNumeroDoCelular());
            return;
        }
        System.out.println(ligacoes);
    }

    private static void listarClientes() {
        System.out.println("Lista de clientes:");
    }

    private static void listarContas() {
        System.out.println("Lista de contas:");
    }

    private static void salvar() {
        System.out.println("Salvando dados do sistema em " + databaseFile.toAbsolutePath());
    }

    private static void restaurar() {
        System.out.println("Restaurando dados do sistema de " + databaseFile.toAbsolutePath() + "\n");
    }
/************************* Criar Comandos aqui dentro *************************/

    private static String promptString(String descricao) {
        System.out.print("\t>>> " + descricao + ": ");
        return scan.nextLine();
    }

    private static int promptInt(String descricao) {
        System.out.print("\t>>> " + descricao + ": ");
        int input;
        try {
            input = Integer.parseInt(scan.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptInt(descricao);
        }
        return input;
    }

    private static double promptDouble(String descricao) {
        System.out.print("\t>>> " + descricao + ": ");
        double input;
        try {
            input = Double.parseDouble(scan.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptDouble(descricao);
        }
        if (input < 0) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptDouble(descricao);
        }
        return input;
    }

    private static GregorianCalendar promptCalendar(String descricao) {
        GregorianCalendar input = new GregorianCalendar();
        System.out.print("\t>>> " + descricao + ": ");
        String inputString = scan.nextLine();
        if (inputString.equals("default") || inputString.equals("")) return null;
        String[] calendarInfo = inputString.split("/");
        int dia = 0, mes = 0, ano = 0;
        try {
            dia = Integer.parseInt(calendarInfo[0]);
            mes = Integer.parseInt(calendarInfo[1]) - 1; // mes e' indexado a partir do 0
            ano = Integer.parseInt(calendarInfo[2]);
        } catch(NumberFormatException e) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptCalendar(descricao);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptCalendar(descricao);
        }
        input = new GregorianCalendar(ano, mes, dia);
        if (dia > input.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) ||
            dia < 1 ||
            mes > input.getActualMaximum(GregorianCalendar.MONTH) ||
            mes < 0) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptCalendar(descricao);
        }
        return input;
    }

    private static void iniciarLinhaDeComando() {
        scan = new Scanner(System.in);
        databaseFile = Paths.get("database.txt");

        System.out.println("Universidade Federal de Minas Gerais");
        System.out.println("Programacao Orientada a Objetos");
        System.out.println("2018-1\n");
        System.out.println("Trabalho Pratico 2");
        System.out.println("Sistema de Operadora de Celular\n");
        System.out.println("Andre Lage");
        System.out.println("Augusto Mafra\n");

        operadora = new Operadora("pooTelecom");

        System.out.println("\nEntre o comando 'ajuda' para obter uma lista dos comandos disponiveis\n");
    }

    private static void setupTeste() {
        hoje = new GregorianCalendar();
        System.out.println("-teste: Iniciando setup de teste");
        operadora.addCliente("ze", "rua do ze", "123");
        operadora.addCliente("jao", "rua do jao", "456");
        operadora.addPlano("basico", 1000);
        operadora.addPlano("premium", 2000);
        operadora.addCelular(true, "123", "basico", hoje);
        operadora.addCelular(false, "123", "premium", hoje);
        operadora.addCelular(true, "456", "basico", hoje);
        operadora.registrarLigacao("000000000", hoje, 5);
        operadora.registrarLigacao("000000000", hoje, 6);
        operadora.registrarLigacao("000000001", hoje, 7);
        operadora.registrarLigacao("000000002", hoje, 8);
    }

    public static void main(String[] args) {
        iniciarLinhaDeComando();
        if (args.contains("-teste")) {
            setupTeste();
        }
        boolean status = true;
        while (status) {
            status = executarLinhaDeComando();
        }
    }

    private static Operadora operadora;
    private static Scanner scan;
    private static Path databaseFile;
}
