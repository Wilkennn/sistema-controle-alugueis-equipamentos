package src.data;

import java.time.LocalDate;
import java.util.List;

import src.entities.Cliente;
import src.entities.Equipamento;

public class AluguelData {

    private int codigo;
    private static int proximoCodigo = 1;
    protected Cliente cliente;
    protected static List<Equipamento> equipamento;
    protected static LocalDate dataInicio;
    protected static LocalDate dataTermino;

    /**
     * Construtor da classe AluguelData.
     *
     * @param cliente     O cliente associado ao aluguel.
     * @param equipamento O equipamento alugado.
     * @param dataInicio  A data de início do aluguel.
     * @param dataTermino A data de término do aluguel.
     */

    public AluguelData(Cliente cliente, List<Equipamento> equipamento, LocalDate dataInicio, LocalDate dataTermino) {
        this.codigo = proximoCodigo;
        proximoCodigo++;
        this.cliente = cliente;
        this.equipamento = equipamento;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public AluguelData(int codigo, Cliente cliente, List<Equipamento> equipamento,
            LocalDate dataInicio, LocalDate dataTermino) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.equipamento = equipamento;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    /**
     * Obtém o Identificador do aluguel.
     *
     * @return O Identificador do aluguel.
     */
    public int getId() {
        return codigo;
    }

    /**
     * Obtém o cliente associado a este aluguel.
     *
     * @return O cliente associado a este aluguel.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente associado a este aluguel.
     *
     * @param cliente O novo cliente associado a este aluguel.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtém o equipamento alugado.
     *
     * @return O equipamento alugado.
     */
    public static List<Equipamento> getEquipamento() {
        return equipamento;
    }

    /**
     * Define o equipamento alugado.
     *
     * @param equipamento A nova lista de equipamentos alugados.
     */
    public void setEquipamento(List<Equipamento> equipamento) {
        AluguelData.equipamento = equipamento;
    }

    /**
     * Obtém a data de início do aluguel.
     *
     * @return A data de início do aluguel.
     */
    public static LocalDate getDataInicio() {
        return dataInicio;
    }

    /**
     * Define a data de início do aluguel.
     *
     * @param dataInicio A nova data de início do aluguel.
     */
    public void setDataInicio(LocalDate dataInicio) {
        AluguelData.dataInicio = dataInicio;
    }

    /**
     * Obtém a data de término do aluguel.
     *
     * @return A data de término do aluguel.
     */
    public static LocalDate getDataTermino() {
        return dataTermino;
    }

    /**
     * Define a data de término do aluguel.
     *
     * @param dataTermino A nova data de término do aluguel.
     */
    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

}
