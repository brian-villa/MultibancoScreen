package dao;

import model.ContaBancaria;
import util.ConexaoBancoDados;

import java.sql.*;

public class ContaBancariaDAO {

    // Método para carregar uma conta do banco de dados
    public ContaBancaria carregarConta(int numeroConta) throws SQLException {
        ContaBancaria conta = null;
        try (Connection connection = ConexaoBancoDados.conectar()) {
            String sql = "SELECT * FROM conta_bancaria WHERE numeroConta = ?";
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, numeroConta);
                System.out.println("Consultando número da conta: " + numeroConta);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String titular = rs.getString("titular");
                    double saldo = rs.getDouble("saldo");
                    String senha = rs.getString("senha");
                    conta = new ContaBancaria(numeroConta, titular, saldo, senha); // Cria a conta a partir dos dados do banco
                    System.out.println("Conta encontrada: " + conta.getTitular()); //
                } else {
                    System.out.println("Nenhuma conta encontrada para o número: " + numeroConta);
                }
            }
        }
        return conta;
    }

    // Método para criar uma nova conta no banco de dados
    public void criarConta(ContaBancaria conta) throws SQLException {
        try (Connection connection = ConexaoBancoDados.conectar()) {
            String sql = "INSERT INTO dbo.conta_bancaria (numeroConta, titular, saldo, senha) VALUES (?, ?, ?, ?)";
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, conta.getNumeroConta());
                stmt.setString(2, conta.getTitular());
                stmt.setDouble(3, 0.0);
                stmt.setString(4, conta.getSenha());
                stmt.executeUpdate();
            }
        }
    }

    // Método para atualizar o saldo da conta no banco de dados
    public void atualizarSaldo(ContaBancaria conta) throws SQLException {
        try (Connection connection = ConexaoBancoDados.conectar()) {
            String sql = "UPDATE dbo.conta_bancaria SET saldo = ? WHERE numeroConta = ?";
            assert connection != null;
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDouble(1, conta.getSaldo());
                stmt.setInt(2, conta.getNumeroConta());
                stmt.executeUpdate();
            }
        }
    }
}
