import java.time.LocalDate;

public class Movimentacoes {
    private LocalDate data;
    private String tipoOperacao;
    private double valor;

    public Movimentacoes(LocalDate data, String tipoOperacao, double valor) {
        this.data = data;
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
    }

    //getters
    public LocalDate getData() {
        return data;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return data + "|" + tipoOperacao + "|" + String.format("%.2f", valor);
    }
}
