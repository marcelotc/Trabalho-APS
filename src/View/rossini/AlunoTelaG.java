package View.rossini;

import Controllers.DAO.AlunoDAO;
import Controllerss.PrincipalController;
import Models.Aluno;

import javax.swing.*;
import java.awt.*;

public class AlunoTelaG extends BaseTela {

    private JTable alunoTabela;
    private JScrollPane scrollPane;
    private JButton remover;
    private JButton voltar;
    private Long codeSelected;

    @Override
    public void inicializaTela() {
        carregaLayout();
        adicionaListeners();
        centralizaTela(this);
    }

    AlunoTelaG() throws HeadlessException {
        super();
        inicializaTela();
    }

    @Override
    public void carregaLayout() {
        carregaTabela();
        remover = new JButton("Remover");
        voltar = new JButton("Voltar");
        setTitle("Alunos");
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(remover)
                                .addComponent(voltar)
                        ));
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(remover)
                                .addComponent(voltar)));

        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    @Override
    public void adicionaListeners() {
        alunoTabela.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            int rowSelected = alunoTabela.getSelectedRow();
            codeSelected = Long.valueOf(alunoTabela.getValueAt(rowSelected, 0).toString());
        });

        remover.addActionListener(actionEvent -> {
            try {
                AlunoDAO.getInstance().remove(codeSelected);
                carregaTabela();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        voltar.addActionListener(actionEvent -> {
            PrincipalController.getInstance().administradorTela.mostraTela();
            dispose();
        });
    }

    private void carregaTabela() {
        String[] columnNames = {"Codigo", "Nome", "CPF", "Peso", "Altura", "Idade"};
        alunoTabela = new JTable(carregaDadosTabela(), columnNames);
        scrollPane = new JScrollPane(alunoTabela);
        alunoTabela.setFillsViewportHeight(true);
    }

    private Object[][] carregaDadosTabela() {
        int i = 0;
        int numeroColunas = 6;
        int numeroDeLinhas = AlunoDAO.cacheAlunos.size();
        Object[][] alunos = new Object[numeroDeLinhas][numeroColunas];
        for (Aluno aluno : AlunoDAO.getInstance().getList()) {
            alunos[i][0] = aluno.getCodigo();
            alunos[i][1] = aluno.getNome();
            alunos[i][2] = aluno.getCpf();
            alunos[i][3] = aluno.getPeso();
            alunos[i][4] = aluno.getAltura();
            alunos[i][5] = aluno.getIdade();
            i++;
        }
        return alunos;
    }

}
