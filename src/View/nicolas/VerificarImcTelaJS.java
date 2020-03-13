package View.nicolas;

import Controllerss.AlunoController;
import Controllerss.PrincipalController;
import Models.Aluno;
import Utils.TipoExercicio;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VerificarImcTelaJS extends JFrame {

    private JTable tabelaImc;
    private JScrollPane scrollPane;
    private JLabel lbMedidasAtuais;
    private JLabel lbPesoAtual;
    private JLabel lbPesoAtual2;
    private JLabel lbAlturaAtual;
    private JLabel lbAlturaAtual2;
    private JLabel lbImcAluno;
    private JLabel lbImcAluno2;
    private JButton btVoltarMenu;

    private final GridBagConstraints gbc = new GridBagConstraints();

    public VerificarImcTelaJS() {
        super("Índice de massa corporal");

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

        Long codigo = PrincipalController.getInstance().getUsuario().getCodigo();
        Aluno aluno = (Aluno) AlunoController.getInstance().recuperaAluno(codigo);

        String peso = Float.toString(aluno.getPeso());
        String altura = Float.toString(aluno.getAltura());
        String imc = Double.toString(AlunoController.getInstance().calculaIMC(aluno));

        lbMedidasAtuais = new JLabel("Medidas atuais");
        lbPesoAtual = new JLabel("Peso: ");
        lbPesoAtual2 = new JLabel(peso);
        lbPesoAtual2.setForeground(Color.red);
        lbAlturaAtual = new JLabel("Altura: ");
        lbAlturaAtual2 = new JLabel(altura);
        lbAlturaAtual2.setForeground(Color.red);
        lbImcAluno = new JLabel("IMC: ");
        lbImcAluno2 = new JLabel(imc);
        lbImcAluno2.setForeground(Color.red);
        btVoltarMenu = new JButton("RETORNAR AO MENU PRINCIPAL");

        criarTabelaImc();

        //instanciando container e setando layout
        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

        //configurações da janela
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

//alinhamento horizontal e vertical e espaçamento entre os componentes
        gbc.insets = new Insets(10, 10, 10, 10);

        //posicionamento dos labels iniciais
        gbc.anchor = GridBagConstraints.CENTER;

        //label medidas atuais
        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add(lbMedidasAtuais, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;

        //label peso atual
        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(lbPesoAtual, gbc);

        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 1;
        container.add(lbPesoAtual2, gbc);
 
        
        //label altura atual
        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(lbAlturaAtual, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        container.add(lbAlturaAtual2, gbc);

        //label imc aluno
        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(lbImcAluno, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        container.add(lbImcAluno2, gbc);
        

        gbc.gridx = 1;
        gbc.gridy = 5;
        container.add(scrollPane, gbc);

        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.gridx = 1;
        gbc.gridy = 6;
        container.add(btVoltarMenu, gbc);

    }

    private void criarTabelaImc() {

        String[] colunas = {"IMC", "RESULTADO"};
        tabelaImc = new JTable(carregaTabelaImc(), colunas);
        tabelaImc.setEnabled(false);
        tabelaImc.setPreferredScrollableViewportSize(new Dimension(500, 96));
        tabelaImc.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tabelaImc);

    }

    private Object[][] carregaTabelaImc() {

        int numeroColunas = 2;
        int numeroLinhas = 6;

        Object[][] exercicios = new Object[numeroLinhas][numeroColunas];

        exercicios[0][0] = "Menos do que 18,5";
        exercicios[1][0] = "Entre 18,5 e 24,9";
        exercicios[2][0] = "Entre 25 e 29,9";
        exercicios[3][0] = "Entre 30 e 34,9";
        exercicios[4][0] = "Entre 35 e 39,9";
        exercicios[5][0] = "Mais do que 40";

        exercicios[0][1] = "Abaixo do peso";
        exercicios[1][1] = "Peso normal";
        exercicios[2][1] = "Sobrepeso";
        exercicios[3][1] = "Obesidade grau 1";
        exercicios[4][1] = "Obesidade grau 2";
        exercicios[5][1] = "Obesidade grau 3";

        return exercicios;
    }

    private void adicionaListener() {
        btVoltarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalController.getInstance().alunoTela.inicializaTela();
                dispose();
            }
        });
    }

}
