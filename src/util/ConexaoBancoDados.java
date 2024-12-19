package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoBancoDados {
    private static final String URL = System.getenv("DB_URL");
    private static final String USUARIO = System.getenv("LOGIN_DB");
    private static final String SENHA = System.getenv("PWD_DB");

    public static Connection conectar() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado com sucesso!");
            return conexao;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        return null;
    };
}
