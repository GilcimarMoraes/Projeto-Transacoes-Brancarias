import model.Transacao;
import service.CalcularSaldo;
import service.LeitorCSV;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class CalcularSaldoTest {

    public static void main(String[] args) {

        System.out.println( "TESTE DO CALCULADOR DE SALDO" );
        System.out.println( "=============================" );

        LeitorCSV leitor = new LeitorCSV();
        List<Transacao> lista = leitor.lerArquivo( "data/operacoes.csv" );

        System.out.println( "Transações lidas: " + lista.size() + "\n" );

        CalcularSaldo calc = new CalcularSaldo();
        Map<String, BigDecimal> saldo = calc.calcularSaldo( lista );

        System.out.println( "SALDOS POR TITULAR" );
        System.out.println( "-".repeat(40));

        for( Map.Entry<String, BigDecimal> entry : saldo.entrySet() ) {
            System.out.printf( "%-10s: R$ %10.2f\n",
                    entry.getKey(),
                    entry.getValue()
            );

            System.out.println( "-".repeat(40) );
            System.out.println( "Teste realizado com sucesso!" );
        }
    }


}
