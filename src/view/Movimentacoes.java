package view;

import model.ContaBancaria;

import java.time.LocalDate;
import java.util.UUID;

public class Movimentacoes {
    private UUID uuid;
    private LocalDate data;
    private String tipoOperacao;
    private double valor;
    private ContaBancaria contaBancaria;

    public Movimentacoes(UUID uuid, LocalDate data, String tipoOperacao, double valor) {
        this.uuid = uuid;
        this.data = data;
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
    }

    //setters
    public void setId(UUID uuid) {
        this.uuid = uuid;
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

    public UUID getUuid() { return uuid; }

    @Override
    public String toString() {
        return data + "|" + tipoOperacao + "|" + String.format("%.2f", valor);
    }
}
