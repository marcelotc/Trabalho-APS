package View.rossini;

import Controllers.DAO.FuncionarioDAO;
import Controllerss.PrincipalController;
import Models.Funcionario;

import javax.swing.*;
import java.awt.*;

public class FuncionarioTelaG extends BaseTela {

    private JTable funcionarioTabela;
    private JScrollPane scrollPane;
    private JButton remover;
    private JButton adicionar;
    private JButton voltar;

    private Long codeSelected;

    FuncionarioTelaG() throws HeadlessException {
        super();
        inicializaTela();
    }

    @Override
    public void inicializaTela() {
        carregaLayout();
        adicionaListeners();
        centralizaTela(this);

    }

    @Override
    public void carregaLayout() {
        carregaTabela();
        remover = new JButton("Remover");
        adicionar = new JButton("Adicionar");
        voltar = new JButton("Voltar");
        setTitle("Funcionários");
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(adicionar)
                                .addComponent(remover)
                                .addComponent(voltar)
                        ));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(adicionar)
                                .addComponent(remover)
                                .addComponent(voltar)));

        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    @Override
    public void adicionaListeners() {
        funcionarioTabela.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            int rowSelected = funcionarioTabela.getSelectedRow();
            codeSelected = Long.valueOf(funcionarioTabela.getValueAt(rowSelected, 0).toString());
        });

        remover.addActionListener(actionEvent -> {
            try {
                FuncionarioDAO.getInstance().remove(codeSelected);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        adicionar.addActionListener(actionEvent -> new FuncionarioForm(this, "Cadastro de Funcionario").inicializaForm());
        voltar.addActionListener(actionEvent -> {
            PrincipalController.getInstance().administradorTela.mostraTela();
            dispose();
        });
    }

    private void carregaTabela() {
        String[] columnNames = {"Codigo", "Nome", "CPF", "Cargo", "Salário"};
        funcionarioTabela = new JTable(carregaDadosTabela(), columnNames);
        scrollPane = new JScrollPane(funcionarioTabela);
        funcionarioTabela.setFillsViewportHeight(true);
    }

    private Object[][] carregaDadosTabela() {
        int i = 0;
        int numeroColunas = 5;
        int numeroDeLinhas = FuncionarioDAO.cacheFuncionarios.size();
        Object[][] funcionarios = new Object[numeroDeLinhas][numeroColunas];
        for (Funcionario funcionario : FuncionarioDAO.getInstance().getList()) {
            funcionarios[i][0] = funcionario.getCodigo();
            funcionarios[i][1] = funcionario.getNome();
            funcionarios[i][2] = funcionario.getCpf();
            funcionarios[i][3] = funcionario.getTipoCargo().name();
            funcionarios[i][4] = funcionario.getSalario();
            i++;
        }
        return funcionarios;
    }

}
