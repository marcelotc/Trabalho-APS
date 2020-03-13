package View.nicolas;

import Controllers.DAO.AlunoDAO;
import Controllerss.AlunoController;
import Controllerss.FuncionarioController;
import Controllerss.PrincipalController;
import Models.Aluno;
import java.awt.Font;
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

public class AlunoTelaJS extends JFrame {

    private JLabel msgBemVindo;
    private JLabel msgSelecionar;
    private JButton btVerificarTreino;
    private JButton btVerificarIMC;
    private JButton btDeslogarSistema;
    private GridBagConstraints gbc;

    public AlunoTelaJS() {

        super("Modo aluno - Menu");
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

        msgBemVindo = new JLabel("Bem vindo a SmarFit !");
        msgSelecionar = new JLabel("Selecione uma das seguintes opções");
        btVerificarTreino = new JButton("Verificar treino");
        btVerificarIMC = new JButton("Verificar índice de massa corporal (IMC)");
        btDeslogarSistema = new JButton("Deslogar do sistema");
        gbc = new GridBagConstraints();

//instanciando container e setando layout
        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

//tamanho da tela,visibilidade,fechar ao clicar no X,iniciando no meio da tela,tamanho da tela não ajustável    
        setSize(350, 350);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

//criando uma fonte nova
        Font fonte = new Font("Serif", Font.BOLD, 20);
        msgBemVindo.setFont(fonte);

//alinhamento horizontal e vertical e espaçamento entre os componentes
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

//definindo a posição de cada componente
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(msgBemVindo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(msgSelecionar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        container.add(btVerificarTreino, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(btVerificarIMC, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        container.add(btDeslogarSistema, gbc);

    }

    private void adicionaListener() {

        try {

            Long codigo = PrincipalController.getInstance().getUsuario().getCodigo();
            Aluno aluno;
            aluno = (Aluno) AlunoController.getInstance().recuperaAluno(codigo);

            btVerificarTreino.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (aluno.getTreino().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Seu treino ainda não foi definido!", "Verificar Treino", 2);
                    } else {
                        AlunoController.getInstance().treinoTela.inicializaTela();
                        escondeTela();
                    }
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERRO", "ERRO", 2);
        }

        btVerificarIMC.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        AlunoController.getInstance().imcTela.inicializaTela();
                        escondeTela();
                    }

                }
        );
        btDeslogarSistema.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e
                    ) {
                        dispose();
                        PrincipalController.getInstance().loginTela.mostraTela();
                        try {
                            AlunoDAO.getInstance().persistir();
                            
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null,"ERRO","ERRO",2);
                        }
                    }
                }
        );

    }
}
