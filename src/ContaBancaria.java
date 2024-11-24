import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContaBancaria {
    private int numeroConta;
    private String titular;
    private double saldo;


    //Construtor
    public ContaBancaria(int numeroConta, String titular, double saldo) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
    }

    //Metodos para interagir com a conta
    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if(saldo >= valor) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    //Metodo principal para criar a interface
    public static void main(String[] args) {
        JFrame frame = new JFrame("ContaBancaria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Componentes da interface
        JLabel lblNumeroConta = new JLabel("Numero da Conta:");
        JTextField txtNumeroConta = new JTextField(10);
        JLabel lblTitular = new JLabel("Titular:");
        JTextField txtTitular = new JTextField(20);
        JLabel lblSaldo = new JLabel("Saldo:");
        JTextField txtSaldo = new JTextField(10);
        txtSaldo.setEditable(false); // Campo de saldo não editável
        JButton btnDepositar = new JButton("Depositar");
        JButton btnSacar = new JButton("Sacar");

        //criando a conta
        ContaBancaria conta = new ContaBancaria(12345, "João da Silva", 1000);

        //Atualizando o saldo na interface
        txtSaldo.setText(String.valueOf(conta.getSaldo()));

        //ActionListener para o botao depositar
        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do deposito: "));
                conta.depositar(valor);
                txtSaldo.setText(String.valueOf(conta.getSaldo()));
            }
        });

        //Action Listener para sacar
        btnSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque: "));
                conta.sacar(valor);
                txtSaldo.setText(String.valueOf(conta.getSaldo()));
            }
        });

        //Adicionando os componentes ao frame
        frame.pack();
        frame.setVisible(true);
    }
}
