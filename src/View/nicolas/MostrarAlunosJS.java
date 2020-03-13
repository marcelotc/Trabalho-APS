package View.nicolas;

import Controllers.DAO.AlunoDAO;
import Controllerss.FuncionarioController;
import Controllerss.PrincipalController;
import Models.Aluno;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MostrarAlunosJS extends JFrame {

    private JTable tabelaAlunos;
    private JScrollPane scrollPane;
    private JLabel lbInicial;
    private JLabel lbAlunos;
    private JButton btVoltarMenu;

    private GridBagConstraints gbc;

    public MostrarAlunosJS() {

        super("Alunos da academia");

       

    }

    public void inicializaTela() {
        carregaLayout();
        adicionaListener();

    }

    public void mostraTela() {
        setVisible(true);
    }

    public void escondeTela() {
        setVisible(false);
    }

    public void carregaLayout() {

        lbInicial = new JLabel("A opção de listar os alunos foi selecionada");
        lbAlunos = new JLabel("Alunos: ");
        btVoltarMenu = new JButton("RETORNAR AO MENU");
        gbc = new GridBagConstraints();

        criarTabelaAlunos();

        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

        setSize(700, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(lbInicial, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(lbAlunos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        container.add(btVoltarMenu, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.CENTER;
        container.add(scrollPane, gbc);

    }

    private void criarTabelaAlunos() {

        String[] colunasInfoAluno = {"Código", "Nome", "CPF", "Peso", "Altura", "Idade"};
        tabelaAlunos = new JTable(carregaDadosTabela(), colunasInfoAluno);
        tabelaAlunos.setEnabled(false);
        tabelaAlunos.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabelaAlunos.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tabelaAlunos);

    }

    private Object[][] carregaDadosTabela() {

        int i = 0;
        int numeroColunas = 6;
        int numeroLinhas = AlunoDAO.cacheAlunos.size();

        Object[][] alunos = new Object[numeroLinhas][numeroColunas];
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

    private void adicionaListener() {

        btVoltarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalController.getInstance().funcionarioTela.inicializaTela();
                dispose();

            }
        });

    }
}
