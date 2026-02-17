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
        int linhasComErros = 0;

        try( BufferedReader br = new BufferedReader( new FileReader( caminho ) ) ) {
            String linha;
            br.readLine();
            System.out.println( "Lendo arquivo .... \n");

            while ( (linha = br.readLine() ) != null ) {
                numeroLinhas++;
                System.out.println( "Linha " + numeroLinhas + " : " + linha );

                try{
                    String[] campos =  linha.split(",");

                    if( campos.length < 6 ) {
                        throw new IllegalArgumentException( "Campos insuficientes!" + campos.length );
                    }

                    String dataTexto = campos[5];
                    System.out.println( "Data como texto: " + dataTexto );

                    LocalDateTime dataHora = LocalDateTime.parse( dataTexto );

                    if(dataHora == null ) {
                        linhasComErros++;
                        System.out.println( " x Linha ignorada: data inválida." );
                        System.out.println( "-".repeat( 40 ) );
                        continue;
                    }

                    Transacao t = new Transacao(
                            campos[0].trim(),
                            campos[1].trim(),
                            campos[2].trim(),
                            campos[3].trim(),
                            campos[4].trim(),
                            dataHora,
                            new BigDecimal(500.00)
                    );

                    transacoes.add(t);
                    System.out.println( "Transação criada para: " + t.getTitular() );

                } catch ( Exception e ) {
                    linhasComErros++;
                    System.out.println( " x Erro na linha " + numeroLinhas + ":" + e.getMessage() );
                }

                System.out.println( "-".repeat( 40 ) );
            }
        } catch( IOException e ) {
            System.out.println( "x Erro: " + e.getMessage() );
        }

        System.out.println( "\n Total: " + transacoes.size() + " transações lidas." );
        return transacoes;
    }
}
