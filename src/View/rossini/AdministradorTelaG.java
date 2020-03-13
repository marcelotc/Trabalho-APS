package View.rossini;


import Controllers.DAO.FuncionarioDAO;
import Controllerss.PrincipalController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdministradorTelaG extends BaseTela {


    private JButton funcionarios;
    private JButton alunos;
    private JButton deslogar;

    private JButton encerrar;
    private ImageIcon iconlogo;

    private JLabel texLogo;

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
        setTitle("Modo Administrador");
        funcionarios = new JButton("Funcionários");
        alunos = new JButton("Alunos");
        deslogar = new JButton("Deslogar");
        encerrar = new JButton("Encerrar");

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(texLogo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                        .addComponent(funcionarios, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                        .addComponent(alunos, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                        .addComponent(deslogar, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(texLogo, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                        .addComponent(funcionarios, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                        .addComponent(alunos, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                        .addComponent(deslogar, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
        );
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public AdministradorTelaG() throws HeadlessException {
        super();
    }

    @Override
    public void adicionaListeners() {

        funcionarios.addActionListener(actionEvent -> {
            new FuncionarioTelaG();
            PrincipalController.getInstance().administradorTela.escondeTela();
        });
        alunos.addActionListener(actionEvent -> {
            new AlunoTelaG();
            PrincipalController.getInstance().administradorTela.escondeTela();
        });
        deslogar.addActionListener(actionEvent -> {
            dispose();
            try {
                FuncionarioDAO.getInstance().persistir();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrincipalController.getInstance().loginTela.mostraTela();
        });

    }
}
