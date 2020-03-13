package View.nicolas;

import Controllerss.FuncionarioController;
import Controllerss.PrincipalController;
import Utils.TipoExercicio;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DefinirTreinoJS extends JFrame {

    private JLabel lbInicial;
    private JLabel lbDigitarCodigo;
    private JLabel lbCodigosExercicios;
    private JLabel lbConcluir;
    private JTable tabelaExercicios;
    private JScrollPane scrollPane;

    private JButton btLimpar;
    private JButton btRetornar;
    private JButton btAdicionar;

    private JTextField tfCodigoAluno;
    private JTextField tfCodigoExercicio;

    private GridBagConstraints gbc;

    public DefinirTreinoJS() {

        super("Definir treino");
        inicializaTela();

    }

    public void inicializaTela() {
        carregaLayout();
        adicionarListener();
    }

    public void mostraTela() {
        limpaTela();
        setVisible(true);
    }

    public void escondeTela() {
        setVisible(false);
    }

    public void carregaLayout() {

        lbInicial = new JLabel("A opção de definir o treino de um aluno foi selecionada");
        lbDigitarCodigo = new JLabel("Digite o código do aluno: ");
        lbCodigosExercicios = new JLabel("Digite os códigos dos exercicios que irão ser incluidos ao treino do respectivo aluno. Para cada exercicio digite o código e clique em adicionar:");
        lbConcluir = new JLabel("Clique em limpar campos para começar uma nova inclusão de treino");

        btLimpar = new JButton("LIMPAR CAMPOS");
        btRetornar = new JButton("RETORNAR AO MENU");
        btAdicionar = new JButton("ADICIONAR");

        gbc = new GridBagConstraints();

        tfCodigoAluno = new JTextField(20);
        tfCodigoExercicio = new JTextField(20);

        criarTabelaExercicios();

        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

        setSize(1000, 600);
        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(lbInicial, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(lbDigitarCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        container.add(lbCodigosExercicios, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        container.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        container.add(lbConcluir, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(tfCodigoAluno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        container.add(tfCodigoExercicio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.ipadx = 125;
        container.add(btAdicionar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        container.add(btLimpar, gbc);

        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 10;
        container.add(btRetornar, gbc);

    }

    private void criarTabelaExercicios() {

        String[] colunaExercicio = {"Exercicio", "Código"};
        tabelaExercicios = new JTable(carregaTabelaExercicios(), colunaExercicio);
        tabelaExercicios.setEnabled(false);
        tabelaExercicios.setPreferredScrollableViewportSize(new Dimension(500, 80));
        tabelaExercicios.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tabelaExercicios);
    }

    private Object[][] carregaTabelaExercicios() {

        int i = 0;
        int numeroColunas = 2;
        int numeroLinhas = TipoExercicio.values().length;

        Object[][] exercicios = new Object[numeroLinhas][numeroColunas];

        for (TipoExercicio exercicio : TipoExercicio.values()) {

            exercicios[i][0] = exercicio.toString();
            exercicios[i][1] = i;
            i++;
        }

        return exercicios;
    }

    private void adicionaExercicio() throws Exception {

        Long codigo = Long.parseLong(tfCodigoAluno.getText());

        FuncionarioController.getInstance().defineTreino(
                codigo,
                Integer.parseInt(tfCodigoExercicio.getText())
        );

        limparCampos2();
    }

    private void limparCampos1() {

        tfCodigoAluno.setText("");
        tfCodigoExercicio.setText("");

    }

    private void limparCampos2() {
        tfCodigoExercicio.setText("");

    }

    private void adicionarListener() {

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    adicionaExercicio();
                    JOptionPane.showMessageDialog(null, "Exercicio vinculado com sucesso ao treino do aluno !", "Adicionar exercicio", 1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar exercicio. Verifique os códigos digitados !", "Adicionar exercicio", 2);
                }

            }
        });

        btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos1();
            }
        });

        btRetornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PrincipalController.getInstance().funcionarioTela.inicializaTela();

            }
        });
    }

    private void limpaTela() {
        tfCodigoAluno.setText("");
        tfCodigoExercicio.setText("");    }

}
