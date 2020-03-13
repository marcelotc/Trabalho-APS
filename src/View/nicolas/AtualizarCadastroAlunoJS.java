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

public class AtualizarCadastroAlunoJS extends JFrame {

    private JLabel lbInicial;
    private JLabel lbDigitarCodigo;
    private JLabel lbAlteracao;
    private JLabel lbPeso;
    private JLabel lbAltura;
    private JLabel lbIdade;

    private JTextField tfCodigoAluno;
    private JTextField tfPeso;
    private JTextField tfAltura;
    private JTextField tfIdade;

    private JButton btConcluir;
    private JButton btCancelar;

    private GridBagConstraints gbc;

    public AtualizarCadastroAlunoJS() {

        super("Atualizar cadastro de aluno");

       

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

        lbInicial = new JLabel("A opção para atualizar o cadastro de um aluno foi selecionada. A alteração é válida apenas para o peso, altura e idade.");
        lbDigitarCodigo = new JLabel("Digite o código do aluno:");
        lbAlteracao = new JLabel("Informe todas as 3 informações mesmo que deseje alterar apenas uma delas.");
        lbPeso = new JLabel("Peso: ");
        lbAltura = new JLabel("Altura: ");
        lbIdade = new JLabel("Idade: ");

        tfCodigoAluno = new JTextField(20);
        tfPeso = new JTextField(20);
        tfAltura = new JTextField(20);
        tfIdade = new JTextField(20);

        btConcluir = new JButton("CONCLUIR");
        btCancelar = new JButton("CANCELAR");

        gbc = new GridBagConstraints();

        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

        setSize(800, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        container.add(lbInicial, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        container.add(lbAlteracao, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(lbDigitarCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        container.add(lbPeso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        container.add(lbAltura, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        container.add(lbIdade, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        //text field
        gbc.gridx = 1;
        gbc.gridy = 4;
        container.add(tfPeso, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        container.add(tfAltura, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        container.add(tfIdade, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        container.add(tfCodigoAluno, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.ipadx = 130;
        container.add(btConcluir, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.ipadx = 130;
        container.add(btCancelar, gbc);

    }

    public void atualizaCadastro() throws Exception {

        Long codigo = Long.parseLong(tfCodigoAluno.getText());

        AlunoController.getInstance().alteraCadastro(
                codigo,
                Float.parseFloat(tfPeso.getText()),
                Float.parseFloat(tfAltura.getText()),
                Integer.parseInt(tfIdade.getText())
        );
    }

    private void adicionarListener() {

        btConcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    atualizaCadastro();
                    JOptionPane.showMessageDialog(null, "O cadastro do aluno foi atualizado com sucesso !", "Atualizar cadastro", 1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar cadastro de aluno, verifique as informações digitadas !", "Atualizar cadastro", 2);
                }

            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalController.getInstance().funcionarioTela.mostraTela();
                dispose();

            }

        });

    }

}
