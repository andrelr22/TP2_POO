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
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Console {

/************************* Criar Comandos aqui dentro *************************/
    static String[] comandos = {"ajuda",
                                "cadastrar",
                                "criar_conta",
                                "cobrar_tarifa",
                                "cobrar_cpmf",
                                "deposito",
                                "excluir_cliente",
                                "excluir_conta",
                                "extrato",
                                "listar_clientes",
                                "listar_contas",
                                "saldo",
                                "saque",
                                "transferencia",
                                "sair"};

    private static boolean executarLinhaDeComando() {
        System.out.print("> ");
        String input = scan.nextLine();
        boolean status = true;
        if (input.equals("ajuda")) {
            mostrarListaDeComandos();
        } else if (input.equals("cadastrar")) {
            cadastrarCliente();
        } else if (input.equals("criar_conta")) {
            criarConta();
        } else if (input.equals("cobrar_tarifa")) {
            cobrarTarifa();
        } else if (input.equals("cobrar_cpmf")) {
            cobrarCPMF();
        } else if (input.equals("deposito")) {
            deposito();
        } else if (input.equals("excluir_cliente")) {
            excluirCliente();
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
        } else if (input.equals("transferencia")) {
            transferencia();
        } else if (input.equals("sair")) {
            salvar();
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
        /*String nome = promptString("Nome");
        String cpf_cnpj = promptString("CPF/CNPJ");
        String endereco = promptString("Endereco");
        String telefone = promptString("Telefone");
        String confirmacao = promptString("Confirmar cadastro do cliente? [s/n]");
        if (confirmacao.equals("s")) {
            boolean status = banco.addCliente(new Cliente(nome, cpf_cnpj, endereco, telefone));
            if (status) {
                System.out.println("Cadastro do cliente concluido com sucesso");
            } else {
                System.out.println("ERRO: Ja existe cliente cadastrado com o CPF/CNPJ especificado");
            }
        } else {
            System.out.println("Cadastro do cliente foi cancelado pelo usuario");
        }*/
    }

    private static void criarConta() {
        System.out.println("Insira informacoes para criacao da conta:");
        /*String cpf_cnpj = promptString("CPF/CNPJ");
        banco.Cliente cliente = banco.getCliente(cpf_cnpj);
        if (cliente == null){
            System.out.println("ERRO: Nenhum cliente cadastrado com CPF/CNPJ " + cpf_cnpj);
            return;
        }
        banco.Conta conta = banco.criaConta(cliente);
        System.out.println("Criacao da conta numero " + conta.getNumConta() +
                           " para " + cliente.getNome() +
                           " concluida com sucesso");*/
    }

    private static void excluirCliente() {
        System.out.println("Insira informacoes do cliente a ser excluido:");
        /*String cpf_cnpj = promptString("CPF/CNPJ");
        int retorno =banco.removeCliente(cpf_cnpj);
        if(retorno==0){
            System.out.println("Cliente excluido com sucesso");
        } else if(retorno==-1){
            System.out.println("ERRO: Cliente não cadastrado no banco");
        }else if(retorno==-2){
            System.out.println("ERRO: O cliente não pode ser deletado, pois ainda possui contas cadastradas");
        }*/
    }

    private static void excluirConta() {
        int numConta = promptInt("Numero da conta");
        /*boolean retorno = banco.removeConta(numConta);
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

    private static void transferencia() {
        System.out.println("Insira informacoes para a transferencia:");
        /*int numContaOrigem = promptInt("Numero da conta de origem");
        int numContaDestino = promptInt("Numero da conta de destino");
        double valor = promptDouble("Valor");
        String confirmacao = promptString("Confirmar transferencia de R$" + valor +
                                          " da conta " + numContaOrigem +
         	                              " para a conta " + numContaDestino + " ? [s/n]");
        if (confirmacao.equals("s")) {
           int status = banco.transferencia(numContaOrigem, numContaDestino , valor);
           if (status==-1) {
               System.out.println("Numero de conta origem é invalido");
           } else if(status==-2){
               System.out.println("Numero de conta destino é invalido");
           } else if(status==-3){
               System.out.println("Saldo insuficiente");
           } else {
               System.out.println("Transferencia realizada com sucesso");
           }
        } else {
            System.out.println("Transferencia cancelada pelo usuario");
        }*/
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
        /*int numConta = promptInt("Numero da conta");
        GregorianCalendar dataInicial = promptCalendar("Data inicial (dd/mm/aaaa ou default = inicio do mes atual)");
        GregorianCalendar dataFinal = promptCalendar("Data final (dd/mm/aaaa ou default = hoje)");
        ArrayList<banco.Movimentacao> extrato = new ArrayList<banco.Movimentacao>();
        if (dataInicial == null) {
            extrato = banco.extrato(numConta);
        } else if (dataFinal == null) {
            extrato = banco.extrato(numConta, dataInicial);
        } else {
            extrato = banco.extrato(numConta, dataInicial, dataFinal);
        }
        if (extrato == null) {
            System.out.println("ERRO: Nenhuma conta com numero " + numConta + " encontrada");
        } else {
            System.out.println(extrato);
        }*/
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

        restaurar();

        System.out.println("\nEntre o comando 'ajuda' para obter uma lista dos comandos disponiveis\n");
    }

    public static void main(String[] args) {
        iniciarLinhaDeComando();
        boolean status = true;
        while (status) {
            status = executarLinhaDeComando();
        }
    }

    private static Scanner scan;
    private static Path databaseFile;
}
