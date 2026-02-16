package service;

import model.Transacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeitorCSV {
    public List<Transacao> lerArquivo(String caminho) {
        List<Transacao> transacoes = new ArrayList<>();

        try( BufferedReader br = new BufferedReader( new FileReader( caminho ) ) ) {
            String linha;
            br.readLine();

            while ( (linha = br.readLine() ) != null ) {
                String[] campos =  linha.split(",");

                Transacao t = new Transacao(
                        campos[0], campos[1], campos[2], campos[3],
                        campos[4], LocalDateTime.now(),
                        new BigDecimal(500.00)
                );

                transacoes.add(t);
                System.out.println( "Arquivo lido: " + t.getTitular() );
            }
        } catch( IOException e ) {
            System.out.println( "x Erro: " + e.getMessage() );
        }
        return transacoes;
    }
}
