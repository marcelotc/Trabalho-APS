package View.rossini;

import javax.swing.*;
import java.awt.*;

abstract public class BaseTela extends JFrame {
    final int VALOR_MAXIMO = 500;
    final int VALOR_DEFAULT_HORIZONTAL = 150;
    final int VALOR_DEFAULT_VERTICAL = GroupLayout.DEFAULT_SIZE;


    public GroupLayout layout;

    public BaseTela() throws HeadlessException {
        Container panel = getContentPane();
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    }
    public abstract void inicializaTela();
    public  void mostraTela(){
        setVisible(true);
    }

    public  void escondeTela(){
        setVisible(false);
    }
    public abstract void  carregaLayout();
    public abstract void  adicionaListeners();

    public void centralizaTela(JFrame frame){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }


}
