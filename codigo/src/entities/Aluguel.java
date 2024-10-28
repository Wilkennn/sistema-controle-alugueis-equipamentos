package src.entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import src.data.AluguelData;

/**
 * Classe que representa um registro de aluguel de equipamento.
 */
public class Aluguel extends AluguelData {

    /**
     * Construtor da classe Aluguel.
     * 
     * @param cliente          Cliente que realizou o aluguel.
     * @param listEquipamentos Equipamento alugado.
     * @param dataInicio       Data de início do aluguel.
     * @param dataTermino      Data de término do aluguel.
     */
    public Aluguel(Cliente cliente, List<Equipamento> listEquipamentos, LocalDate dataInicio, LocalDate dataTermino) {
        super(cliente, listEquipamentos, dataInicio, dataTermino);
    }

    public Aluguel(int codigo, Cliente cliente, List<Equipamento> equipamento, LocalDate dataInicio,
            LocalDate dataTermino) {
        super(codigo, cliente, equipamento, dataInicio, dataTermino);
    }

    /**
     * Registra um novo aluguel.
     */
    public static void registrarAlugueis(int clienteId, List<Equipamento> equipamentos, LocalDate dataIni, LocalDate dataFim ) {
        try {
            Cliente cliente = Cliente.findClienteById(clienteId);

            if (cliente == null) {
               throw new Exception("Cliente não encontrado");
            }

            Aluguel newAluguel = new Aluguel(cliente, equipamentos, dataIni, dataFim);

            List<Aluguel> listAluguel = readAlugueis();

            listAluguel.add(newAluguel);

            writeAluguel(listAluguel);

            System.out.println("Aluguel registrado com sucesso!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Aluguel> readAlugueis() {
        List<Aluguel> lAluguel = new ArrayList<>();

        try (Scanner readTextFile = new Scanner(new FileInputStream("resources\\Aluguel.txt"), "UTF-8")) {
            readTextFile.useLocale(Locale.US);

            int id = 0;
            int clienteId = 0;
            String equipamentos = null;
            String dataIni = null;
            String dataFim = null;

            while (readTextFile.hasNextLine()) {
                String linha = readTextFile.nextLine().trim();

                if (!linha.isEmpty()) {
                    if (id == 0) {
                        id = Integer.parseInt(linha);
                    } else if (clienteId == 0) {
                        clienteId = Integer.parseInt(linha);
                    } else if (equipamentos == null) {
                        equipamentos = linha;
                    } else if (dataIni == null) {
                        dataIni = linha;
                    } else if (dataFim == null) {
                        dataFim = linha;

                        Cliente cliente = Cliente.findClienteById(clienteId);

                        String[] equipamentosIds = equipamentos.split(",");

                        List<Equipamento> listEquipamento = new ArrayList<>();
                        for (String equipamentoId : equipamentosIds) {
                            Equipamento equipamento = Equipamento
                                    .findEquipamentoById(Integer.parseInt(equipamentoId.trim()));
                            listEquipamento.add(equipamento);
                        }

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate dataInicio = LocalDate.parse(dataIni, formatter);
                        LocalDate dataFinal = LocalDate.parse(dataFim, formatter);

                        Aluguel aluguel = new Aluguel(id, cliente, listEquipamento, dataInicio, dataFinal);
                        lAluguel.add(aluguel);

                        // Limpar variáveis para o próximo conjunto de dados
                        id = 0;
                        clienteId = 0;
                        equipamentos = null;
                        dataIni = null;
                        dataFim = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lAluguel;
    }

    public static void writeAluguel(List<Aluguel> alugueis) {
        try {
            File file = new File("resources\\Aluguel.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Aluguel aluguel : alugueis) {
                bufferedWriter.write(String.valueOf(aluguel.getId()));
                bufferedWriter.newLine();

                bufferedWriter.write(String.valueOf(aluguel.getCliente().getCodigo()));
                bufferedWriter.newLine();

                /**
                 * Lista de Equipamentos
                 */
                List<Equipamento> listEquipamento = aluguel.getEquipamento();
                StringBuilder equipamentoIds = new StringBuilder();

                for (Equipamento equipamento : listEquipamento) {
                    equipamentoIds.append(equipamento.getCodigo()).append(", ");
                }

                if (equipamentoIds.length() > 0) {
                    equipamentoIds.setLength(equipamentoIds.length() - 2); // Remove a última vírgula e espaço
                }

                bufferedWriter.write(equipamentoIds.toString());
                bufferedWriter.newLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                /**
                 * Data Início
                 */
                String dataIniFormatada = aluguel.getDataInicio().format(formatter);
                bufferedWriter.write(dataIniFormatada);
                bufferedWriter.newLine();

                /**
                 * Data Fim
                 */
                String dataFimFormatada = aluguel.getDataTermino().format(formatter);
                bufferedWriter.write(dataFimFormatada);
                bufferedWriter.newLine();

                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Aluguel findAluguelById(int id) {

        List<Aluguel> alugueis = readAlugueis();

        try {

            for (Aluguel aluguel : alugueis) {
                if (aluguel.getId() == id) {
                    return aluguel;
                }
            }

        } catch (Exception e) {
            System.out.println("Aluguel com ID " + id + " não encontrado.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Calcula o valor total dos aluguéis.
     * 
     * @return
     * 
     * @return O valor total dos aluguéis.
     */
    public static double calcularValorTotal() {
        try {
            LocalDate dataInicio = getDataInicio();
            LocalDate dataTermino = getDataTermino();

            Period duracao = calcularDiferencaData(dataInicio, dataTermino);
            int duracaoDias = duracao.getDays();

            List<Equipamento> equipamentos = getEquipamento();

            double valorTotal = 0;
            for (Equipamento equipamento : equipamentos) {
                double custoEquipamento = equipamento.getValorPorDia() * duracaoDias;
                valorTotal += custoEquipamento;
            }

            return valorTotal;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Period calcularDiferencaData(LocalDate data1, LocalDate data2) {
        return Period.between(data1, data2);
    }

    public static double calcularFaturamentoMensal() {

        double faturamento = 0.0;

        List<Aluguel> alugueis = readAlugueis();

        for (Aluguel aluguel : alugueis) {

            // Validar se o aluguel não tem data inicial ou final no mês atual
            if (aluguel.getDataInicio().getMonthValue() != LocalDate.now().getMonthValue() && aluguel.getDataTermino().getMonthValue() != LocalDate.now().getMonthValue()) { continue; }

            int days = calcularDiferencaData(aluguel.getDataInicio(), aluguel.getDataTermino()).getDays();           
            
            // Adicionar valor total do aluguel ao faturamento
            faturamento += days * aluguel.calcularValorTotal();
        }
        
        return faturamento;
    }

}
