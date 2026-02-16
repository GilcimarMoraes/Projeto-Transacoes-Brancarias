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
        int numeroLinhas = 0;

        try( BufferedReader br = new BufferedReader( new FileReader( caminho ) ) ) {
            String linha;
            br.readLine();
            System.out.println( "Lendo arquivo .... \n");

            while ( (linha = br.readLine() ) != null ) {
                numeroLinhas++;
                System.out.println( "Linha " + numeroLinhas + " : " + linha );

                String[] campos =  linha.split(",");
                String dataTexto = campos[5];
                System.out.println( "Data como texto: " + dataTexto );

                LocalDateTime dataHora = LocalDateTime.parse( dataTexto );
                System.out.println( "Data convertida: " + dataHora );

                Transacao t = new Transacao(
                        campos[0],
                        campos[1],
                        campos[2],
                        campos[3],
                        campos[4],
                        dataHora,
                        new BigDecimal(500.00)
                );

                transacoes.add(t);
                System.out.println( "Transação criada para: " + t.getTitular() );
                System.out.println( "-------------------------------------------" );
            }
        } catch( IOException e ) {
            System.out.println( "x Erro: " + e.getMessage() );
        }

        System.out.println( "\n Total: " + transacoes.size() + "transações lidas." );
        return transacoes;
    }
}
