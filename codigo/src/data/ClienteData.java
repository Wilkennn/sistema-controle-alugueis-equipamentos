package src.data;

import java.util.List;

import src.entities.Aluguel;


public class ClienteData {

    private int codigo;
    private static int proximoCodigo = 1;
    protected String cpf;
    protected String nome;
    protected List<Aluguel> consultaHistorico;

    /**
     * Construtor da classe ClienteData.
     *
     * @param codigo            O codigo do cliente.
     * @param cpf               O CPF do cliente.
     * @param nome              O nome do cliente.
     * @param consultaHistorico O histórico de aluguéis do cliente.
     */
    public ClienteData(String cpf, String nome, List<Aluguel> consultaHistorico) {
        this.codigo = proximoCodigo;
        proximoCodigo++;
        this.cpf = cpf;
        this.nome = nome;
        this.consultaHistorico = consultaHistorico;
    }

    public ClienteData(int codigo, String cpf, String nome, List<Aluguel> consultaHistorico) {
        this.codigo = proximoCodigo;
        this.cpf = cpf;
        this.nome = nome;
        this.consultaHistorico = consultaHistorico;
    }

    /**
     * Obtém o codigo do cliente.
     *
     * @return O codigo do cliente.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Define o codigo do cliente.
     *
     * @param codigo O novo codigo do cliente.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o CPF do cliente.
     *
     * @return O CPF do cliente.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpf O novo CPF do cliente.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o histórico de aluguéis do cliente.
     *
     * @return O histórico de aluguéis do cliente.
     */
    public List<Aluguel> getConsultaHistorico() {
        return consultaHistorico;
    }

    /**
     * Define o histórico de aluguéis do cliente.
     *
     * @param historicoAlugueis O novo histórico de aluguéis do cliente.
     */
    public void setConsultaHistorico(List<Aluguel> consultaHistorico) {
        this.consultaHistorico = consultaHistorico;
    }
}
