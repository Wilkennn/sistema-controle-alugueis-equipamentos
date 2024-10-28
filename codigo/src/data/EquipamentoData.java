
package src.data;

import src.entities.TipoEquipamento;

public class EquipamentoData {

    private int codigo;
    protected static int proximoCodigo = 1;
    protected double valorPorDia;
    protected String nome;
    protected TipoEquipamento tipo;

    /**
     * Construtor da classe EquipamentoData.
     *
     * @param codigo    O código do equipamento.
     * @param nome A descrição do equipamento.
     * @param tipo      O tipo de equipamento.
     */
    public EquipamentoData(String nome, TipoEquipamento tipo, double valorPorDia) {
        this.codigo = proximoCodigo;
        proximoCodigo++;
        this.nome = nome;
        this.valorPorDia = valorPorDia;
        this.tipo = tipo;
    }

    public EquipamentoData(int codigo, String nome, TipoEquipamento tipo, double valorPorDia) {
        this.codigo = codigo; 
        this.nome = nome;
        this.tipo = tipo;
        this.valorPorDia = valorPorDia;
    }

    /**
     * Obtém o código do equipamento.
     *
     * @return O código do equipamento.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código do equipamento.
     *
     * @param codigo O novo código do equipamento.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém a descrição do equipamento.
     *
     * @return A descrição do equipamento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define a descrição do equipamento.
     *
     * @param nome A nova descrição do equipamento.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o tipo de equipamento.
     *
     * @return O tipo de equipamento.
     */
    public TipoEquipamento getTipo() {
        return tipo;
    }

    /**
     * Define o tipo de equipamento.
     *
     * @param tipo O novo tipo de equipamento.
     */
    public void setTipo(TipoEquipamento tipo) {
        this.tipo = tipo;
    }

        /**
     * Obtém o valor do aluguel por dia.
     *
     * @return O valor do aluguel por dia.
     */
    public double getValorPorDia() {
        return valorPorDia;
    }

    /**
     * Define o valor do aluguel por dia.
     *
     * @param valorPorDia O novo valor do aluguel por dia.
     */
    public void setValorPorDia(double valorPorDia) {
        this.valorPorDia = valorPorDia;
    }
}
