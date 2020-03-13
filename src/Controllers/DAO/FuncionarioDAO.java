package Controllers.DAO;

import Models.Funcionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FuncionarioDAO extends DAO<Funcionario> {
    private static FuncionarioDAO ourInstance = new FuncionarioDAO();

    public static FuncionarioDAO getInstance() {
        return ourInstance;
    }

    public static HashMap<Long, Funcionario> cacheFuncionarios;

    private FuncionarioDAO() {
        enderecoArquivo = "C:\\Users\\nicol\\Desktop\\funcionario.ser";
        cacheFuncionarios = new HashMap<>();
        loadFuncionarios();
    }

    @Override
    public Funcionario get(Long codigo) throws Exception {
        if (!cacheFuncionarios.containsKey(codigo))
            throw new Exception("Nao existe funcionario para este codigo");
        return cacheFuncionarios.get(codigo);
    }

    @Override
    public void put(Funcionario funcionario) throws Exception {
        if (cacheFuncionarios.containsKey(funcionario.getCodigo()))
            throw new Exception("Ja existe funcionario para este codigo");
        cacheFuncionarios.put(funcionario.getCodigo(), funcionario);
        persistir();
    }

    @Override
    public void remove(Long codigo) throws Exception {
        if (!cacheFuncionarios.containsKey(codigo))
            throw new Exception("Esse funcionario nao existe");
        cacheFuncionarios.remove(codigo);
        persistir();
    }

    @Override
    public List<Funcionario> getList() {
        List<Funcionario> funcionarios = new ArrayList<>(cacheFuncionarios.values());
        return funcionarios;
    }

    public void persistir() throws IOException {
        super.persist(cacheFuncionarios);
    }

    public void loadFuncionarios() {
        try {
            cacheFuncionarios = super.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
