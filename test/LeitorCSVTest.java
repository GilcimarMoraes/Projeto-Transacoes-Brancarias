import model.Transacao;
import service.LeitorCSV;

import java.util.List;

public class LeitorCSVTest {

    public static void main(String[] args) {

        LeitorCSV leitorCSV = new LeitorCSV();

        List<Transacao> lista = leitorCSV.lerArquivo( "data/operacoes.csv" );

        System.out.println( "\n Total lido: " + lista.size() );

        for( Transacao t : lista ) {
            System.out.println( " " + t );
        }
    }
}
