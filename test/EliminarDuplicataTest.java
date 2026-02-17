import model.Transacao;
import service.EliminarDuplicata;
import service.LeitorCSV;

import java.util.List;

public class EliminarDuplicataTest {

    public static void main(String[] args) {

        System.out.println( "TESTE ELIMINAÇÃO DE DUPLICATAS!" );
        System.out.println( "=================================\n");

        LeitorCSV leitor = new LeitorCSV();
        List<Transacao> lista = leitor.lerArquivo( "data/operacoes.csv" );

        System.out.println( "\n Lista: " + lista.size() );
        for( int i = 0; i < lista.size(); i++ ) {
            Transacao t = lista.get( i );
            System.out.printf( "%d.%s | %s | %s | R$ %.2f\n",
                    ( i + 1 ),
                    t.getTitular(),
                    t.getTipoOperacao(),
                    t.getDataHora(),
                    t.getValor()
                    );
        }

        EliminarDuplicata eliminar = new EliminarDuplicata();
        List<Transacao> listaSemDuplicatas = eliminar.eliminarDuplicates( lista );

        System.out.println( "LISTA SEM DUPLICATAS: " );
        for( int i = 0; i < listaSemDuplicatas.size(); i++ ) {
            Transacao t = listaSemDuplicatas.get( i );
            System.out.printf( "%d. %s | %s | %s | R$ %.2f\n",
                    ( i + 1 ),
                    t.getTitular(),
                    t.getTipoOperacao(),
                    t.getDataHora(),
                    t.getValor()
                    );
        }

    }
}

