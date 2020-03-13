package View.nicolas;

import Controllers.DAO.AlunoDAO;
import Controllers.DAO.FuncionarioDAO;
import Controllerss.FuncionarioController;
import Controllerss.PrincipalController;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FuncionarioTelaJS extends JFrame {

    private JLabel msgSelecionar;
    private JButton btCadastrarAluno;
    private JButton btAtualizarCadastroAluno;
    private JButton btDeletarAluno;
    private JButton btMostrarAlunos;
    private JButton btDefinirTreino;
    private JButton btDeslogarSistema;
    private GridBagConstraints gbc;

    public FuncionarioTelaJS() {

        super("Modo funcionário - Menu");

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

        msgSelecionar = new JLabel("Selecione uma das seguintes opções");
        btCadastrarAluno = new JButton("Cadastrar aluno");
        btAtualizarCadastroAluno = new JButton("Atualizar cadastro aluno");
        btDeletarAluno = new JButton("Deletar cadastro aluno");
        btMostrarAlunos = new JButton("Mostrar alunos da academia");
        btDefinirTreino = new JButton("Definir treino de aluno");
        btDeslogarSistema = new JButton("Deslogar do sistema");
        gbc = new GridBagConstraints();

//instanciando container e definindo layout
        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

//configurações da janela
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

//alinhamento horizontal e vertical e espaçamento entre os componentes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

//definindo a posição de cada componente
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(msgSelecionar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(btCadastrarAluno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(btAtualizarCadastroAluno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(btDeletarAluno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        container.add(btMostrarAlunos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        container.add(btDefinirTreino, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        container.add(btDeslogarSistema, gbc);
    }

    private void adicionaListener() {

        btCadastrarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FuncionarioController.getInstance().cadastroTelaAluno.inicializaTela();
                dispose();

            }

        });
        btAtualizarCadastroAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncionarioController.getInstance().atualizaCadastroAluno.inicializaTela();
                dispose();
            }
        });
        btDeletarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncionarioController.getInstance().deletaCadastroAluno.inicializaTela();
                dispose();

            }

        });
        btMostrarAlunos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncionarioController.getInstance().mostraAlunos.inicializaTela();
                dispose();
            }

        });

        btDefinirTreino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncionarioController.getInstance().defineTreinoAluno.mostraTela();
                dispose();
            }

        });

        btDeslogarSistema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PrincipalController.getInstance().loginTela.mostraTela();
                try {
                    FuncionarioDAO.getInstance().persistir();
                    AlunoDAO.getInstance().persistir();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERRO", "ERRO", 2);
                }
            }

        });

    }

}
