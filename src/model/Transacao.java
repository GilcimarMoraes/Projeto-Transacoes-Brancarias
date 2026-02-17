package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public String getConta() { return conta; }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(
                agencia, transacao.agencia) &&
                Objects.equals(conta, transacao.conta) &&
                Objects.equals(banco, transacao.banco) &&
                Objects.equals(titular, transacao.titular) &&
                Objects.equals(tipoOperacao, transacao.tipoOperacao) &&
                Objects.equals(dataHora, transacao.dataHora) &&
                Objects.equals(valor, transacao.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, conta, banco, titular, tipoOperacao, dataHora, valor);
    }
}
