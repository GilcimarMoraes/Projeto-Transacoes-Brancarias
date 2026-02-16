package service;

import model.Transacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgruparPorTitular {

    public Map<String, List<Transacao>> agruparPorTitular( List<Transacao> transacoes ) {
        Map<String, List<Transacao>> grupos =  new HashMap<>();
        for ( Transacao t : transacoes ) {
            String titular = t.getTitular();

            if( !grupos.containsKey(titular) ) {
                grupos.put( titular, new ArrayList<>() );
            }
            grupos.get( titular ).add( t );
        }
        return grupos;
    }
}
