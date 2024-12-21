package model;

import dao.LoginDAO;
import dao.MovimentacoesDAO;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RelatorioBancario {

    public static void gerarRelatorioParaConta(ContaBancaria conta) {
        MovimentacoesDAO movimentacoesDAO = new MovimentacoesDAO();
        LoginDAO loginDAO = new LoginDAO();

        try {
            // Busca movimentações da conta logada
            List<Movimentacoes> movimentacoes = movimentacoesDAO.listarMovimentacoes(conta.getNumeroConta());
            List<String> historicoLogins = loginDAO.obterHistoricoLogins(conta.getNumeroConta());

            // Calcula o saldo atual
            double saldoAtual = calcularSaldoAtual(movimentacoes);

            // Gera o relatório
            gerarRelatorio(movimentacoes, conta, historicoLogins);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar movimentações no Banco de Dados");
        }
    }

    private static double calcularSaldoAtual(List<Movimentacoes> movimentacoes) {
        double saldo = 0.0;
        for (Movimentacoes mov : movimentacoes) {
            if ("DEPOSITO".equalsIgnoreCase(mov.getTipoOperacao())) {
                saldo += mov.getValor();
            } else if ("SACAR".equalsIgnoreCase(mov.getTipoOperacao())) {
                saldo -= mov.getValor();
            }
        }
        return saldo;
    }

    private static void gerarRelatorio(List<Movimentacoes> movimentacoes, ContaBancaria conta, List<String> historicoLogins) {
        String nomeArquivo = "extrato_bancario.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("RELATÓRIO DE EXTRATO BANCÁRIO");
            writer.newLine();
            writer.write("=============================");
            writer.newLine();
            writer.newLine();

            // Escreve as movimentações no relatório
            writer.write("Movimentações:");
            writer.newLine();
            for (Movimentacoes mov : movimentacoes) {
                writer.write(mov.toString());
                writer.newLine();
            }

            writer.newLine();
            writer.write("=============================");
            writer.newLine();
            writer.write(String.format("Saldo Atual: €%.2f", conta.getSaldo()));
            writer.newLine();

            // Escreve o histórico de logins no relatório
            writer.newLine();
            writer.write("Histórico de Logins:");
            writer.newLine();
            if (historicoLogins.isEmpty()) {
                writer.write("Nenhum login registrado.");
            } else {
                for (String login : historicoLogins) {
                    writer.write(login);
                    writer.newLine();
                }
            }

            writer.write("=============================");
            writer.newLine();

            System.out.println("Relatório gerado com sucesso no arquivo: " + nomeArquivo);
            JOptionPane.showMessageDialog(null, "Relatório salvo em " + nomeArquivo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar o relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
