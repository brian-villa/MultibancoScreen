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

        getContentPane().setBackground(Color.white);
        setLocation(350, 200);
        setVisible(true);

        //Tela principal
        JLabel text = new JLabel("Selecione a sua Transação:", JLabel.CENTER);
        text.setBounds(235,30, 350, 35);
        text.setFont(new Font("System", Font.BOLD, 20));
        add(text);

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

        //componentes SALDO
        JLabel lblSaldo = new JLabel("Saldo: ");
        lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblSaldo.setBounds(130 ,100 , 90, 20);
        add(lblSaldo);

        txtSaldo = new JTextField(20);
        txtSaldo.setBounds(50, 100, 150, 30);
        txtSaldo.setFont(new Font("Tahoma", Font.PLAIN, 22));
        txtSaldo.setEditable(false);
        add(txtSaldo);

        setVisible(true);
    }

    private void atualizarSaldo() {
        txtSaldo.setText(String.format("€ %.2f", conta.getSaldo()));
    }

    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(1245, "Joao", 100);
        new TelaCaixaEletronico(conta);
    }

    //Evento dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSair) {
            System.exit(0);
        } else if(e.getSource() == btnSaldo) {
            atualizarSaldo();
        } else if(e.getSource() == btnDepositar) {
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a depositar:"));
            conta.depositar(valor);
            atualizarSaldo();
        } else if (e.getSource() == btnSacar) {
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a ser sacado:"));
            conta.sacar(valor);
            atualizarSaldo();
        }
    }
}
