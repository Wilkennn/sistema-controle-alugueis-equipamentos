package src;

import src.entities.Aluguel;
import src.entities.Cliente;
import src.entities.Equipamento;
import src.entities.TipoEquipamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        Scanner scr = new Scanner(System.in);

        System.out.println("BEM-VINDO À BETONADO ALUGUEL DE EQUIPAMENTOS LTDA.");

        do {
            
            System.out.println("\n#1 - Listar equipamentos disponíveis");
            System.out.println("#2 - Registrar novo aluguel");
            System.out.println("#3 - Mostrar informações de todos os aluguéis");
            System.out.println("#4 - Mostrar informações de alugueis de um cliente");
            System.out.println("#5 - Mostrar faturamento mensal");
            System.out.println("#6 - Sair");

            System.out.print("\nDigite o número da opção desejada: ");

            int opcao = scr.nextInt();

            scr.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {

                // ------------------------------------------------------ //

                case 1:
            
                    // Mostrar equipamentos disponíveis
                    System.out.println("\n> Equipamentos disponíveis:");

                    List<Equipamento> equipamentos = Equipamento.readEquipamentos();

                    for (Equipamento equipamento : equipamentos) {


                        TipoEquipamento tipoEquipamento =  equipamento.getTipo();
                        String nomeTipoEquipamento = tipoEquipamento.getNome();

                        System.out.println("Código: " + equipamento.getCodigo() + " | NOME: " + equipamento.getNome() 
                        + " | VALOR DIARIA: " + equipamento.getValorPorDia() + " | TIPO: " + nomeTipoEquipamento);
                    }
                    break;

                // ------------------------------------------------------ //

                case 2:

                    // Pegar nome do cliente
                    System.out.print("\nDigite o ID do cliente: ");
                    String clienteId = scr.nextLine();

                    // ------------------------------------------------------ //

                    // Procurar pelo equipamento no banco de dados
                   int numEquipamentos = 0;
                   List<Equipamento> equipamentosToAluguel = new ArrayList<>();
                   while(numEquipamentos == 5 ){
                        System.out.print("Digite o código do equipamento: ");
                        String equipamentoId = scr.nextLine();

                        Equipamento equipamento = Equipamento.findEquipamentoById(Integer.parseInt(equipamentoId));

                        // Validar equipamento
                        if (equipamento == null) {
                            System.out.println("x Equipamento não encontrado. Por favor, tente novamente.");
                            break;
                        }else{
                            equipamentosToAluguel.add(equipamento);
                            numEquipamentos++;
                        }
                   }

                    // ------------------------------------------------------ //
                    
                    // Data de início e término do aluguel
                    System.out.print("Digite a data de início do aluguel (AAAA/MM/DD): ");
                    String dataInicio = scr.nextLine();

                    System.out.print("Digite a data de término do aluguel (AAAA/MM/DD): ");
                    String dataTermino = scr.nextLine();

                    // Validar datas
                    if(LocalDate.parse(dataInicio, df) == null || LocalDate.parse(dataTermino, df) == null) {

                        System.out.println("Data inválida. Por favor, tente novamente.");
                        break;
                    }
                    else if(LocalDate.parse(dataInicio, df).isAfter(LocalDate.parse(dataTermino, df))) {

                        System.out.println("Data de início maior que data de término. Por favor, tente novamente.");
                        break;
                    }

                    // ------------------------------------------------------ //           
                    
                    // Registrar aluguel


                    Aluguel.registrarAlugueis(Integer.parseInt(clienteId), equipamentosToAluguel, LocalDate.parse(dataInicio, df), LocalDate.parse(dataTermino, df));

                    System.out.println("> Aluguel registrado com sucesso!");
                    break;

                case 3:

                    // Mostrar informações de todos os aluguéis
                    System.out.println("\n> Informações de todos os aluguéis:");

                    List<Aluguel> aluguels = Aluguel.readAlugueis();

                    if(aluguels.size() == 0) {
                        System.out.println("x Nenhum aluguel registrado.");
                        break;
                    }

                    for (Aluguel aluguel : aluguels) {
                        System.out.println("[i] Cliente: " +aluguel.getCliente().getNome());

                        for (Equipamento equipamento : aluguel.getEquipamento()) {
                            System.out.println("[i] Equipamento: " + equipamento.getNome() + " (" + equipamento.getCodigo() + ")");
                        }

                        System.out.println("[i] Período: " + aluguel.getDataInicio() + " - " + aluguel.getDataInicio() +"");
                        System.out.println("[i] Valor diário: R$" + Aluguel.calcularValorTotal() / (Aluguel.calcularDiferencaData(aluguel.getDataInicio(), aluguel.getDataTermino())).getDays());
                        System.out.println("[i] Valor total: R$" + Aluguel.calcularValorTotal() );
                    }
                    break;

                case 4:

                    // Mostrar informações de alugueis de um cliente
                    System.out.print("\nDigite o ID do cliente: ");
                    String clienteIdToSearch = scr.nextLine();

                    Cliente clienteFinded = Cliente.findClienteById(Integer.parseInt(clienteIdToSearch));

                    // Validar cliente
                    if (clienteFinded == null) {

                        System.out.println("x Cliente não encontrado. Por favor, tente novamente.");
                        break;
                    }

                    scr.nextLine(); // Limpa o buffer do teclado

                    for (Aluguel aluguel : clienteFinded.getConsultaHistorico()) {
                        System.out.println("[i] Cliente: " +aluguel.getCliente().getNome());

                        for (Equipamento equipamento : aluguel.getEquipamento()) {
                            System.out.println("[i] Equipamento: " + equipamento.getNome() + " (" + equipamento.getCodigo() + ")");
                        }

                        System.out.println("[i] Período: " + aluguel.getDataInicio() + " - " + aluguel.getDataInicio() +"");
                        System.out.println("[i] Valor diário: R$" + Aluguel.calcularValorTotal() / (Aluguel.calcularDiferencaData(aluguel.getDataInicio(), aluguel.getDataTermino())).getDays());
                        System.out.println("[i] Valor total: R$" + Aluguel.calcularValorTotal() );
                    }
                    
                    break;

                case 5:

                    // Mostrar faturamento mensal no formato R$XXXX,XX
                    System.out.println("\n> Faturamento mensal: R$" + String.format("%.2f", Aluguel.calcularFaturamentoMensal()));
                    break;

                case 6:

                    System.out.println("> Saindo do programa. Obrigado!");

                    scr.close(); // Fecha o scanner

                    // Fechar o programa
                    System.exit(0);

                default: System.out.println("x Opção inválida. Por favor, escolha uma opção válida.");
            }
        } 
        while (true);
    }
}
