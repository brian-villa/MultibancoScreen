package controller;

import model.ContaBancaria;
import view.TelaCaixaEletronico;
import view.TelaTransferencia;

import javax.swing.*;

public class TelaTransferenciaController {
    private ContaBancaria conta;
    private TelaTransferencia telaTransferencia;

    public TelaTransferenciaController(ContaBancaria conta) {
        this.conta = conta;
        this.telaTransferencia = new TelaTransferencia(this);
    }

    public void realizarTransferencia(double valor) {
        String contaDestino = JOptionPane.showInputDialog("Digite o número da conta de destino:");

        if (contaDestino == null || contaDestino.isEmpty()) {
            JOptionPane.showMessageDialog(telaTransferencia, "Operação cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (conta.getNumeroConta() == Integer.parseInt(contaDestino)) {
            JOptionPane.showMessageDialog(telaTransferencia, "Não é possível transferir para a mesma conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (conta.getSaldo() < valor) {
            JOptionPane.showMessageDialog(telaTransferencia, "Saldo insuficiente", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            conta.transferir(valor, Integer.parseInt(contaDestino));
            JOptionPane.showMessageDialog(telaTransferencia, "Transferência de € " + valor + " para a conta " + contaDestino + " realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(telaTransferencia, "Erro ao realizar transferência.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void tratarTransferenciaOutroValor(String input) {
        try {
            double valor = Double.parseDouble(input);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(telaTransferencia, "O valor deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            realizarTransferencia(valor);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(telaTransferencia, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void voltar() {
        telaTransferencia.dispose(); // Fecha a tela atual
        new TelaCaixaEletronico(conta); // Volta para a tela principal
    }
}
