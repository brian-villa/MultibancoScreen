package controller;

import model.ContaBancaria;
import view.TelaCaixaEletronico;
import view.TelaSacar;

import javax.swing.*;

public class TelaSacarController {
    private ContaBancaria conta;
    private TelaSacar telaSacar;

    public TelaSacarController(ContaBancaria conta) {
        this.conta = conta;
        this.telaSacar = new TelaSacar(this);
    }

    public void sacar(double valor) {
        if (conta.getSaldo() >= valor) {
            conta.sacar(valor);
            JOptionPane.showMessageDialog(telaSacar, "Saque de € " + valor + " realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(telaSacar, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sacarOutroValor() {
        try {
            String valorInput = JOptionPane.showInputDialog(telaSacar, "Digite o valor a sacar:");
            if (valorInput != null) {
                double valor = Double.parseDouble(valorInput);
                sacar(valor);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(telaSacar, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void voltar() {
        new TelaCaixaEletronico(conta); // Retorna para a tela principal
    }
}
