package View.nicolas;

// As telas que irão implementar essa interface devem possuir os métodos de inicializar,cadastrar,deletar,listar e o método para escolher essas tarefas.
public interface InterfaceTela {

    /**
     * O método inicializar é responsável pela inicialização da tela do tipo de
     * usuário que está acessando o sistema. Dentro desse método o usuário irá
     * escolher uma das opções de sua tela.
     *
     * @throws java.lang.Exception
     */
    public void inicializar() throws Exception;

    /**
     * O método cadastrar é utilizada pelas telas que a implementam para
     * realizar algum tipo de cadastro.
     *
     * @throws java.lang.Exception
     */
    public void cadastrar() throws Exception;

    /**
     * O método deletar é responsável de excluir algum registro que foi
     * armazenado, como os cadastros.
     *
     * @throws java.lang.Exception
     */
    public void deletar() throws Exception;

    /**
     * As telas deverão utilizar esse método para listar na tela algum tipo de
     * registro que foi armazenado.
     *
     * @throws java.lang.Exception
     */
    public void listar() throws Exception;

    /**
     * Esse método é executado pela tela para definir qual tarefa escolhida pelo
     * usuário que vai ser iniciada
     *
     * @param escolha = número digitado pelo usuário
     * @throws java.lang.Exception
     */
    public void escolheTarefa(int escolha) throws Exception;

}
