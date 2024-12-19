package model;

import dao.ContaBancariaDAO;
import dao.MovimentacoesDAO;
import view.Movimentacoes;

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
    private final String senha; // Agora você tem a senha em vez de pin

    // Construtor
    public ContaBancaria(int numeroConta, String titular, double saldo, String senha) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
        this.senha = senha; // Armazena a senha da conta
        this.movimentacoes = new ArrayList<>();
        this.movimentacoesDAO = new MovimentacoesDAO();

        carregarMovimentacoes();
    }

    // Método para autenticar a conta com a senha
    public boolean autenticar(String senhaInput) {
        return this.senha.equals(senhaInput);
    }

    // Metodos para interagir com a conta
    public void depositar(double valor) {
        saldo += valor;
        movimentacoes.add(new Movimentacoes(UUID.randomUUID(), LocalDate.now(), "Depósito", valor));
    }

    public void sacar(double valor) {
        if(saldo >= valor) {
            saldo -= valor;
            movimentacoes.add(new Movimentacoes(UUID.randomUUID(), LocalDate.now(), "Saque", -valor));
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    public ArrayList<Movimentacoes> getMovimentacoes() {
        return movimentacoes;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public int getNumeroConta() { return numeroConta; }

    // Método para carregar as movimentações do banco de dados
    private void carregarMovimentacoes() {
        try {
            List<Movimentacoes> mov = movimentacoesDAO.listarMovimentacoes();
            movimentacoes.addAll(mov);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar movimentações do banco de dados");
        }
    }

    // Método principal para criar a interface
    public static void main(String[] args) {
        JFrame frame = new JFrame("model.ContaBancaria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Criando a conta
        ContaBancaria conta = new ContaBancaria(12345, "João da Silva", 1000, "1234");

        // Adicionando os componentes ao frame
        frame.pack();
        frame.setVisible(true);
    }
}
