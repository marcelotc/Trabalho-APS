package View.nicolas;

import Controllerss.AlunoController;
import Controllerss.FuncionarioController;
import Controllerss.PrincipalController;
import Utils.TipoCargo;
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

public class CadastroAlunoJS extends JFrame {

//bel]
    private Long codigo;
    private JLabel msgInicial;
    private JLabel msgInicial2;
    private JLabel lbNome;
    private JLabel lbCPF;
    private JLabel lbPeso;
    private JLabel lbAltura;
    private JLabel lbIdade;
    private JButton btConcluir;
    private JButton btCancelar;

//textfield
    private JTextField tfNome;
    private JTextField tfCPF;
    private JTextField tfPeso;
    private JTextField tfAltura;
    private JTextField tfIdade;

//Parametros posicao
    private GridBagConstraints gbc;

    public CadastroAlunoJS() {

        super("Cadastrar aluno");
        
        
    }

    public void inicializaTela(){
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

       
        
        
        msgInicial = new JLabel("A opção de cadastrar um aluno foi selecionada");
        msgInicial2 = new JLabel("Preencha as informações desse aluno");
        lbNome = new JLabel("Nome:");
        lbCPF = new JLabel("CPF:");
        lbPeso = new JLabel("Peso:");
        lbAltura = new JLabel("Altura:");
        lbIdade = new JLabel("Idade:");
        btConcluir = new JButton("Concluir");
        btCancelar = new JButton("Cancelar");

        tfNome = new JTextField(20);
        tfCPF = new JTextField(20);
        tfPeso = new JTextField(20);
        tfAltura = new JTextField(20);
        tfIdade = new JTextField(20);

        gbc = new GridBagConstraints();

//instanciando container e setando layout
        JPanel container = new JPanel();
        getContentPane().add(container);
        container.setLayout(new GridBagLayout());

//configurações da janela
        setSize(500, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

//alinhamento horizontal e vertical e espaçamento entre os componentes
        gbc.insets = new Insets(10, 10, 10, 10);

//posicionamento dos labels iniciais
        gbc.anchor = GridBagConstraints.LINE_START;

//definindo a posição de cada componente
        gbc.gridx = 1;
        gbc.gridy = 0;
        container.add(msgInicial, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        container.add(msgInicial2, gbc);
        

        //posicionamento dos labels de cadastro
        gbc.anchor = GridBagConstraints.LINE_END;

        //nome
        gbc.gridx = 0;
        gbc.gridy = 3;
        container.add(lbNome, gbc);

        //cpf
        gbc.gridx = 0;
        gbc.gridy = 4;
        container.add(lbCPF, gbc);

        //peso
        gbc.gridx = 0;
        gbc.gridy = 5;
        container.add(lbPeso, gbc);

        //altura        
        gbc.gridx = 0;
        gbc.gridy = 6;
        container.add(lbAltura, gbc);
        //idade
        gbc.gridx = 0;
        gbc.gridy = 7;
        container.add(lbIdade, gbc);

        //posicionamente dos campos de texto
        gbc.anchor = GridBagConstraints.LINE_START;

        //nome
        gbc.gridx = 1;
        gbc.gridy = 3;
        container.add(tfNome, gbc);

        //cpf
        gbc.gridx = 1;
        gbc.gridy = 4;
        container.add(tfCPF, gbc);

        //peso
        gbc.gridx = 1;
        gbc.gridy = 5;
        container.add(tfPeso, gbc);

        //altura
        gbc.gridx = 1;
        gbc.gridy = 6;
        container.add(tfAltura, gbc);

        //idade
        gbc.gridx = 1;
        gbc.gridy = 7;
        container.add(tfIdade, gbc);

        //botoes
        //botao concluir
        gbc.gridx = 1;
        gbc.gridy = 8;
        container.add(btConcluir, gbc);

        //posicionamento do botao cancelar
        gbc.anchor = GridBagConstraints.CENTER;

        //botao cancelar
        gbc.gridx = 1;
        gbc.gridy = 8;
        container.add(btCancelar, gbc);

    }

    public void cadastraAluno() throws Exception {
        
        codigo = PrincipalController.getInstance().geraCodigoRandomicoUnico();
        
        AlunoController.getInstance().adicionaAluno(
                codigo,
                tfNome.getText(),
                tfCPF.getText(),
                TipoCargo.ALUNO,
                Float.parseFloat(tfPeso.getText()),
                Float.parseFloat(tfAltura.getText()),
                Integer.parseInt(tfIdade.getText()));
    }

    private void adicionaListener() {

        btConcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cadastraAluno();
                    JOptionPane.showMessageDialog(null, "O aluno foi cadastrado com sucesso! Código do aluno: "+codigo, "Cadastro de aluno", 1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Cadastro de aluno", 2);
                }
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrincipalController.getInstance().funcionarioTela.inicializaTela();
                dispose();

            }

        });

    }
}
