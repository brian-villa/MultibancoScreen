package view;

import controller.TelaTransferenciaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTransferencia extends JFrame implements ActionListener {
    private TelaTransferenciaController controller;
    private JButton btnTransferir10, btnTransferir20, btnTransferir50, btnTransferir100, btnOutroValor, btnVoltar;

    public TelaTransferencia(TelaTransferenciaController controller) {
        this.controller = controller;

        // Configuração da janela
        setTitle("Transferência Multibanco");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Centraliza janela na tela
        getContentPane().setBackground(Color.white);

        // Label para o título
        JLabel lblTitulo = new JLabel("Escolha o valor da transferência", JLabel.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel central com os botões
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(3, 2, 20, 20));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // Botões
        btnTransferir10 = criarBotao("€ 10", painelCentral);
        btnTransferir20 = criarBotao("€ 20", painelCentral);
        btnTransferir50 = criarBotao("€ 50", painelCentral);
        btnTransferir100 = criarBotao("€ 100", painelCentral);
        btnOutroValor = criarBotao("Outro valor", painelCentral);
        btnVoltar = criarBotao("Voltar", painelCentral);

        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton criarBotao(String texto, JPanel painel) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Tahoma", Font.BOLD, 18));
        botao.addActionListener(this);
        painel.add(botao);
        return botao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTransferir10) {
            controller.realizarTransferencia(10);
        } else if (e.getSource() == btnTransferir20) {
            controller.realizarTransferencia(20);
        } else if (e.getSource() == btnTransferir50) {
            controller.realizarTransferencia(50);
        } else if (e.getSource() == btnTransferir100) {
            controller.realizarTransferencia(100);
        } else if (e.getSource() == btnOutroValor) {
            String input = JOptionPane.showInputDialog("Digite o valor a Transferir:");
            controller.tratarTransferenciaOutroValor(input);
        } else if (e.getSource() == btnVoltar) {
            controller.voltar();
        }
    }
}
