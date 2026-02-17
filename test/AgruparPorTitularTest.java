import model.Transacao;

import service.AgruparPorTitular;
import service.EliminarDuplicata;
import service.LeitorCSV;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class AgruparPorTitularTest {

    public static void main(String[] args) {

        System.out.println( "TESTE DE AGRUPAMENTO POR TITULAR" );
        System.out.println( "================================" );

        // Ler transações
        LeitorCSV leitor = new LeitorCSV();
        List<Transacao> lista =  leitor.lerArquivo( "data/operacoes.csv" );

        //Eliminar Duplicatas
        EliminarDuplicata eliminar = new EliminarDuplicata();
        List<Transacao> listaUnica = eliminar.eliminarDuplicates( lista );

        System.out.println( "\n Total de transações únicas: " + listaUnica.size() );

        // Agrupar por Titular
        AgruparPorTitular agrupar = new AgruparPorTitular();
        Map<String, List<Transacao>> grupos = agrupar.agruparPorTitular( lista );

        // Mostrar resultados
        System.out.println( "\n GRUPOS POR TITULAR: ");
        System.out.println( "=".repeat( 60 ) );

        for ( String titular : grupos.keySet() ) {
            List<Transacao> transacoesPorTitular = grupos.get( titular );

            System.out.printf( "\n %s (%d transações):\n", titular, transacoesPorTitular.size() );
            System.out.println( "-".repeat( 40 ) );

            for( int i = 0; i < transacoesPorTitular.size(); i++ ) {
                Transacao t = transacoesPorTitular.get( i );
                System.out.printf( " %d. %s | %s | R$ %.2f | %s\n",
                        ( i + 1),
                        t.getTipoOperacao(),
                        t.getDataHora(),
                        t.getValor(),
                        t.getConta()
                );
            }
        }

        // Estatísticas
        System.out.println( "\n ESTATÍSTICAS: ");
        System.out.println( "=".repeat( 40 ) );
        System.out.println( "Total de titulares: " + grupos.size() );

        int totalTransacoes = 0;
        for (List<Transacao> listarTitular : grupos.values() ) {
            totalTransacoes += listarTitular.size();
        }
        System.out.println( "Total transações: " + totalTransacoes );

        System.out.println( "\n Média por Titular: ");
        for(String titular : grupos.keySet() ) {
            int quantidade = grupos.get( titular ).size();
            System.out.printf( " %s. %d transações\n", titular, quantidade );
        }
    }


}
