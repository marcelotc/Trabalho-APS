package View.rossini;

import javax.swing.*;
import java.awt.*;

public abstract class FormBase extends JDialog {
    final int VALOR_MAXIMO = 300;
    final int VALOR_DEFAULT_HORIZONTAL = 200;
    final int VALOR_DEFAULT_VERTICAL = 50;

    private Container panel = getContentPane();
    public GroupLayout layout = new GroupLayout(panel);

    FormBase(Frame frame, String title, Boolean modal) {
        super(frame, title, modal);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

    }

    public abstract void inicializaForm();

    public abstract void carregaLayout();

    public abstract void adicionaListeners();

    public void centralizaTela(JDialog dialog) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - dialog.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - dialog.getHeight()) / 2);
        dialog.setLocation(x, y);
    }

}
