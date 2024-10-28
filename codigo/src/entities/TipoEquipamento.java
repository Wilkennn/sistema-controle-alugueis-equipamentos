package src.entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.data.TipoEquipamentoData;

/**
 * Classe que representa um tipo de equipamento disponível para aluguel.
 */
public class TipoEquipamento extends TipoEquipamentoData {

    /**
     * Construtor da classe TipoEquipamento.
     * 
     * @param nome      Nome do tipo de equipamento.
     * @param descricao Descrição do tipo de equipamento.
     */
    public TipoEquipamento(String nome, String descricao) {
        super(nome, descricao);
    }

    /**
     * Construtor da classe TipoEquipamento.
     * 
     * @param codigo    Código único do tipo de equipamento.
     * @param nome      Nome do tipo de equipamento.
     * @param descricao Descrição do tipo de equipamento.
     */
    public TipoEquipamento(int codigo, String nome, String descricao) {
        super(codigo, nome, descricao);
    }

    /**
     * Lê os tipos de equipamento a partir de um arquivo.
     * 
     * @return Uma lista de objetos TipoEquipamento lidos do arquivo.
     */
    public static List<TipoEquipamento> readTipoEquipamentos() {
        List<TipoEquipamento> lTipoEquipamentos = new ArrayList<>();

        try {
            File file = new File("resources\\TipoEquipamento.txt");
            FileInputStream fileStream = new FileInputStream(file);

            Scanner readTextFile = new Scanner(fileStream, "UTF-8");

            int id = 0;
            String nome = null;
            String descricao = null;

            while (readTextFile.hasNextLine()) {
                String linha = readTextFile.nextLine().trim();

                if (!linha.isEmpty()) {
                    if (linha.matches("\\d+")) {
                        id = Integer.parseInt(linha);
                    } else if (nome == null) {
                        nome = linha;
                    } else if (descricao == null) {
                        descricao = linha;
                        if (id != 0 && nome != null && descricao != null) {
                            TipoEquipamento tipoEquipamento = new TipoEquipamento(id, nome, descricao);
                            lTipoEquipamentos.add(tipoEquipamento);
                            id = 0;
                            nome = null;
                            descricao = null;
                        }
                    }
                }
            }

            readTextFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lTipoEquipamentos;
    }

    /**
     * Escreve os tipos de equipamento em um arquivo.
     * 
     * @param tipoEquipamentos Lista de objetos TipoEquipamento a serem escritos no arquivo.
     */
    public static void writeTipoEquipamentos(List<TipoEquipamento> tipoEquipamentos) {
        try {
            File file = new File("resources\\TipoEquipamento.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (TipoEquipamento tipoEquipamento : tipoEquipamentos) {
                bufferedWriter.write(String.valueOf(tipoEquipamento.getCodigo()));
                bufferedWriter.newLine();
                bufferedWriter.write(tipoEquipamento.getNome());
                bufferedWriter.newLine();
                bufferedWriter.write(tipoEquipamento.getDescricao());
                bufferedWriter.newLine();

                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca um TipoEquipamento pelo seu ID.
     * 
     * @param id O ID do TipoEquipamento a ser buscado.
     * @return O TipoEquipamento encontrado ou null se não for encontrado.
     */
    public static TipoEquipamento findTipoEquipamentoById(int id) {

        List<TipoEquipamento> tipoEquipamentos = readTipoEquipamentos();

        try {

            for (TipoEquipamento tipoEquipamento : tipoEquipamentos) {
                if (tipoEquipamento.getCodigo() == id) {
                    return tipoEquipamento;
                }
            }
        } catch (Exception e) {
            System.out.println("TipoEquipamento com ID " + id + " não encontrado.");
            e.printStackTrace();
        }

        return null;
    }
}
