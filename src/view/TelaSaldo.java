package view;

import controller.TelaSaldoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSaldo extends JFrame implements ActionListener {
    private TelaSaldoController controller;
    private JButton btnVoltar;
    private JLabel lblSaldoValor;

    public TelaSaldo(TelaSaldoController controller, double saldo) {
        this.controller = controller;

        // Configuração da janela
        setTitle("Saldo Multibanco");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Centraliza a janela na tela
        getContentPane().setBackground(Color.white);

        // Label para o título
        JLabel lblSaldoTitulo = new JLabel("Saldo Atual:", JLabel.CENTER);
        lblSaldoTitulo.setFont(new Font("Tahoma", Font.BOLD, 38));
        add(lblSaldoTitulo, BorderLayout.NORTH);

        // Painel central para o saldo
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(2, 1, 20, 20)); // 2 linhas, 1 coluna, espaçamento de 20px
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Margens
        painelCentral.setBackground(Color.white);

        // Label para mostrar o saldo
        lblSaldoValor = new JLabel("€ " + String.format("%.2f", saldo), JLabel.CENTER);
        lblSaldoValor.setFont(new Font("Tahoma", Font.PLAIN, 32));
        painelCentral.add(lblSaldoValor);

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnVoltar.addActionListener(this);
        painelCentral.add(btnVoltar);

        // Adiciona o painel central ao centro da janela
        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVoltar) {
            controller.voltar();
        }
    }

    // Atualiza o saldo exibido
    public void atualizarSaldo(double saldo) {
        lblSaldoValor.setText("€ " + String.format("%.2f", saldo));
    }
}
