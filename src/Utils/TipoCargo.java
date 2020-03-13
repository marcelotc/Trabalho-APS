package Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoCargo {
    ALUNO, AUXILIAR, DONO, INSTRUTOR, ESTAGIARIO;

    public static void mostraCargos() {
        Arrays.stream(TipoCargo.values()).forEach(tipoCargo -> {
            System.out.println(String.format("(%s)-  %s", tipoCargo.ordinal(), tipoCargo.toString()));
        });

    }

    public static ArrayList<String> getFuncionariosTipos() {
        ArrayList<String> strings = new ArrayList<>();
        Arrays.stream(TipoCargo.values()).forEach(tipoCargo -> {
            if(tipoCargo !=TipoCargo.ALUNO) strings.add(tipoCargo.toString());
        });
        return strings;
    }


}


