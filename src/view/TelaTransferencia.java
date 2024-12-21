package view;

import model.ContaBancaria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaTransferencia extends JFrame implements ActionListener {
    private ContaBancaria conta;
    private JButton btnTransferir10, btnTransferir20, btnTransferir50, btnTransferir100, btnOutroValor, btnVoltar;

    public TelaTransferencia(ContaBancaria conta) {
        this.conta = conta;

        //Configuração janela
        setTitle("Transferência Multibanco");
        setSize(800,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); //Centraliza janela na tela
        getContentPane().setBackground(Color.white);

        //label para o título
        JLabel lblTitulo = new JLabel("Escolha o valor da transferência", JLabel.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(lblTitulo, BorderLayout.NORTH);

        //Painel central com os botões
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(3, 2, 20,20));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50,100,50,100));

        //Botões
        btnTransferir10 = new JButton("€ 10");
        btnTransferir10.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTransferir10.addActionListener(this);
        painelCentral.add(btnTransferir10);

        btnTransferir20 = new JButton("€ 20");
        btnTransferir20.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTransferir20.addActionListener(this);
        painelCentral.add(btnTransferir20);

        btnTransferir50 = new JButton("€ 50");
        btnTransferir50.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTransferir50.addActionListener(this);
        painelCentral.add(btnTransferir50);

        btnTransferir100 = new JButton("€ 100");
        btnTransferir100.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnTransferir100.addActionListener(this);
        painelCentral.add(btnTransferir100);

        btnOutroValor = new JButton("Outro valor");
        btnOutroValor.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnOutroValor.addActionListener(this);
        painelCentral.add(btnOutroValor);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnVoltar.addActionListener(this);
        painelCentral.add(btnVoltar);

        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTransferir10) {
            try {
                realizarTransferencia(10);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnTransferir20) {
            try {
                realizarTransferencia(20);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnTransferir50) {
            try {
                realizarTransferencia(50);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnTransferir100) {
            try {
                realizarTransferencia(100);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnOutroValor) {
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor a Transferir:"));
            try {
                realizarTransferencia(valor);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == btnVoltar) {
            dispose();
            new TelaCaixaEletronico(conta);
        }
    }

    public void realizarTransferencia(double valor) throws SQLException {
        String contaDestino = JOptionPane.showInputDialog("Digite o número da conta de destino");

        if(contaDestino == null || contaDestino.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Operação cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
        }
        // Verifica se a conta de destino é a mesma da conta de origem
        if (conta.getNumeroConta() == Integer.parseInt(contaDestino)) {
            JOptionPane.showMessageDialog(this, "Não é possível transferir para a mesma conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;  // Interrompe a transferência se as contas forem iguais
        }

        if(conta.getSaldo() < valor) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        assert contaDestino != null;
        conta.transferir(valor, Integer.parseInt(contaDestino));
        JOptionPane.showMessageDialog(this, "Transferência de € " + valor + " para a conta " + contaDestino + " realizada com sucesso!",  "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

}
