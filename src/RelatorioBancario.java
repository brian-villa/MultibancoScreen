import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class RelatorioBancario {

    public static void main(String[] args) {
        // Exemplo de lista de movimentações
        ArrayList<Movimentacoes> movimentacoes = new ArrayList<>();

        // Chamando o método para gerar relatório
        double saldoAtual = 150.00; // Exemplo de saldo
        gerarRelatorio(movimentacoes, saldoAtual);
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
