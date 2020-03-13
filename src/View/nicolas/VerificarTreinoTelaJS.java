package View.nicolas;

import Controllerss.AlunoController;
import Controllerss.PrincipalController;
import Models.Aluno;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VerificarTreinoTelaJS extends JFrame {

    private JTable exercicios;
    private JScrollPane scrollPane;
    private JLabel lbRepeticoesAerobios;
    private JLabel lbRepeticoesAnaerobios;
    private JLabel lbOrientacao;
    private JButton btVoltar;
    private GridBagConstraints gbc;

    public VerificarTreinoTelaJS() {
        super("Treino");
        
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

        lbRepeticoesAerobios = new JLabel("Exercicios aeróbios - Realizar de 10 a 30 minutos");
        lbRepeticoesAnaerobios = new JLabel("Exercicios anaeróbios - Realizar 3 séries de 10 a 15 repetições");
        lbOrientacao = new JLabel("Solicite orientação do seu professor");
        btVoltar = new JButton("RETORNAR AO MENU PRINCIPAL");
        gbc = new GridBagConstraints();

        criarTabelaTreino();

        //instanciando container e setando layout
        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

        //configurações da janela
        setSize(600, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //alinhamento horizontal e vertical e espaçamento entre os componentes
        gbc.insets = new Insets(10, 10, 10, 10);

        //posicionamento dos labels iniciais
        //posicionamento dos componentes
        //adicionar tabela do treino do aluno
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(scrollPane, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(lbRepeticoesAerobios, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(lbRepeticoesAnaerobios, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(lbOrientacao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        container.add(btVoltar, gbc);

    }

    private void criarTabelaTreino() {

        String[] colunaExercicios = {"Exercicio"};
        exercicios = new JTable(carregaTabelaTreino(), colunaExercicios);
        exercicios.setEnabled(false);
        exercicios.setPreferredScrollableViewportSize(new Dimension(500, 80));
        exercicios.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(exercicios);
    }

    private Object[][] carregaTabelaTreino() {

        Aluno aluno = (Aluno) AlunoController.getInstance().recuperaAluno(PrincipalController.getInstance().getUsuario().getCodigo());

        int i = 0;
        int numeroColunas = 1;
        int numeroLinhas = aluno.getTreino().size();

        Object[][] treino = new Object[numeroLinhas][numeroColunas];

        for (TipoExercicio exercicio : aluno.getTreino()) {
            treino[i][0] = exercicio.toString();
            i++;
        }

        return treino;
    }

    private void adicionaListener() {

        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalController.getInstance().alunoTela.inicializaTela();
                dispose();
            }
        });

    }

}
