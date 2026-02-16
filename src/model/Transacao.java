package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {

    private String agencia;
    private String conta;
    private String banco;
    private String titular;
    private String tipoOperacao;
    private LocalDateTime dataHora;
    private BigDecimal valor;

    public Transacao( String agencia, String conta, String banco,
                      String titular, String tipoOperacao,
                      LocalDateTime dataHora, BigDecimal valor ) {
        this.agencia = agencia;
        this.conta = conta;
        this.banco = banco;
        this.titular = titular;
        this.tipoOperacao = tipoOperacao;
        this.dataHora = dataHora;
        this.valor = valor;
    }

    public String getTitular() {
        return titular;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return titular + " - " + tipoOperacao + " - R$ " + valor + " - " + dataHora;
    }
}
