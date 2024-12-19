package view;

import dao.MovimentacoesDAO;
import util.ConexaoBancoDados;

import javax.sql.DataSource;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class RelatorioBancario {

    public static void main(String[] args) {
        MovimentacoesDAO movimentacoesDAO = new MovimentacoesDAO();

        try {
            List<Movimentacoes> movimentacoes = movimentacoesDAO.listarMovimentacoes();

            double saldoAtual = calcularSaldoAtual(movimentacoes);

            gerarRelatorio(movimentacoes, saldoAtual);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar movimentações no Banco de Dados");
        }
    }

    //Método para calcular o saldo atual baseado nas movimentações
    private static double calcularSaldoAtual(List<Movimentacoes> movimentacoes) {
        double saldo = 0.0;
        for(Movimentacoes mov : movimentacoes) {
            if("DEPOSITO".equalsIgnoreCase(mov.getTipoOperacao())) {
                saldo += mov.getValor();
            } else if ("SACAR".equalsIgnoreCase(mov.getTipoOperacao())) {
                saldo -= mov.getValor();
            }
        }
        return saldo;
    }

    // Método para gerar o relatório
    static void gerarRelatorio(List<Movimentacoes> movimentacoes, double saldoAtual) {
        String nomeArquivo = "extrato_bancario.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Escreve o cabeçalho do relatório
            writer.write("RELATÓRIO DE EXTRATO BANCÁRIO");
            writer.newLine();
            writer.write("=============================");
            writer.newLine();
            writer.newLine();

            // Escreve as movimentações no arquivo
            for (Movimentacoes mov : movimentacoes) {
                writer.write(mov.toString());
                writer.newLine();
            }

            writer.write("=============================");
            writer.newLine();
            writer.write(String.format("Saldo Atual: €%.2f", saldoAtual));
            writer.newLine();

            // Mensagem de sucesso
            System.out.println("Relatório gerado com sucesso no arquivo: " + nomeArquivo);
            JOptionPane.showMessageDialog(null, "Relatório salvo em " + nomeArquivo, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar o relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
