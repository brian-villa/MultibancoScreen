package view;

import model.ContaBancaria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    ContaBancaria conta;
    JButton login, clear;
    JTextField pinTextField;

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


        getContentPane().setBackground(Color.white);


        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            pinTextField.setText("");
        } else if(e.getSource() == login) {
            //Verificaçao de senha
            try {
                String senhaInput = pinTextField.getText();

                if (conta.autenticar(senhaInput)) {
                    new TelaCaixaEletronico(conta); 
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Login();

    }


}
