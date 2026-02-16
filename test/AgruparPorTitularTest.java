import model.Transacao;

import service.AgruparPorTitular;
import service.LeitorCSV;

import java.util.List;
import java.util.Map;

public class AgruparPorTitularTest {

    public static void main(String[] args) {
        LeitorCSV leitor = new LeitorCSV();
        List<Transacao> lista =  leitor.lerArquivo( "data/operacoes.csv" );

        AgruparPorTitular agrupar = new AgruparPorTitular();
        Map<String, List<Transacao>> grupos = agrupar.agruparPorTitular( lista );

        System.out.println( "\n Grupos: ");
        for ( String titular : grupos.keySet() ) {
            System.out.println( titular + ": " + grupos.get( titular ).size() + " transações.");
        }
    }


}
