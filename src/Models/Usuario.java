package Models;


import Utils.TipoCargo;

public abstract class Usuario implements java.io.Serializable {

    private Long codigo;
    private String nome;
    private String cpf;
    private TipoCargo tipoCargo;

    /**
     * Método construtor que instancia um novo Usuario
     *
     * @param codigo - código do usuário
     * @param nome - nome do usuário
     * @param cpf - cpf do usuário
     * @param tipoCargo - tipo de usuário
     */
    public Usuario(Long codigo, String nome, String cpf, TipoCargo tipoCargo) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoCargo = tipoCargo;
    }

    /**
     * Método para retorno do código do usuário
     *
     * @return int - código do usuário
     */
    public Long getCodigo() {
        return this.codigo;
    }

    /**
     * Método para retorno do nome do usuário
     *
     * @return String - nome do usuário
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Método para definir o nome do usuário
     *
     * @param nome - nome do usuário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para retorno do cpf do usuário
     *
     * @return String - cpf do usuário
     */
    public String getCpf() {
        return this.cpf;
    }

    /**
     * Método para definir o cpf do usuário
     *
     * @param cpf - cpf do usuário
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Método para retorno do tipo de usuário
     *
     * @return TipoCargo - tipoCargo do usuário
     */
    public TipoCargo getTipoCargo() {
        return this.tipoCargo;
    }

}
