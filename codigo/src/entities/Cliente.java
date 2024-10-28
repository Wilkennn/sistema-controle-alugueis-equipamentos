package src.entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import src.data.ClienteData;

/**
 * Classe que representa um cliente que realiza aluguéis de equipamentos.
 */
public class Cliente extends ClienteData {

    /**
     * Construtor da classe Cliente.
     * 
     * @param codigo            Identificador único do cliente.
     * @param cpf               CPF do cliente.
     * @param nome              Nome do cliente.
     * @param historicoAlugueis Lista de histórico de aluguéis do cliente.
     */

    public Cliente(String cpf, String nome, List<Aluguel> historicoAlugueis) {
        super(cpf, nome, historicoAlugueis);
    }

    public Cliente(int codigo, String cpf, String nome, List<Aluguel> historicoAlugueis) {
        super(codigo, cpf, nome, historicoAlugueis);
    }

    public static List<Cliente> readClientes() {
        List<Cliente> lClientes = new ArrayList<>();

        try {
            File file = new File("resources\\Clientes.txt");
            FileInputStream fileStream = new FileInputStream(file);

            Scanner readTextFile = new Scanner(fileStream, "UTF-8");
            readTextFile.useLocale(Locale.US); // Define o formato com ponto como separador decimal

            int id = 0;
            String cpf = null;
            String nome = null;
            String historico = null;

            while (readTextFile.hasNextLine()) {
                String linha = readTextFile.nextLine().trim();

                if (!linha.isEmpty()) {
                    if (linha.matches("\\d+") && id == 0) {
                        id = Integer.parseInt(linha);
                    } else if (linha.matches("\\d+") && cpf == null) {
                        cpf = linha;
                    } else if (nome == null) {
                        nome = linha;
                    } else if (historico == null) {
                        historico = linha;

                        if (id != 0 && cpf != null && nome != null && historico != null) {
                            String[] alugueisIds = historico.split(",");

                            List<Aluguel> historicoAluguel = new ArrayList<>();
                            for (String aluguelId : alugueisIds) {
                                Aluguel aluguel = Aluguel.findAluguelById(Integer.parseInt(aluguelId.trim()));
                                historicoAluguel.add(aluguel);
                            }

                            Cliente cliente = new Cliente(id, cpf, nome, historicoAluguel);

                            lClientes.add(cliente);

                            id = 0;
                            nome = null;
                            cpf = null;
                            historico = null;
                        }
                    }
                }
            }

            readTextFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lClientes;
    }

    public static void writeCliente(List<Cliente> clientes) {
        try {
            File file = new File("resources\\Clientes.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Cliente cliente : clientes) {

                bufferedWriter.write(String.valueOf(cliente.getCodigo()));
                bufferedWriter.newLine();

                bufferedWriter.write(cliente.getCpf());
                bufferedWriter.newLine();

                bufferedWriter.write(cliente.getNome());
                bufferedWriter.newLine();

                List<Aluguel> listAluguel = cliente.getConsultaHistorico();
                StringBuilder aluguelIds = new StringBuilder();
                
                for (Aluguel aluguel : listAluguel) {
                    aluguelIds.append(aluguel.getId()).append(", ");
                }
                
                if (aluguelIds.length() > 0) {
                    aluguelIds.setLength(aluguelIds.length() - 2); // Remove a última vírgula e espaço
                }

                bufferedWriter.write(aluguelIds.toString());
                bufferedWriter.newLine();

                bufferedWriter.newLine(); 
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cliente findClienteById(int id) {

        List<Cliente> clientes = readClientes();

        try {

            for (Cliente cliente : clientes) {
                if (cliente.getCodigo() == id) {
                    return cliente;
                }
            }

        } catch (Exception e) {
            System.out.println("Cliente com ID " + id + " não encontrado.");
            e.printStackTrace();
        }

        return null;
    }
}
