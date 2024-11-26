import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDepositar extends JFrame implements ActionListener {
    private ContaBancaria conta;
    private JButton btnDepositar10, btnDepositar20, btnDepositar50, btnDepositar100,btnDepOutras, btnVoltar;

    public TelaDepositar(ContaBancaria conta){
        this.conta = conta;

        // Configuração da janela
        setTitle("Depositar Multibanco");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        getContentPane().setBackground(Color.white);

        // Label para o título
        JLabel lblTitulo = new JLabel("Escolha o valor do Deposito:");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitulo.setBounds(200, 40, 400, 40);
        add(lblTitulo);

        // Botão para sacar €10
        btnDepositar10 = new JButton("€ 10");
        btnDepositar10.setBounds(200, 150, 120, 50);
        btnDepositar10.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnDepositar10.addActionListener(this);
        add(btnDepositar10);

        // Botão para sacar €20
        btnDepositar20 = new JButton("€ 20");
        btnDepositar20.setBounds(320, 150, 120, 50);
        btnDepositar20.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnDepositar20.addActionListener(this);
        add(btnDepositar20);

        // Botão para sacar €50
        btnDepositar50 = new JButton("€ 50");
        btnDepositar50.setBounds(200, 200, 120, 50);
        btnDepositar50.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnDepositar50.addActionListener(this);
        add(btnDepositar50);

        // Botão para sacar €100
        btnDepositar100 = new JButton("€ 100");
        btnDepositar100.setBounds(320, 200, 120, 50);
        btnDepositar100.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnDepositar100.addActionListener(this);
        add(btnDepositar100);

        // botao para outras importancias

        btnDepOutras = new JButton("Outros valores");
        btnDepOutras.setBounds(440, 150, 120, 50);
        btnDepOutras.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnDepOutras.addActionListener(this);
        add(btnDepOutras);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(440, 200, 120, 50);
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnVoltar.addActionListener(this);
        add(btnVoltar);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnVoltar){
            dispose();

        }
        if(e.getSource() == btnDepositar10){
            conta.depositar(10);
            JOptionPane.showMessageDialog(null, "Deposito Realizado com sucesso!");
        }
        if(e.getSource() == btnDepositar20){
            conta.depositar(20);
            JOptionPane.showMessageDialog(null, "Deposito Realizado com sucesso!");
        }
        if(e.getSource() == btnDepositar50){
            conta.depositar(50);
            JOptionPane.showMessageDialog(null, "Deposito Realizado com sucesso!");
        }
        if(e.getSource() == btnDepositar100){
            conta.depositar(100);
            JOptionPane.showMessageDialog(null, "Deposito Realizado com sucesso!");
        }
        if (e.getSource() == btnDepOutras){
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a depositar:"));
            conta.depositar(valor);
            JOptionPane.showMessageDialog(null, "Deposito Realizado com sucesso!");
        }
        if (e.getSource() == btnVoltar){
            dispose();
            new TelaCaixaEletronico(conta);
        }
    }
}
