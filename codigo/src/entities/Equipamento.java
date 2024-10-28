package src.entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import src.data.EquipamentoData;

/**
 * Classe que representa um equipamento disponível para aluguel.
 */
public class Equipamento extends EquipamentoData {

    /**
     * Construtor da classe Equipamento.
     * 
     * @param codigo      Código único do equipamento.
     * @param descricao   Descrição do equipamento.
     * @param valorPorDia Valor da Diaria do Equipamento
     * @param tipo        Tipo de equipamento.
     */
    public Equipamento(String descricao, TipoEquipamento tipo, double valorPorDia) {
        super(descricao, tipo, valorPorDia);
    }

    public Equipamento(int codigo, String descricao, TipoEquipamento tipo, double valorPorDia) {
        super(codigo, descricao, tipo, valorPorDia);
    }

    public static List<Equipamento> readEquipamentos() {
        List<Equipamento> lEquipamentos = new ArrayList<>();
    
        try {
            File file = new File("resources\\Equipamento.txt");
            FileInputStream fileStream = new FileInputStream(file);
    
            Scanner readTextFile = new Scanner(fileStream, "UTF-8");
    
            int id = 0;
            String nome = null;
            double valorPorDia = 0.0;
            int tipo = 0;
    
            while (readTextFile.hasNextLine()) {
                String linha = readTextFile.nextLine().trim();
    
                if (!linha.isEmpty()) {
                    if (id == 0) {
                        id = Integer.parseInt(linha);
                    } else if (nome == null) {
                        nome = linha;
                    } else if (valorPorDia == 0.0) {
                        valorPorDia = Double.parseDouble(linha);
                    } else if (tipo == 0) {
                        tipo = Integer.parseInt(linha);
    
                        TipoEquipamento tipoEquipamento = TipoEquipamento.findTipoEquipamentoById(tipo);
    
                        Equipamento equipamento = new Equipamento(id, nome, tipoEquipamento, valorPorDia);
                        lEquipamentos.add(equipamento);
    
                        // Reinicializar as variáveis para o próximo conjunto de dados
                        id = 0;
                        nome = null;
                        valorPorDia = 0.0;
                        tipo = 0;
                    }
                }
            }
    
            readTextFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lEquipamentos;
    }

    /**
     * Método para escrever informações de equipamentos em um arquivo de texto.
     * 
     * @param equipamentos Uma lista de objetos Equipamento a serem escritos no
     *                     arquivo.
     */
    public static void writeEquipamentos(List<Equipamento> equipamentos) {
        try {
            File file = new File("resources\\Equipamento.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Equipamento equipamento : equipamentos) {

                bufferedWriter.write(String.valueOf(equipamento.getCodigo()));
                bufferedWriter.newLine();

                bufferedWriter.write(equipamento.getNome());
                bufferedWriter.newLine();

                String numeroComoString = Double.toString(equipamento.getValorPorDia());
                bufferedWriter.write(numeroComoString);
                bufferedWriter.newLine();

                bufferedWriter.write(String.valueOf(equipamento.getTipo().getCodigo()));
                bufferedWriter.newLine();

                bufferedWriter.newLine(); // Adiciona uma linha em branco para separar os equipamentos
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para encontrar um equipamento por ID em uma lista de equipamentos.
     * 
     * @param id O código do equipamento a ser procurado.
     * @return O equipamento encontrado ou null se não encontrado.
     */
    public static Equipamento findEquipamentoById(int id) {

        List<Equipamento> equipamentos = readEquipamentos();

        try {

            for (Equipamento equipamento : equipamentos) {
                if (equipamento.getCodigo() == id) {
                    return equipamento;
                }
            }

        } catch (Exception e) {
            System.out.println("Equipamento com ID " + id + " não encontrado.");
            e.printStackTrace();
        }

        return null;
    }
}
