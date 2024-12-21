package view;

import controller.TelaCadastroContaController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroConta extends JFrame implements ActionListener {
    private JTextField txtNumeroConta, txtNomeTitular;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnCancelar;
    private TelaCadastroContaController controller;

    public TelaCadastroConta() {
        // Configuração da janela
        setTitle("Cadastro de Conta Bancária");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10)); // 4 linhas, 2 colunas, espaçamento 10px
        setLocationRelativeTo(null);

        // Campos de entrada
        add(new JLabel("Número da Conta (6 dígitos):"));
        txtNumeroConta = new JTextField();
        add(txtNumeroConta);

        add(new JLabel("Senha (4 dígitos):"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        add(new JLabel("Nome do Titular:"));
        txtNomeTitular = new JTextField();
        add(txtNomeTitular);

        // Botões
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this);
        add(btnCadastrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        add(btnCancelar);

        setVisible(true);
    }

    public void setController(TelaCadastroContaController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastrar) {
            String numeroContaInput = txtNumeroConta.getText();
            String senhaInput = new String(txtSenha.getPassword());
            String nomeTitular = txtNomeTitular.getText();

            try {
                controller.cadastrarConta(numeroContaInput, senhaInput, nomeTitular);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar conta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
            new Login(); // Voltar para a tela de login
        }
    }

    public static void main(String[] args) {
        // Criar o controlador e a interface de cadastro
        TelaCadastroContaController controller = new TelaCadastroContaController();
        TelaCadastroConta telaCadastroConta = new TelaCadastroConta();
        telaCadastroConta.setController(controller);
        controller.setView(telaCadastroConta);
    }
}
