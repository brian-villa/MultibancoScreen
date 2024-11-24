import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSaldo extends JFrame implements ActionListener {
    private ContaBancaria conta;
    private JButton btnVoltar;

    public TelaSaldo(ContaBancaria conta) {
        this.conta = conta;

        // Configuração da janela
        setTitle("Saldo Multibanco");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        getContentPane().setBackground(Color.white);

        // Label para o título
        JLabel lblSaldoTitulo = new JLabel("Saldo Atual:");
        lblSaldoTitulo.setFont(new Font("Tahoma", Font.BOLD, 38));
        lblSaldoTitulo.setBounds(300, 40, 400, 40);
        add(lblSaldoTitulo);

        // Label para mostrar o saldo
        JLabel lblSaldoValor = new JLabel("€ " + String.format("%.2f", conta.getSaldo()));
        lblSaldoValor.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblSaldoValor.setBounds(320, 150, 400, 40);
        add(lblSaldoValor);

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(325, 300, 150, 30);
        btnVoltar.addActionListener(this);
        add(btnVoltar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVoltar) {
            dispose(); // Fecha a tela atual
            new TelaCaixaEletronico(conta); // Volta para a tela principal
        }
    }
}
