package Controllerss;

import Controllers.DAO.AlunoDAO;
import Models.Aluno;
import Models.Funcionario;
import Models.Usuario;
import Utils.TipoCargo;
import Utils.TipoExercicio;
import View.nicolas.VerificarImcTelaJS;
import View.nicolas.VerificarTreinoTelaJS;
import View.rossini.AlunoTelaG;

import java.util.ArrayList;
import java.util.List;

public class AlunoController {

    private AlunoDAO alunoDAO;
    public VerificarTreinoTelaJS treinoTela;
    public VerificarImcTelaJS imcTela;

    private static AlunoController ourInstance = new AlunoController();

    /**
     * Método para resgatar a instância da classe, que foi instanciada no
     * atributo ourInstance
     *
     * @return AlunoController - Instância da classe
     */
    public static AlunoController getInstance() {
        return ourInstance;
    }

    /**
     * Método construtor que instancia um novo controlador de alunos e inicia o
     * arraylist de alunos.
     */
    public AlunoController() {
        alunoDAO = AlunoDAO.getInstance();
        imcTela = new VerificarImcTelaJS();
        treinoTela = new VerificarTreinoTelaJS();
        
    }

    /**
     * Método para retorno do array de alunos cadastrados na academia.
     *
     * @return ArrayList<Aluno> - array de alunos cadastrados
     */
    public List<Aluno> getAlunos() {
        return alunoDAO.getList();
    }

    /**
     * Método que adiciona o cadastro de um novo aluno a partir das informações
     * definidas no parâmetro.
     *
     * @param codigo - código do aluno
     * @param nome - nome do aluno
     * @param cpf - cpf do aluno
     * @param tipoCargo - tipo de usuário(aluno)
     * @param peso - peso do aluno
     * @param altura - altura do aluno
     * @param idade - idade do aluno
     * @throws java.lang.Exception
     */
    public void adicionaAluno(Long codigo, String nome, String cpf, TipoCargo tipoCargo, float peso, float altura, int idade) throws Exception {

        if (!verificaParametros(codigo, nome, cpf, tipoCargo, peso, altura, idade)) {
            throw new Exception("Erro ao cadastrar aluno ! Parâmetros informados incorretamente.");
        }

        if (cpfJaCadastrado(cpf)) {
            throw new Exception("Erro ao cadastrar aluno ! Já existe um usuário com esse CPF cadastrado.");
        }

        alunoDAO.put(new Aluno(codigo, nome, cpf, tipoCargo, peso, altura, idade));
    }

    private boolean cpfJaCadastrado(String cpf) {

        for (Aluno aluno : alunoDAO.getList()) {
            if (aluno.getCpf().equals(cpf)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Método que define o array de alunos para inicialização do sistema
     * Utilizado na classe do controlador de registro
     *
     * @param alunos - array de alunos
     */
    public void setAlunos(ArrayList<Aluno> alunos) {
        alunos.stream().forEach(funcionario -> {
            try {
                alunoDAO.put(funcionario);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Método que verifica se os parâmetros informados pelo usuário são válidos.
     *
     * @param codigo - código do aluno
     * @param nome - nome do aluno
     * @param cpf - cpf do aluno
     * @param TipoCargo - tipo de usuário(aluno)
     * @param peso - peso do aluno
     * @param altura - altura do aluno
     * @param idade - idade do aluno
     * @return boolean - retorna verdadeiro se os parâmetros forem válidos
     */
    private boolean verificaParametros(Long codigo, String nome, String cpf, TipoCargo TipoCargo, float peso, float altura, int idade) {

        boolean valido = false;
        if (verificaNumerosValidos(codigo, peso, altura, idade)) {
            if (verificaStringsValidas(nome, cpf)) {
                if (TipoCargo != null) {
                    valido = true;
                }
            }
        }

        return valido;
    }

    /**
     * Método que verifica se os parâmetros informados pelo usuário são válidos.
     * Utilizado no método verificaParametros
     *
     * @param codigo - código do aluno
     * @param peso - peso do aluno
     * @param altura - altura do aluno
     * @param idade - idade do aluno
     * @return boolean - retorna verdadeiro se os parâmetros forem válidos
     */
    private boolean verificaNumerosValidos(Long codigo, float peso, float altura, int idade) {
        return ((codigo > 0) || (peso > 0) || (altura > 0) || (idade > 0));
    }

    /**
     * Método que verifica se os parâmetros informados pelo usuário são válidos.
     * Utilizado no método verificaParametros
     *
     * @param nome - nome do aluno
     * @param cpf - cpf do aluno
     * @return boolean - retorna verdadeiro se os parâmetros forem válidos
     */
    private boolean verificaStringsValidas(String nome, String cpf) {
        return !((nome.isEmpty()) || (cpf.isEmpty()));
    }

    /**
     * Método que deleta o cadastro do aluno a partir de seu código.
     *
     * @param codigo - código do aluno
     * @throws java.lang.Exception
     */
    public void deletaAluno(Long codigo) throws Exception {

        if (codigo > 0) {
            alunoDAO.remove(codigo);
        } else {
            throw new Exception();
        }

    }

    /**
     * Método que altera o cadastro do aluno a partir de seu código. É possivel
     * alterar apenas a altura,idade e peso.
     *
     * @param codigo - código do aluno
     * @param peso - peso do aluno
     * @param altura - altura do aluno
     * @param idade - idade do aluno
     * @throws java.lang.Exception
     */
    public void alteraCadastro(Long codigo, float peso, float altura, int idade) throws Exception {

        Aluno a = (Aluno) this.recuperaAluno(codigo);

        if (peso > 0 && altura > 0 && idade > 0) {
            a.setAltura(altura);
            a.setIdade(idade);
            a.setPeso(peso);
        } else {
            throw new Exception();
        }

    }

    /**
     * Método que verifica se o código aleatório gerado já foi utilizado no
     * array de alunos
     *
     * @param codigo - código gerado
     * @return boolean - caso esteja disponivel retornará true(verdadeiro)
     */
    public boolean codigoEstaDisponivel(Long codigo) throws Exception {
        return !alunoDAO.cacheAlunos.containsKey(codigo);
    }

    /**
     * Método que retorna o aluno a partir de seu código.
     *
     * @param codigo - código do aluno
     * @return Usuario - objeto aluno(herda a classe Usuário)
     */
    public Usuario recuperaAluno(Long codigo) {

        try {
            if (codigo > 0) {
                return alunoDAO.get(codigo);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public double calculaIMC(Aluno aluno) {

        double imc;
        double alturaAoQuadrado = Math.pow(aluno.getAltura(), 2);

        imc = Math.round((aluno.getPeso() / alturaAoQuadrado)*1000)/1000;

        return imc;
    }

}
