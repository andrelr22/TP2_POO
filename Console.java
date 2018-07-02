// Universidade Federal de Minas Gerais
// Programacao Orientada a Objetos
// 2018-1
//
// Trabalho Pratico 2
// Sistema de Operadora de Celular
//
// Andre Lage
// Augusto Mafra

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import excecoes.*;

import operadora.Operadora;
import operadora.Ligacao;

public class Console {

/************************* Criar Comandos aqui dentro *************************/
    static String[] comandos = {"ajuda",
                                "add_creditos",
                                "cadastrar",
                                "celular_info",
                                "criar_celular",
                                "criar_plano",
                                "excluir_celular",
                                "extrato",
                                "telefonar",
                                "listar",
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
        } else if (input.equals("celular_info")) {
            obterInfoDoCelular();
        } else if (input.equals("excluir_celular")) {
            excluirCelular();
        } else if (input.equals("extrato")) {
            extrato();
        } else if (input.equals("listar")) {
            listar();
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
        try {
            operadora.removeCelular(numero);
        } catch (CelularInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNumeroDoCelular());
            return;
        } catch (ClienteInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getCpfCnpj());
            return;
        }
        System.out.println("Celular " + numero + " excluido com sucesso");
    }

    private static void adicionaCreditos(){
    	System.out.println("Insira as informações necessárias para a adiçao de creditos");
    	String numero = promptString("Numero do Celular");
    	double creditos = promptDouble("Quantidade de creditos a ser adicionados");
    	int retorno = operadora.adicionaCreditos(numero, creditos);
    	if (retorno==1){
    		System.out.println("creditos adicionados com sucesso");
    	} else if(retorno==-1) {
    		System.out.println("ERRO: O celular não é do tipo pré pago");
    	} else if (retorno==-2){
    		System.out.println("ERRO: Número de celular não encontrado");
    	}
    }

    private static void listar(){
    	String tipo = promptString("Digite o que se deseja listar: Clientes [c], Planos [p] ou Celulares[e]");
    	if (tipo.equals("c")){

    		System.out.println("Clientes cadastrados:");

			List<String> info = new Vector<String>();
			info=operadora.listarClientes();

			for (int i = 0; i < info.size(); i++) {
	            System.out.println(info.get(i));
	        }

    	}else if(tipo.equals("p")){

    		System.out.println("Planos Cadastrados");

    		List<String> info = new Vector<String>();
    		info=operadora.listarPlanos();

    		for (int i = 0; i < info.size(); i++) {
	            System.out.println(info.get(i));
	        }

    	}else if(tipo.equals("e")){

    		System.out.println("Celulares cadastrados:");

    		List<String> info = new Vector<String>();
    		info=operadora.listarCelulares();

    		for (int i = 0; i < info.size(); i++) {
	            System.out.println(info.get(i));
	        }

    	}else{
    		System.out.println("Comando inválido");
    	}



    }

    private static void telefonar() {
        System.out.println("Insira informacoes para o registro da ligacao:");
        String numeroDoCelular = promptString("Numero do celular");
        GregorianCalendar dataLigacao = promptCalendar("Data da ligacao(dd/mm/aaaa ou default = hoje)");
        GregorianCalendar horaLigacao = promptHora("Horario da ligacao(hh:mm ou default = agora)");
        double duracao = promptDouble("Duracao da chamada (minutos)");
        String confirmacao = promptString("Confirmar ligacao? [s/n]");
        if (!confirmacao.equals("s")) {
            System.out.println("Ligacao cancelada pelo usuario");
            return;
        }

        // J.O.V.E.M. = Java Overly Verbose Experimental Method
        if (dataLigacao == null) dataLigacao = new GregorianCalendar();
        if (horaLigacao == null) horaLigacao = new GregorianCalendar();
        dataLigacao.set(GregorianCalendar.HOUR_OF_DAY, horaLigacao.get(GregorianCalendar.HOUR_OF_DAY));
        dataLigacao.set(GregorianCalendar.MINUTE, horaLigacao.get(GregorianCalendar.MINUTE));

        try {
            operadora.registrarLigacao(numeroDoCelular, dataLigacao, duracao);
        } catch (CelularInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNumeroDoCelular());
            return;
        }
        System.out.println("Ligacao concluida com sucesso");
    }

    private static void obterInfoDoCelular() {
        System.out.println("Insira informacoes para consulta do celular:");
        String numeroDoCelular = promptString("Numero do celular");
        List<String> info = new Vector<String>();
        try {
            info = operadora.obterInfoDoCelular(numeroDoCelular);
        } catch (CelularInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNumeroDoCelular());
            return;
        }
        mostrarInfoDoCelular(info);
    }

    private static void mostrarInfoDoCelular(List<String> info) {
        for (int i = 0; i < info.size(); i++) {
            System.out.print(info.get(i) + ": ");
            i++;
            System.out.println(info.get(i));
        }
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

    private static GregorianCalendar promptHora(String descricao) {
        GregorianCalendar input = new GregorianCalendar();
        System.out.print("\t>>> " + descricao + ": ");
        String inputString = scan.nextLine();
        if (inputString.equals("default") || inputString.equals("")) return null;
        String[] horaInfo = inputString.split(":");
        int hora = 0, minuto = 0;
        try {
            hora = Integer.parseInt(horaInfo[0]);
            minuto = Integer.parseInt(horaInfo[1]);
        } catch(NumberFormatException e) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptHora(descricao);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptHora(descricao);
        }
        input = new GregorianCalendar();
        input.set(GregorianCalendar.HOUR_OF_DAY, hora);
        input.set(GregorianCalendar.MINUTE, minuto);
        if (hora > 23 || minuto > 59) {
            System.out.println("\tERRO: Insira um " + descricao + " valido");
            input = promptHora(descricao);
        }
        return input;
    }

    private static void iniciarLinhaDeComando() {
        scan = new Scanner(System.in);

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
        GregorianCalendar hoje = new GregorianCalendar();
        System.out.println("-teste: Iniciando setup de teste\n");
        try {
            operadora.addCliente("ze", "rua do ze", "123");
            operadora.addCliente("jao", "rua do jao", "456");
            operadora.addPlano("basico", 1000);
            operadora.addPlano("premium", 2000);
            operadora.addCelular(true, "123", "basico", hoje);
            operadora.addCelular(false, "123", "premium", hoje);
            operadora.addCelular(true, "456", "basico", hoje);
            operadora.registrarLigacao("000000001", hoje, 7);
        } catch (ClienteInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getCpfCnpj());
        } catch (PlanoInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNome());
        } catch (CelularInvalidoException excp) {
            System.out.println(excp.getMessage() + excp.getNumeroDoCelular());
        }
    }

    public static void main(String[] args) {
        iniciarLinhaDeComando();
        if (Arrays.asList(args).contains("-teste")) {
            setupTeste();
        }
        boolean status = true;
        while (status) {
            status = executarLinhaDeComando();
        }
    }

    private static Operadora operadora;
    private static Scanner scan;
}
