package service;

import model.Transacao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EliminarDuplicata {

    public List<Transacao> eliminarDuplicates(List<Transacao> transacoes) {
        Set<Transacao> set = new HashSet<Transacao>( transacoes );

        List<Transacao> listaSemDuplicata = new ArrayList<>( set );

        return listaSemDuplicata;
    }
}
