package model;

import dao.ContaBancariaDAO;
import dao.MovimentacoesDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContaBancaria {
    private final int numeroConta;
    private final String titular;
    private double saldo;
    private final ArrayList<Movimentacoes> movimentacoes;
    private final MovimentacoesDAO movimentacoesDAO;
    private final ContaBancariaDAO contaBancariaDAO;
    private final String senha;

    // Construtor
    public ContaBancaria(int numeroConta, String titular, double saldo, String senha) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
        this.senha = senha; // Armazena a senha da conta
        this.movimentacoes = new ArrayList<>();
        this.movimentacoesDAO = new MovimentacoesDAO();
        this.contaBancariaDAO = new ContaBancariaDAO();

        carregarMovimentacoes();
    }

    //getters

    public ArrayList<Movimentacoes> getMovimentacoes() { return movimentacoes; }

    public double getSaldo() { return saldo; }

    public String getTitular() { return titular; }

    public int getNumeroConta() { return numeroConta; }

    public String getSenha() { return senha; }

    // Método para autenticar a conta com a senha
    public boolean autenticar(String senhaInput) {
        return this.senha.equals(senhaInput);
    }

    // Metodos para interagir com a conta

    public void depositar(double valor) {
        saldo += valor;
        Movimentacoes mov = new Movimentacoes(UUID.randomUUID(), LocalDate.now(), "DEPOSITO", valor);
        movimentacoes.add(mov);

        try {
            movimentacoesDAO.inserirMovimentacoes(mov, this.numeroConta);
            contaBancariaDAO.atualizarSaldo(this);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar a movimentação no banco!");
        }
    }

    public void sacar(double valor) {
        if(saldo >= valor) {
            saldo -= valor;

            Movimentacoes mov = new Movimentacoes(UUID.randomUUID(), LocalDate.now(), "SACAR", -valor);
            movimentacoes.add(mov);

            try {
                movimentacoesDAO.inserirMovimentacoes(mov, this.numeroConta);
                contaBancariaDAO.atualizarSaldo(this);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao salvar a movimentação no banco!");
            }

        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public void transferir(double valor, int numeroContaDestino) throws SQLException {
        ContaBancariaDAO contaDestinoDAO = new ContaBancariaDAO();
        ContaBancaria contaDestino = contaDestinoDAO.carregarConta(numeroContaDestino);

        if (this.numeroConta == numeroContaDestino) {
            System.out.println("Não é possível transferir para a mesma conta.");
            JOptionPane.showMessageDialog(null, "Não é possível transferir para a mesma conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(saldo >= valor) {
            saldo -= valor;

            Movimentacoes mov = new Movimentacoes(UUID.randomUUID(), LocalDate.now(), "Transferência_Enviada" + " para: " + contaDestino.getTitular(), -valor);
            movimentacoes.add(mov);

            try {
                contaDestino.saldo += valor;

                movimentacoesDAO.inserirMovimentacoes(mov, this.numeroConta);

                //atualiza o BD
                contaBancariaDAO.atualizarSaldo(this);
                contaBancariaDAO.atualizarSaldo(contaDestino);

                Movimentacoes movRecebida = new Movimentacoes(UUID.randomUUID(), LocalDate.now(), "Transferência_Recebida" + " de " + this.getTitular(), valor);
                movimentacoesDAO.inserirMovimentacoes(movRecebida, numeroContaDestino);

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao realizar a transferência");
            }
        } else {
            System.out.println("Saldo insuficiente para a transferência");
        }
    }


    // Método para carregar as movimentações do banco de dados
    private void carregarMovimentacoes() {
        try {
            List<Movimentacoes> mov = movimentacoesDAO.listarMovimentacoes(this.numeroConta);
            movimentacoes.addAll(mov);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar movimentações do banco de dados");
        }
    }
}
