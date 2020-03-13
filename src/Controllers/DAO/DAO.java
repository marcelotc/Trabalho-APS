package Controllers.DAO;
import Models.Funcionario;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public abstract class DAO<T> {

    public String enderecoArquivo = "";

    public void persist(HashMap<Long, T> usuario) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream(enderecoArquivo);

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(usuario);
            out.flush();
            out.close();
            fileOut.close();

        } catch (
                FileNotFoundException var4) {
            var4.printStackTrace();
        } catch (
                IOException var5) {
            var5.printStackTrace();
        }

    }

    public HashMap<Long,T> load() throws IOException {
        try {
            FileInputStream fileIn = new FileInputStream(enderecoArquivo);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            HashMap<Long,T> usuario = (HashMap<Long, T>) in.readObject();
            in.close();
            fileIn.close();
            return  usuario;
        } catch (IOException var4) {
            return new HashMap<>();
        } catch (ClassNotFoundException var5) {
            return new HashMap<>();
        }
    }


    public abstract T get(Long codigo) throws Exception;

    public abstract void put(T usuario) throws Exception;

    public abstract void remove(Long codigo) throws Exception;

    public abstract List<T> getList();

}