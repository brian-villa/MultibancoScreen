import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCaixaEletronico extends JFrame implements ActionListener {
    ContaBancaria conta;
    JTextField txtSaldo;
    JButton btnDepositar, btnSacar, btnSaldo, btnSair;

    public TelaCaixaEletronico(ContaBancaria conta) {
        this.conta = conta;

        //configuração da janela
        setTitle("ATM Multibanco");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.white);
        setLocation(350, 200);
        setVisible(true);

        //Tela principal
        JLabel text = new JLabel("Selecione a sua Transação:", JLabel.CENTER);
        text.setBounds(235,230, 350, 35);
        text.setFont(new Font("System", Font.BOLD, 20));
        add(text);

        //MENSAGEM PARA USUARIO
        JLabel lblBemVindo = new JLabel("Bem-vindo, " + conta.getTitular() + "!");
        lblBemVindo.setBounds(270,30, 350, 35);
        lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblBemVindo.setBounds(240, 150, 400, 40);
        add(lblBemVindo);


        //Botao Depositar
        btnDepositar = new JButton("Depositar");
        btnDepositar.setBounds(170 , 315, 150, 30);
        btnDepositar.addActionListener(this);
        add(btnDepositar);

        //Botao SACAR
        btnSacar = new JButton("Sacar");
        btnSacar.setBounds(485, 315, 150,30);
        btnSacar.addActionListener(this);
        add(btnSacar);

        //Botao SALDO
        btnSaldo = new JButton("Saldo");
        btnSaldo.setBounds(170, 375, 150,30);
        btnSaldo.addActionListener(this);
        add(btnSaldo);

        //Botao SAIR
        btnSair = new JButton("Sair");
        btnSair.setBounds(485, 375, 150,30);
        btnSair.addActionListener(this);
        add(btnSair);


        setVisible(true);
    }

    private void atualizarSaldo() {
        if (txtSaldo != null) {
            txtSaldo.setText(String.format("€ %.2f", conta.getSaldo()));
        }
    }


    //Evento dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSair) {
            System.exit(0);

        } else if(e.getSource() == btnSaldo) {
            dispose();
            new TelaSaldo(conta).setVisible(true);
            atualizarSaldo();

        } else if(e.getSource() == btnDepositar) {
            dispose();
            new TelaDepositar(conta).setVisible(true);
            atualizarSaldo();

        } else if (e.getSource() == btnSacar) {
            dispose();
            new TelaSacar(conta).setVisible(true);
            atualizarSaldo();
        }
    }
}
