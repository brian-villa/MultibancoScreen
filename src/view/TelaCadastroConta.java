package view;

import model.ContaBancaria;
import dao.ContaBancariaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroConta extends JFrame implements ActionListener {
    private JTextField txtNumeroConta, txtNomeTitular;
    private JPasswordField txtSenha;
    private JButton btnCadastrar, btnCancelar;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCadastrar) {
            try {
                int numeroConta = Integer.parseInt(txtNumeroConta.getText());
                String senha = new String(txtSenha.getPassword());
                String nomeTitular = txtNomeTitular.getText();

                // Validações básicas
                if (String.valueOf(numeroConta).length() != 6) {
                    JOptionPane.showMessageDialog(this, "O número da conta deve ter 6 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (senha.length() != 4) {
                    JOptionPane.showMessageDialog(this, "A senha deve ter 4 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nomeTitular.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "O nome do titular não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Criação da conta
                ContaBancaria novaConta = new ContaBancaria(numeroConta,nomeTitular, 0.0, senha);
                ContaBancariaDAO dao = new ContaBancariaDAO();
                dao.criarConta(novaConta);

                JOptionPane.showMessageDialog(this, "Conta cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new Login();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnCancelar) {
            dispose();
            new Login();
        }
    }
}
