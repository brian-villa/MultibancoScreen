package view;

import model.ContaBancaria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCaixaEletronico extends JFrame implements ActionListener {
    ContaBancaria conta;
    JButton btnDepositar, btnSacar, btnSaldo, btnSair, btnExtrato;

    public TelaCaixaEletronico(ContaBancaria conta) {
        this.conta = conta;

        // Configuração da janela
        setTitle("ATM Multibanco");
        setSize(800, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Usar BorderLayout para organizar as seções
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.white);

        // Painel superior com mensagem de boas-vindas
        JLabel lblBemVindo = new JLabel("Bem-vindo, " + conta.getTitular() + "!", JLabel.CENTER);
        lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(lblBemVindo, BorderLayout.NORTH);

        // Painel central com os botões
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(3, 2, 20, 20)); // 3 linhas, 2 colunas, espaçamento 20px
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Margens

        // Botões
        btnDepositar = new JButton("Depositar");
        btnDepositar.setFont(new Font("Tahoma", Font.PLAIN, 18)); // Define o tamanho da fonte do botão
        btnDepositar.addActionListener(this);
        painelCentral.add(btnDepositar);

        btnSacar = new JButton("Sacar");
        btnSacar.setFont(new Font("Tahoma", Font.PLAIN, 18)); // Define o tamanho da fonte do botão
        btnSacar.addActionListener(this);
        painelCentral.add(btnSacar);

        btnSaldo = new JButton("Saldo");
        btnSaldo.setFont(new Font("Tahoma", Font.PLAIN, 18)); // Define o tamanho da fonte do botão
        btnSaldo.addActionListener(this);
        painelCentral.add(btnSaldo);

        btnExtrato = new JButton("Gerar Extrato");
        btnExtrato.setFont(new Font("Tahoma", Font.PLAIN, 18)); // Define o tamanho da fonte do botão
        btnExtrato.addActionListener(this);
        painelCentral.add(btnExtrato);

        btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Tahoma", Font.PLAIN, 18)); // Define o tamanho da fonte do botão
        btnSair.addActionListener(this);
        painelCentral.add(btnSair);

        add(painelCentral, BorderLayout.CENTER);

        // Painel inferior com instruções
        JLabel text = new JLabel("Selecione a sua Transação:", JLabel.CENTER);
        text.setFont(new Font("System", Font.BOLD, 20));
        add(text, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Evento dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSair) {
            System.exit(0);

        } else if (e.getSource() == btnSaldo) {
            dispose();
            new TelaSaldo(conta).setVisible(true);

        } else if (e.getSource() == btnDepositar) {
            dispose();
            new TelaDepositar(conta).setVisible(true);

        } else if (e.getSource() == btnSacar) {
            dispose();
            new TelaSacar(conta).setVisible(true);

        } else if (e.getSource() == btnExtrato) {
            try {
                RelatorioBancario.gerarRelatorio(conta.getMovimentacoes(), conta.getSaldo());
                JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar o relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
