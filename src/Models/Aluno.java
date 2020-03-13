package Models;

import Utils.TipoCargo;
import Utils.TipoExercicio;

import java.util.ArrayList;

public class Aluno extends Usuario implements java.io.Serializable {

    private int idade;
    private float peso;
    private float altura;
    private ArrayList<TipoExercicio> treino;

    /**
     * Método construtor que instancia um novo aluno
     *
     * @param codigo - código do usuário
     * @param nome - nome do usuário
     * @param cpf - cpf do usuário
     * @param tipoCargo - tipo de usuário(aluno)
     * @param peso - peso do aluno
     * @param altura - altura do aluno
     * @param idade - idade do aluno
     */
    public Aluno(Long codigo, String nome, String cpf, TipoCargo tipoCargo, float peso, float altura, int idade) {
        super(codigo, nome, cpf, tipoCargo);
        this.peso = peso;
        this.altura = altura;
        this.idade = idade;
        this.treino = new ArrayList<>();
    }

    /**
     * Método para retorno do peso do aluno
     *
     * @return float - peso do aluno
     */
    public float getPeso() {
        return this.peso;
    }

    /**
     * Método para definir o peso do aluno
     *
     * @param peso - peso do aluno
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /**
     * Método para retorno da altura do aluno
     *
     * @return float - altura do aluno
     */
    public float getAltura() {
        return this.altura;
    }

    /**
     * Método para definir a altura do aluno
     *
     * @param altura - altura do aluno
     */
    public void setAltura(float altura) {
        this.altura = altura;
    }

    /**
     * Método para retorno da idade do aluno
     *
     * @return int - idade do aluno
     */
    public int getIdade() {
        return this.idade;
    }

    /**
     * Método para definir a idade do aluno
     *
     * @param idade - idade do aluno
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * Método para retorno do treino do aluno
     *
     * @return ArrayList<String> - treino do aluno
     */
    public ArrayList<TipoExercicio> getTreino() {
        return this.treino;
    }

}
