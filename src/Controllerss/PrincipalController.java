package Controllerss;

import Controllers.DAO.FuncionarioDAO;
import Models.Usuario;

import java.util.Random;

import View.nicolas.AlunoTelaJS;
import View.nicolas.FuncionarioTelaJS;
import View.rossini.AdministradorTelaG;
import View.rossini.CadastroTelaG;
import View.rossini.LoginTelaG;

public class PrincipalController implements java.io.Serializable {

    private static PrincipalController ourInstance = new PrincipalController();

    private FuncionarioController funcionarioController = FuncionarioController.getInstance();
    private AlunoController alunoController = AlunoController.getInstance();
    private FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstance();


    public CadastroTelaG cadastroTela;
    public LoginTelaG loginTela;
    public AdministradorTelaG administradorTela;
    public FuncionarioTelaJS funcionarioTela;
    public AlunoTelaJS alunoTela;

    private Usuario usuario;

    /**
     * Método para resgatar a instância da classe, que foi instanciada no
     * atributo ourInstance
     *
     * @return PrincipalController - Instância da classe
     */
    public static PrincipalController getInstance() {
        return ourInstance;
    }

    public LoginTelaG getLoginTela() {
        return this.loginTela;
    }

    /**
     * Método para retorno do atributo usuario
     *
     * @return Usuario - usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Método construtor que instancia um novo controlador principal e
     * inicializa os atributos salvos no controlador de registro.
     */
    private PrincipalController() {

        cadastroTela = new CadastroTelaG();
        loginTela = new LoginTelaG();
        administradorTela = new AdministradorTelaG();
        funcionarioTela = new FuncionarioTelaJS();
        alunoTela = new AlunoTelaJS();
    }

    /**
     * Método que inicia o sistema e leva até ao método da tela de login do
     * usuário.
     *
     * @throws java.lang.Exception
     */
    public void inicia() throws Exception {

        try {
            if (this.verificaPrimeiroAcesso()) {
                this.cadastroTela.inicializaTela();
            }
            else{
                this.loginTela.inicializaTela();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Método que verifica se é o primeiro acesso ao sistema
     *
     * @return boolean - caso seja o primeiro acesso retornará true(verdadeiro)
     */
    public boolean verificaPrimeiroAcesso() {

        if (funcionarioController.getFuncionarios().isEmpty() && alunoController.getAlunos().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Método que cadastra um usuário administrador a partir da instância do
     * controlador de funcionário.
     *
     * @param codigo - código do usuário
     * @throws java.lang.Exception
     */
    public void cadastraAdmin(Long codigo) throws Exception {
        funcionarioController.adicionaAdmin(codigo);
    }

    /**
     * Método que gera um código aleatório entre 1 e 999 para um usuário
     *
     * @return int - código aleatório para o usuário
     * @throws java.lang.Exception
     */
    public Long geraCodigoRandomicoUnico() throws Exception {
        int i = 0;
        while (i < 1000) {
            long randomNumber = new Random().longs(1, 999).limit(1).findFirst().getAsLong();
            if (estaDisponivel(randomNumber) == true) {
                return randomNumber;
            }
            i++;
        }
        throw new Exception("Slots já ocupados!");
    }

    /**
     * Método que verifica se o código aleatório gerado já foi utilizado( se
     * está disponivel ).
     *
     * @return boolean - caso esteja disponivel retornará true(verdadeiro)
     */
    private boolean estaDisponivel(Long randomNumber) throws Exception {
        return funcionarioController.codigoEstaDisponivel(randomNumber)
                && alunoController.codigoEstaDisponivel(randomNumber);
    }

    /**
     * Método que retorna o usuário a partir de seu código.
     *
     * @param codigo - código do usuário
     * @return Usuario - objeto Usuario
     */
    public Usuario recuperaUsuario(Long codigo) throws Exception {

        if (!funcionarioController.codigoEstaDisponivel(codigo)) {
            return funcionarioController.recuperaFuncionario(codigo);
        }

        if (!alunoController.codigoEstaDisponivel(codigo)) {
            return alunoController.recuperaAluno(codigo);
        }

        return null;
    }

    /**
     * Método utilizado durante o acesso do sistema que verifica o tipo de
     * usuário e o leva até sua respectiva tela.
     *
     * @param codigo - código do usuário
     * @throws java.lang.Exception
     */
    public void acessaSistema(Long codigo) throws Exception {

        usuario = PrincipalController.getInstance().recuperaUsuario(codigo);
        switch (usuario.getTipoCargo()) {
            case DONO:
                this.administradorTela.inicializaTela();
                break;
            case ESTAGIARIO:
            case AUXILIAR:
            case INSTRUTOR:
                this.funcionarioTela.inicializaTela();
                break;
            case ALUNO:
                this.alunoTela.inicializaTela();
                break;
            default:
                this.loginTela.inicializaTela();
        }
    }
}
