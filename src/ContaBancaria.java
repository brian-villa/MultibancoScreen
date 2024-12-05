import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContaBancaria {
    private int numeroConta;
    private String titular;
    private double saldo;
    private ArrayList<Movimentacoes> movimentacoes;


    //Construtor
    public ContaBancaria(int numeroConta, String titular, double saldo) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldo;
        this.movimentacoes = new ArrayList<>();
    }

    //Metodos para interagir com a conta
    public void depositar(double valor) {
        saldo += valor;
        movimentacoes.add(new Movimentacoes(LocalDate.now(), "Depósito", valor));
    }

    public void sacar(double valor) {
        if(saldo >= valor) {
            saldo -= valor;
            movimentacoes.add(new Movimentacoes(LocalDate.now(), "Saque", -valor));
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

    //Metodo principal para criar a interface
    public static void main(String[] args) {
        JFrame frame = new JFrame("ContaBancaria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //criando a conta
        ContaBancaria conta = new ContaBancaria(12345, "João da Silva", 1000);

        //Adicionando os componentes ao frame
        frame.pack();
        frame.setVisible(true);
    }
}
