package dao;

import model.ContaBancaria;
import util.ConexaoBancoDados;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    public void registrarLogin(ContaBancaria conta) throws SQLException {
        //inserir o login no banco de dados
        String sql = "INSERT INTO historico_logins (numeroConta, dataHoraLogin) VALUES (?, ?)";

        // Estabelecendo a conexão com o banco de dados
        try (Connection connection = ConexaoBancoDados.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, conta.getNumeroConta());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao registrar login no histórico.", e);
        }
    }

    public List<String> obterHistoricoLogins(int numeroConta) {
        List<String> historico = new ArrayList<>();

        String sql = "SELECT dataHoraLogin FROM historico_logins WHERE numeroConta = ? ORDER BY dataHoraLogin DESC";

        try (Connection connection = ConexaoBancoDados.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numeroConta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Timestamp dataHora = rs.getTimestamp("dataHoraLogin");

                    if (dataHora != null) {
                        // Converte para LocalDateTime
                        LocalDateTime localDateTime = dataHora.toLocalDateTime();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        String formattedDate = localDateTime.format(formatter);

                        // Adiciona o histórico com a data formatada
                        historico.add(formattedDate);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar histórico de logins.");
        }

        return historico;
    }
}
