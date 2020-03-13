package View.rossini;

import Controllers.DAO.FuncionarioDAO;
import Controllerss.PrincipalController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginTelaG extends BaseTela {


    private JLabel codeFieldLabel;
    private JLabel texLogo;
    private ImageIcon iconlogo;
    private JTextField codeFieldText;
    private JButton loginButton;

    public LoginTelaG() {
        super();

    }

    @Override
    public void inicializaTela() {
        carregaLayout();
        adicionaListeners();
        centralizaTela(this);
    }

    @Override
    public void carregaLayout() {
        iconlogo = new ImageIcon((getClass().getResource("smartFit2.png")));
        texLogo = new JLabel(iconlogo);
        codeFieldLabel = new JLabel();
        codeFieldText = new JTextField(6);
        loginButton = new JButton();
        codeFieldLabel.setText("Código");
        codeFieldText.setText("");
        loginButton.setText("Entrar");

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(texLogo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(codeFieldLabel, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(codeFieldText, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO))
                        .addComponent(loginButton, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(texLogo, 0, 100, VALOR_MAXIMO)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(codeFieldLabel, 0, GroupLayout.DEFAULT_SIZE, VALOR_MAXIMO)
                                .addComponent(codeFieldText, 0, GroupLayout.DEFAULT_SIZE, VALOR_MAXIMO))
                        .addComponent(loginButton)
        );

        setPreferredSize(new Dimension(300, 300));
        setVisible(true);
        setDefaultCloseOperation(salvaDados());
        pack();
    }

    private int salvaDados(){
        try {
            FuncionarioDAO.getInstance().persistir();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JFrame.EXIT_ON_CLOSE;
    }

    @Override
    public void adicionaListeners() {
        loginButton.addActionListener(actionEvent -> {
            try {
                escondeTela();
                PrincipalController.getInstance().acessaSistema(Long.valueOf(codeFieldText.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
