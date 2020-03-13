package View.rossini;

import Controllerss.FuncionarioController;
import Controllerss.PrincipalController;
import Utils.TipoCargo;

import javax.swing.*;
import java.awt.*;

public class FuncionarioForm extends FormBase {


    public FuncionarioForm(Frame frame, String s) {
        super(frame, s, true);
    }

    private JLabel codigoLabel;
    private JLabel codigoCampo;

    private JLabel nomeLabel;
    private JTextField nomeCampo;

    private JLabel cpfLabel;
    private JTextField cpfCampo;
    private JLabel cargoLabel;
    private JComboBox<String> cargo;

    private JLabel salarioLabel;
    private JTextField salarioCampo;


    private JButton salvarBotao;
    private JButton limparBotao;
    private JButton voltarBotao;


    @Override
    public void inicializaForm() {
        carregaLayout();
        adicionaListeners();
        setSize(350, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
    }

    @Override
    public void carregaLayout() {
        codigoLabel = new JLabel("Codigo");
        codigoCampo = new JLabel(geraCodigoRandomico());
        nomeLabel = new JLabel("Nome");
        nomeCampo = new JTextField();
        cpfLabel = new JLabel("CPF");
        cpfCampo = new JTextField();
        cargoLabel = new JLabel("CPF");
        cargo = new JComboBox(TipoCargo.getFuncionariosTipos().toArray());
        salarioLabel = new JLabel("Salário");
        salarioCampo = new JTextField();
        salvarBotao = new JButton("Salvar");
        limparBotao = new JButton("Limpar");
        voltarBotao = new JButton("Voltar");

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(codigoLabel, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(codigoCampo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nomeLabel, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(nomeCampo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(cpfLabel, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(cpfCampo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(cargoLabel, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(cargo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(salarioLabel, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(salarioCampo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(salvarBotao, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(limparBotao, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(voltarBotao, 0, 200, VALOR_MAXIMO))

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(codigoLabel, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO)
                                .addComponent(codigoCampo, 0, VALOR_DEFAULT_HORIZONTAL, VALOR_MAXIMO))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(nomeLabel, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                                .addComponent(nomeCampo, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(cpfLabel, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                                .addComponent(cpfCampo, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(cargoLabel, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                                .addComponent(cargo, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(salarioLabel, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                                .addComponent(salarioCampo, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(salvarBotao, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                                .addComponent(limparBotao, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO)
                                .addComponent(voltarBotao, 0, VALOR_DEFAULT_VERTICAL, VALOR_MAXIMO))

        );
    }

    @Override
    public void adicionaListeners() {
        salvarBotao.addActionListener(
                actionEvent -> {
                    try {
                        criaFuncionario();
                        JOptionPane.showMessageDialog(this, "O funcionario foi cadastro com sucesso", "Cadastro de Funcionario", JOptionPane.INFORMATION_MESSAGE);


                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Existe algum campo fora do padrão", "Cadastro de Funcionario", JOptionPane.ERROR_MESSAGE);

                    }
                });
        limparBotao.addActionListener(actionEvent -> limparCampos());
        voltarBotao.addActionListener(actionEvent -> dispose());
    }

    private String geraCodigoRandomico() {
        try {
            return Long.toString(PrincipalController.getInstance().geraCodigoRandomicoUnico());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void limparCampos() {

        nomeCampo.setText("");
        cpfCampo.setText("");
        salarioCampo.setText("");
    }

    private void criaFuncionario() throws Exception {
        FuncionarioController.getInstance().adicionaFuncionario(
                Long.valueOf(codigoCampo.getText()),
                nomeCampo.getText(),
                cpfCampo.getText(),
                TipoCargo.valueOf(cargo.getSelectedItem().toString()),
                Float.parseFloat(salarioCampo.getText())
        );
    }


}
