package Controllers.DAO;

import Models.Aluno;
import Models.Funcionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlunoDAO extends DAO<Aluno> {

    public static HashMap<Long, Aluno> cacheAlunos;
    private static AlunoDAO ourInstance = new AlunoDAO();

    public static AlunoDAO getInstance() {
        return ourInstance;
    }

    private AlunoDAO() {
        enderecoArquivo = "C:\\Users\\nicol\\Desktop\\aluno.ser";
        cacheAlunos = new HashMap<>();
        loadAlunos();
    }

    @Override
    public Aluno get(Long codigo) throws Exception {
        if (!cacheAlunos.containsKey(codigo))
            throw new Exception("Nao existe Aluno para este codigo");
        return cacheAlunos.get(codigo);
    }

    @Override
    public void put(Aluno aluno) throws Exception {
        if (cacheAlunos.containsKey(aluno.getCodigo()))
            throw new Exception("Ja existe aluno para este codigo");
        cacheAlunos.put(aluno.getCodigo(), aluno);
        persistir();
    }

    @Override
    public void remove(Long codigo) throws Exception {
        if (!cacheAlunos.containsKey(codigo))
            throw new Exception("Esse aluno nao existe");
        cacheAlunos.remove(codigo);
        persistir();
    }

    @Override
    public List<Aluno> getList() {
        List<Aluno> funcionarios = new ArrayList<>(cacheAlunos.values());
        return funcionarios;
    }

    public void persistir() throws IOException {
        super.persist(cacheAlunos);
    }

    public void loadAlunos() {
        try {
            cacheAlunos = super.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}