import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSacar extends JFrame implements ActionListener {
    private ContaBancaria conta;
    private JButton btnSacar10, btnSacar20, btnSacar50, btnSacar100,btnOutras, btnVoltar;

    public TelaSacar(ContaBancaria conta){
        this.conta = conta;

        // Configuração da janela
        setTitle("Saque Multibanco");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        getContentPane().setBackground(Color.white);

        // Label para o título
        JLabel lblTitulo = new JLabel("Escolha o valor do saque:");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitulo.setBounds(200, 40, 400, 40);
        add(lblTitulo);

        // Botão para sacar €10
        btnSacar10 = new JButton("€ 10");
        btnSacar10.setBounds(200, 150, 120, 50);
        btnSacar10.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSacar10.addActionListener(this);
        add(btnSacar10);

        // Botão para sacar €20
        btnSacar20 = new JButton("€ 20");
        btnSacar20.setBounds(320, 150, 120, 50);
        btnSacar20.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSacar20.addActionListener(this);
        add(btnSacar20);

        // Botão para sacar €50
        btnSacar50 = new JButton("€ 50");
        btnSacar50.setBounds(200, 200, 120, 50);
        btnSacar50.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSacar50.addActionListener(this);
        add(btnSacar50);

        // Botão para sacar €100
        btnSacar100 = new JButton("€ 100");
        btnSacar100.setBounds(320, 200, 120, 50);
        btnSacar100.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSacar100.addActionListener(this);
        add(btnSacar100);

        // botao para outras importancias

        btnOutras = new JButton("Outros valores");
        btnOutras.setBounds(440, 150, 120, 50);
        btnOutras.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnOutras.addActionListener(this);
        add(btnOutras);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(440, 200, 120, 50);
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnVoltar.addActionListener(this);
        add(btnVoltar);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSacar10){
            if (conta.getSaldo() >= 10){
                conta.sacar(10);
            }else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            }
        }
        if (e.getSource() == btnSacar20){
            if (conta.getSaldo() >= 20){
                conta.sacar(20);
            }else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            }
        }
        if (e.getSource() == btnSacar50){
            if (conta.getSaldo() >= 50){
                conta.sacar(50);
            } else {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            }
        }
        if (e.getSource() == btnSacar100){
            if (conta.getSaldo() >= 100){
                conta.sacar(100);
            } else{
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            }
        }
        if (e.getSource() == btnOutras){
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a sacar:"));
            if (conta.getSaldo() >= valor){
                conta.sacar(valor);
            }else{
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
            }
        }
        if (e.getSource() == btnVoltar){
            dispose();
            new TelaCaixaEletronico(conta);
        }
    }
}
