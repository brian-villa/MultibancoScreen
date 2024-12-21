package view;

import controller.LoginController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private JTextField numeroContaTextField, pinTextField;
    private JButton loginButton, clearButton, cadastrarContaButton;
    private LoginController controller;

    public Login() {
        setTitle("ATM Multibanco");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.NORMAL);

        // Imagem da aplicação
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png"));
        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon iconScaled = new ImageIcon(img);

        JLabel label = new JLabel(iconScaled);
        label.setBounds(150, 10, 100, 100);
        add(label);

        // Título
        JLabel text = new JLabel("Multibanco ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(270, 40, 400, 40);
        add(text);

        // Número da Conta
        JLabel numeroContaLabel = new JLabel("NÚMERO DA CONTA:");
        numeroContaLabel.setBounds(200, 100, 200, 40);
        add(numeroContaLabel);

        numeroContaTextField = new JTextField();
        numeroContaTextField.setBounds(370, 110, 200, 30);
        add(numeroContaTextField);

        // Senha
        JLabel pinLabel = new JLabel("SENHA:");
        pinLabel.setBounds(200, 150, 200, 40);
        add(pinLabel);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(370, 159, 200, 30);
        add(pinTextField);

        // Botões
        loginButton = new JButton("Confirmar");
        loginButton.setBounds(280, 250, 100, 30);
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.BLACK);
        loginButton.addActionListener(this);
        add(loginButton);

        clearButton = new JButton("Limpar");
        clearButton.setBounds(410, 250, 100, 30);
        clearButton.setBackground(Color.YELLOW);
        clearButton.setForeground(Color.BLACK);
        clearButton.addActionListener(this);
        add(clearButton);

        cadastrarContaButton = new JButton("Cadastrar Conta");
        cadastrarContaButton.setBounds(280, 290, 230, 30);
        cadastrarContaButton.setBackground(Color.CYAN);
        cadastrarContaButton.setForeground(Color.BLACK);
        cadastrarContaButton.addActionListener(this);
        add(cadastrarContaButton);

        // Definir fundo
        getContentPane().setBackground(Color.white);

        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            numeroContaTextField.setText("");
            pinTextField.setText("");
        } else if (e.getSource() == loginButton) {
            String numeroContaInput = numeroContaTextField.getText();
            String senhaInput = pinTextField.getText();

            if (numeroContaInput.isEmpty() || senhaInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
                return;
            }

            try {
                controller.autenticarConta(numeroContaInput, senhaInput);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        } else if (e.getSource() == cadastrarContaButton) {
            new TelaCadastroConta();
            dispose();
        }
    }

    public static void main(String[] args) {
        // Criar a instância do controlador
        LoginController controller = new LoginController();

        // Criar a interface de login e configurar o controlador
        Login loginView = new Login();
        loginView.setController(controller);
        controller.setView(loginView);
    }
}
