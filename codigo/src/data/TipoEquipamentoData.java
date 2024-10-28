package src.data;

public class TipoEquipamentoData {

    private int codigo;
    protected static int proximoCodigo = 1;
    protected String nome;
    protected String descricao;

    /**
     * Construtor da classe TipoEquipamentoData.
     *
     * @param codigo    O código do tipo de equipamento.
     * @param nome      O nome do tipo de equipamento.
     * @param descricao A descrição do tipo de equipamento.
     */
    public TipoEquipamentoData(String nome, String descricao) {
        this.codigo = proximoCodigo;
        proximoCodigo++;
        this.nome = nome;
        this.descricao = descricao;
    }

    public TipoEquipamentoData(int codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Obtém o código do tipo de equipamento.
     *
     * @return O código do tipo de equipamento.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o código do tipo de equipamento.
     *
     * @param codigo O novo código do tipo de equipamento.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o nome do tipo de equipamento.
     *
     * @return O nome do tipo de equipamento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do tipo de equipamento.
     *
     * @param nome O novo nome do tipo de equipamento.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do tipo de equipamento.
     *
     * @return A descrição do tipo de equipamento.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do tipo de equipamento.
     *
     * @param descricao A nova descrição do tipo de equipamento.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}