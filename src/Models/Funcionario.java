package Models;

import Utils.TipoCargo;
import Utils.TipoCargo;

public class Funcionario extends Usuario implements java.io.Serializable {

    private float salario;

    /**
     * Método construtor que instancia um novo funcionário
     *
     * @param codigo - código do usuário
     * @param nome - nome do usuário
     * @param cpf - cpf do usuário
     * @param tipoCargo - tipo de cargo de usuário(funcionário)
     * @param salario - salário do funcionário
     */
    public Funcionario(Long codigo, String nome, String cpf, TipoCargo tipoCargo, float salario) {
        super(codigo, nome, cpf, tipoCargo);
        this.salario = salario;
    }
    /**
     * Método para retorno do cargo do funcionário
     *
     * @return String - cargo do funcionário
     */
    public float getSalario() {
        return this.salario;
    }

    /**
     * Método para definir o salário do funcionário
     *
     * @param salario - salário do funcionário
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * Método para retornar as informações do funcionário em uma string única
     *
     * @return String - informações do funcionário
     */
    @Override
    public String toString() {
        String concatenada = "Código: " + getCodigo() + "\n" + "Nome: " + getNome() + "\n" + "CPF: " + getCpf() + "\n" + "Cargo: " + getTipoCargo() + "\n" + "Salário: " + salario + " reais" + "\n";
        return concatenada;
    }

}
