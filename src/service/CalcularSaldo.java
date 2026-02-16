package service;

import model.Transacao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcularSaldo {
    public Map<String, BigDecimal> calcularSaldo(List<Transacao> transacoes) {
        Map<String, BigDecimal> saldo = new HashMap<>();

        for( Transacao t : transacoes ) {
            String titular = t.getTitular();
            BigDecimal saldoAtual = saldo.getOrDefault(titular, BigDecimal.ZERO);

            if( t.getTipoOperacao().equals( "Deposito" ) ) {
                saldoAtual = saldoAtual.add(t.getValor());
            } else if( t.getTipoOperacao().equals( "Saque" ) ) {
                saldoAtual = saldoAtual.subtract(t.getValor());
            }

            saldo.put(titular, saldoAtual);
        }

        return saldo;
    };
}
