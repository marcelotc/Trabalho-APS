package View.rossini;

import Controllerss.PrincipalController;

import javax.swing.*;
import java.awt.*;

public class CadastroTelaG extends BaseTela {

    private JTextArea msgPrincipal;
    private JLabel msgResultado;
    private JLabel codigoLabel;
    private JLabel codigoValue;
    private JLabel texLogo;
    private ImageIcon iconlogo;
    private JButton botaoCadastro;
    private JButton botaoLogar;

    public CadastroTelaG() throws HeadlessException {
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
        msgPrincipal = new JTextArea("Seja bem vindo ao sistema. \n" +
                "Este é primeiro acesso ao sistema, logo é necessário \n gerar um código inicial de acesso ao sistema.");
        iconlogo = new ImageIcon((getClass().getResource("smartFit2.png")));
        texLogo = new JLabel(iconlogo);
        codigoLabel = new JLabel("Código");
        codigoValue = new JLabel();
        msgResultado = new JLabel();
        botaoCadastro = new JButton("Cadastrar");
        botaoLogar = new JButton("Logar");

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(texLogo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                        .addComponent(msgPrincipal, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(codigoLabel, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(codigoValue, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO))
                        .addComponent(msgResultado, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoCadastro, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(botaoLogar, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(texLogo, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                        .addComponent(msgPrincipal, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(codigoLabel, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                                .addComponent(codigoValue, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO))
                        .addComponent(msgResultado, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(botaoCadastro, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                                .addComponent(botaoLogar, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)));

        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

    }

    @Override
    public void adicionaListeners() {
        botaoCadastro.addActionListener(actionEvent -> {
            try {
                Long codigo = PrincipalController.getInstance().geraCodigoRandomicoUnico();
                PrincipalController.getInstance().cadastraAdmin(codigo);
                JOptionPane.showMessageDialog(this, "Administrador cadastro com sucesso", "Cadastro de administrador", JOptionPane.INFORMATION_MESSAGE);
                codigoValue.setText(Long.toString(codigo));
                botaoCadastro.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Não foi possível cadastrar o administrador", "Cadastro de administrador", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
        botaoLogar.addActionListener(actionEvent -> {
            dispose();
            PrincipalController.getInstance().loginTela.inicializaTela();

        });
    }
}
