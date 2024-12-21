package view;

import dao.MovimentacoesDAO;
import model.ContaBancaria;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RelatorioBancario {

    public static void gerarRelatorioParaConta(ContaBancaria conta) {
        MovimentacoesDAO movimentacoesDAO = new MovimentacoesDAO();

        try {
            // Busca movimentações da conta logada
            List<Movimentacoes> movimentacoes = movimentacoesDAO.listarMovimentacoes(conta.getNumeroConta());

            // Calcula o saldo atual
            double saldoAtual = calcularSaldoAtual(movimentacoes);

            // Gera o relatório
            gerarRelatorio(movimentacoes, conta);

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

    private static void gerarRelatorio(List<Movimentacoes> movimentacoes, ContaBancaria conta) {
        String nomeArquivo = "extrato_bancario.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("RELATÓRIO DE EXTRATO BANCÁRIO");
            writer.newLine();
            writer.write("=============================");
            writer.newLine();
            writer.newLine();

            for (Movimentacoes mov : movimentacoes) {
                writer.write(mov.toString());
                writer.newLine();
            }

            writer.write("=============================");
            writer.newLine();
            writer.write(String.format("Saldo Atual: €%.2f", conta.getSaldo()));
            writer.newLine();

            System.out.println("Relatório gerado com sucesso no arquivo: " + nomeArquivo);
            JOptionPane.showMessageDialog(null, "Relatório salvo em " + nomeArquivo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar o relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
