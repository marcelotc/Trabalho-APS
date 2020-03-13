package Utils;

import java.util.Arrays;

public enum TipoExercicio {

    ESTEIRA, ESCADA, SUPINO_RETO, SUPINO_INCLINADO, LEG_PRESS, ABDUTOR_PERNA, EXTENSOR_PERNA,
            FLEXOR_PERNA, REMADA_BAIXA, REMADA_ALTA, FLEXÃO, AGACHAMENTO;

    public static void mostraExercicios() {
        Arrays.stream(TipoExercicio.values()).forEach(tipoExercicio -> {
            System.out.println(String.format("(%s)-  %s", tipoExercicio.ordinal(), tipoExercicio.toString()));
        });
    }

}
