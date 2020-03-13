package Controllerss;

import Controllers.DAO.AlunoDAO;
import Controllers.DAO.FuncionarioDAO;
import Models.Aluno;
import Models.Funcionario;
import Models.Usuario;
import Utils.TipoCargo;
import Utils.TipoExercicio;
import Utils.TipoTarefasFuncionario;
import View.nicolas.*;
import View.rossini.AlunoTelaG;
import View.rossini.FuncionarioTelaG;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private FuncionarioDAO funcionarioDAO;


    private static FuncionarioController ourInstance = new FuncionarioController();

    public MostrarAlunosJS mostraAlunos;
    public DefinirTreinoJS defineTreinoAluno;
    public DeletarCadastroAlunoJS deletaCadastroAluno;
    public AtualizarCadastroAlunoJS atualizaCadastroAluno;
    public CadastroAlunoJS cadastroTelaAluno;

    /**
     * Método para resgatar a instância da classe, que foi instanciada no
     * atributo ourInstance
     *
     * @return FuncionarioController - Instância da classe
     */
    public static FuncionarioController getInstance() {
        return ourInstance;
    }

    /**
     * Método construtor que instancia um novo controlador de funcionário e
     * inicia o arraylist de funcionários.
     */
    private FuncionarioController() {

        funcionarioDAO = FuncionarioDAO.getInstance();
        cadastroTelaAluno = new CadastroAlunoJS();
        deletaCadastroAluno = new DeletarCadastroAlunoJS();
        atualizaCadastroAluno = new AtualizarCadastroAlunoJS();
        defineTreinoAluno = new DefinirTreinoJS();
        mostraAlunos = new MostrarAlunosJS();
    }

    /**
     * Método que verifica se o código aleatório gerado já foi utilizado no
     * array de funcionários
     *
     * @param codigo - código gerado
     * @return boolean - caso esteja disponivel retornará true(verdadeiro)
     */
    public boolean codigoEstaDisponivel(Long codigo) {

        return !funcionarioDAO.cacheFuncionarios.containsKey(codigo);
    }

    /**
     * Método que define o array de funcionários para inicialização do sistema
     * Utilizado na classe do controlador de registro
     *
     * @param funcionarios - array de funcionários
     */
    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {

        funcionarios.stream().forEach(funcionario -> {
            try {
                funcionarioDAO.put(funcionario);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Método para resgatar o array que se encontram os funcionários
     * cadastrados.
     *
     * @return ArrayList<Funcionario> - array dos funcionários
     */
    public List<Funcionario> getFuncionarios() {
        return funcionarioDAO.getList();
    }

    /**
     * Método que retorna o funcionário a partir de seu código.
     *
     * @param codigo - código do funcionário
     * @return Usuario - objeto funcionário(herda a classe Usuário)
     */
    public Usuario recuperaFuncionario(Long codigo) throws Exception {

        if (codigo > 0) {
            return funcionarioDAO.get(codigo);
        }
        return null;
    }

    /**
     * Método que deleta o cadastro do funcionário a partir de seu código.
     *
     * @param codigo - código do funcionário
     * @throws java.lang.Exception
     */
    public void deletaFuncionario(Long codigo) throws Exception {

        Funcionario funcionarioDeletado = null;

        if (codigo > 0) {
            funcionarioDAO.remove(codigo);
        }
    }

    /**
     * Método que adiciona o cadastro de um novo funcionário a partir das
     * informações definidas no parâmetro.
     *
     * @param codigo    - código do funcionário
     * @param nome      - nome do funcionário
     * @param cpf       - cpf do funcionário
     * @param TipoCargo - tipo de usuário(funcionário)
     * @param salario   - salário do funcionário
     * @throws java.lang.Exception
     */
    public void adicionaFuncionario(Long codigo, String nome, String cpf, TipoCargo TipoCargo, float salario) throws Exception {

        if (!verificaParametros(codigo, nome, cpf, TipoCargo, salario)) {
            throw new Exception("Parâmetros informados incorretamente.Não foi possivel cadastrar o funcionário.");
        }
        funcionarioDAO.put(new Funcionario(codigo, nome, cpf, TipoCargo, salario));
    }

    /**
     * Método que verifica se os parâmetros informados pelo usuário são válidos.
     *
     * @param codigo    - código do funcionário
     * @param nome      - nome do funcionário
     * @param cpf       - cpf do funcionário
     * @param TipoCargo - tipo de usuário(funcionário)
     * @param salario   - salário do funcionário
     * @return boolean - retorna verdadeiro se os parâmetros forem válidos
     */
    private boolean verificaParametros(Long codigo, String nome, String cpf, TipoCargo TipoCargo, float salario) {

        boolean valido = false;
        if (verificaNumerosValidos(codigo, salario)) {
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
     * @param codigo  - código do funcionário
     * @param salario - salário do funcionário
     * @return boolean - retorna verdadeiro se os parâmetros forem válidos
     */
    private boolean verificaNumerosValidos(Long codigo, float salario) {
        return ((codigo > 0) || (salario > 0));
    }

    /**
     * Método que verifica se os parâmetros informados pelo usuário são válidos.
     * Utilizado no método verificaParametros
     *
     * @param nome - nome do funcionário
     * @param cpf  - cpf do funcionário
     * @return boolean - retorna verdadeiro se os parâmetros forem válidos
     */
    private boolean verificaStringsValidas(String nome, String cpf) {
        return !((nome.isEmpty()) || (cpf.isEmpty()));
    }

    /**
     * Método que adiciona um usuário administrador padrão no primeiro acesso ao
     * sistema.
     *
     * @param codigo - código do usuário administrador(funcionário)
     * @throws java.lang.Exception
     */
    public void adicionaAdmin(Long codigo) throws Exception {

        if (codigoEstaDisponivel(codigo) == true) {
            funcionarioDAO.put(new Funcionario(codigo, "Administrador", "000.000.000-00", TipoCargo.DONO, 0));
        } else {
            throw new Exception("Administrador não adicionado");
        }

    }

    /**
     * Método que mostra as informações de um determinado funcionário
     *
     * @param funcionario - objeto do tipo Funcionario
     */
   

    /**
     * Método que lista na tela todos os funcionários cadastrados.
     *
     * @throws java.lang.Exception
     */

    /**
     * Método que inclui um treino para um determinado aluno.
     *
     * @param codigo    - código do usuário(aluno)
     * @param exercicio - código do exercicio
     * @throws java.lang.Exception
     */
    public void defineTreino(Long codigo, int exercicio) throws Exception {
        
        if (exercicio >= 0) {
        AlunoDAO.cacheAlunos.get(codigo).getTreino().add(TipoExercicio.values()[exercicio]);
    }
    }
    public boolean verificaPermissao(int escolha) {
        TipoTarefasFuncionario tarefa = TipoTarefasFuncionario.values()[escolha];
        if (PrincipalController.getInstance().getUsuario().getTipoCargo() == TipoCargo.ESTAGIARIO) {
            return (tarefa == TipoTarefasFuncionario.DELETAR || tarefa == TipoTarefasFuncionario.DEFINIR) ? false : true;
        }
        return true;

    }

}
