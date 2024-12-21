package view;

import dao.ContaBancariaDAO;
import model.ContaBancaria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    ContaBancaria conta;
    JButton login, clear, cadastrarConta;;
    JTextField pinTextField, numeroContaTextField;

    Login() {
        setTitle("ATM Multibanco");

        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);  // Impede redimensionamento
        setExtendedState(JFrame.NORMAL);  // Impede a maximização

        //Imagem da aplicação
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png"));
        Image i2 = icon.getImage().getScaledInstance(300, 200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(150, 10, 100, 100);
        add(label);

        // texto de título
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

        // PIN de validação
        JLabel pin = new JLabel("SENHA:");
        pin.setBounds(200, 150, 400, 40);
        add(pin);

        //Input senha
        pinTextField = new JPasswordField();
        pinTextField.setBounds(370, 159, 200, 30);
        add(pinTextField);

        //Botao de "OK"
        login = new JButton("Confirmar");
        login.setBounds(280, 250, 100, 30);
        login.setBackground(Color.GREEN);
        login.setForeground(Color.BLACK);
        login.addActionListener(this);
        add(login);

        //Botao de "Limpar"
        clear = new JButton("Limpar");
        clear.setBounds(410, 250, 100, 30);
        clear.setBackground(Color.YELLOW);
        clear.setForeground(Color.BLACK);
        clear.addActionListener(this);
        add(clear);

        //Botao de "Cadastrar COnta"
        cadastrarConta = new JButton("Cadastrar Conta");
        cadastrarConta.setBounds(280, 290, 230, 30);
        cadastrarConta.setBackground(Color.CYAN);
        cadastrarConta.setForeground(Color.BLACK);
        cadastrarConta.addActionListener(this);
        add(cadastrarConta);


        getContentPane().setBackground(Color.white);


        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            numeroContaTextField.setText("");
            pinTextField.setText("");
        } else if(e.getSource() == login) {
            //Verificaçao de senha
            try {
                String numeroContaInput = numeroContaTextField.getText();
                String senhaInput = pinTextField.getText();

                // Validação dos campos
                if (numeroContaInput.isEmpty() || senhaInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
                    return;
                }

                // Converte para int
                int numeroConta = Integer.parseInt(numeroContaInput);
                // Cria uma instância do DAO
                ContaBancariaDAO dao = new ContaBancariaDAO();
                conta = dao.carregarConta(numeroConta);

                if (conta == null) {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                } else if (conta.autenticar(senhaInput)) {
                    new TelaCaixaEletronico(conta);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Número da conta deve ser um número válido!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro :" + ex.getMessage());
            }
        } else if (e.getSource() == cadastrarConta) {
            //abrir a tela de cadastro de conta
            new TelaCadastroConta();
            dispose();
        }
    }

    public static void main(String[] args) {
        new Login();

    }


}
