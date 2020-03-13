package View.nicolas;

import Controllerss.AlunoController;
import Controllerss.PrincipalController;
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
import javax.swing.JTextField;

public class DeletarCadastroAlunoJS extends JFrame {

    private JLabel lbInicial;
    private JLabel lbDigitarCodigo;

    private JButton btRemover;
    private JButton btCancelar;

    private JTextField tfCodigoAluno;

    private GridBagConstraints gbc;

    public DeletarCadastroAlunoJS() {

        super("Deletar cadastro de aluno");

    }

    public void inicializaTela() {
        carregaLayout();
        adicionarListener();
    }

    public void mostraTela() {
        setVisible(true);
    }

    public void escondeTela() {
        setVisible(false);
    }

    public void carregaLayout() {

        lbInicial = new JLabel("A opção de remover o cadastro de um aluno foi selecionada");
        lbDigitarCodigo = new JLabel("Informe o código do aluno a ser removido:");

        btRemover = new JButton("REMOVER");
        btCancelar = new JButton("CANCELAR");

        tfCodigoAluno = new JTextField(20);

        gbc = new GridBagConstraints();

        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

        setSize(550, 300);
        setVisible(true);
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

        gbc.gridx = 1;
        gbc.gridy = 1;
        container.add(tfCodigoAluno, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        container.add(btRemover, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 1;
        gbc.gridy = 2;
        container.add(btCancelar, gbc);

    }

    private void deletaCadastroAluno() throws Exception {

        Long codigo = Long.parseLong(tfCodigoAluno.getText());

        AlunoController.getInstance().deletaAluno(codigo);

    }

    private void limparCampos(){
    tfCodigoAluno.setText("");
    }
    
    
    private void adicionarListener() {

        btRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    deletaCadastroAluno();
                    JOptionPane.showMessageDialog(null, "O cadastro do aluno foi removido com sucesso", "Remover cadastro de aluno", 1);
                    limparCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel remover o cadastro desse aluno, verifique o código digitado !", "Remover cadastro de aluno", 2);
                    limparCampos(); 
                }

            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalController.getInstance().funcionarioTela.inicializaTela();
                limparCampos();
                dispose();
                
            }
        });

    }

}
