package controller;

import model.ContaBancaria;
import view.TelaCaixaEletronico;
import view.TelaSaldo;

public class TelaSaldoController {
    private ContaBancaria conta;
    private TelaSaldo telaSaldo;

    public TelaSaldoController(ContaBancaria conta) {
        this.conta = conta;
        this.telaSaldo = new TelaSaldo(this, conta.getSaldo());
    }

    public void voltar() {
        telaSaldo.dispose(); // Fecha a tela de saldo
        new TelaCaixaEletronico(conta); // Abre a tela principal
    }

    // Método para atualizar o saldo dinamicamente (se necessário)
    public void atualizarSaldo() {
        double saldoAtual = conta.getSaldo();
        telaSaldo.atualizarSaldo(saldoAtual);
    }
}
