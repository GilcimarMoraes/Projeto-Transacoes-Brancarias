package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TratarErrosCSV {

    private static final List<DateTimeFormatter> FORMATOS = new ArrayList<>();
    static {
        FORMATOS.add( DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        FORMATOS.add( DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ) );
        FORMATOS.add( DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" ) );
        FORMATOS.add( DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ) );
        FORMATOS.add( DateTimeFormatter.ofPattern( "yyyy/MM/dd HH:mm:ss" ) );
        FORMATOS.add( DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss" ) );
        FORMATOS.add( DateTimeFormatter.ofPattern( "dd-MM-yyyy'T'HH:mm:ss" ) );
        FORMATOS.add( DateTimeFormatter.ofPattern( "dd/MM/yyyy'T'HH:mm:ss" ) );
        FORMATOS.add( DateTimeFormatter.ofPattern( "yyyy/MM/dd'T'HH:mm:ss" ) );
    }

    public static LocalDateTime tratarData( String dataTexto, int numeroLinha ) {
        System.out.println( "Trantando data: '"  + dataTexto + "'" );

        dataTexto = limparData( dataTexto );
        dataTexto = aplicarCorrecoes( dataTexto );

        System.out.println( "Após correções: '" + dataTexto + "'" );

        for( DateTimeFormatter formato : FORMATOS ) {
            try{
                LocalDateTime data = LocalDateTime.parse( dataTexto, formato );
                System.out.println( "Formato funcionou: " + formato );
                return data;
            } catch ( DateTimeParseException e ) {

            }
        }

        return tentarExtracaoNumerica( dataTexto, numeroLinha );
    }

    private static String limparData( String data ) {
        if( data == null )
            return "";

        String limpa = data.trim();
        limpa = limpa.replace( "  ", " " );

        return limpa;
    }

    private static String aplicarCorrecoes( String data ) {
        String corrigida = data;
        corrigida = corrigida.replace("011:", "11:");

        if( corrigida.contains( "24:" ) ) {
            corrigida.replace( "24:", "00:");
            System.out.println( "Corrigindo 24: para 00:" );
        }

        corrigida = corrigida.replace( "t", "T" );

        if( corrigida.length() == 16 && corrigida.contains( "T" ) ) {
            corrigida = corrigida + ":00";
            System.out.println( "Adicionado segundo: " + corrigida );
        }

        if( corrigida.contains( "/" ) ) {
            corrigida = corrigida.replace( "/", "-");
            System.out.println( "Trocando / por -: " +  corrigida );
        }

        corrigida = corrigida.replace( " T", "T");
        corrigida = corrigida.replace( "T ", "T");

        return corrigida;
    }

    private static LocalDateTime tentarExtracaoNumerica( String data, int numeroLinha ) {
        System.out.println( "Tentando extração númerica..." );

        String numeros = data.replaceAll( "[^0-9]", "" );

        if( numeros.length() >= 14 ) {
            String ano = numeros.substring( 0, 4 );
            String mes = numeros.substring( 4, 6 );
            String dia = numeros.substring( 6, 8 );
            String hora = numeros.substring( 8, 10 );
            String min = numeros.substring( 10, 12 );
            String seg = numeros.substring( 12, 14 );

            String dataFormatada = String.format( "%s-%s-%sT%s:%s:%s", ano, mes, dia, hora, min, seg );

            System.out.println( "Exibindo: " + dataFormatada );

            try{
                return LocalDateTime.parse( dataFormatada, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
            } catch ( DateTimeParseException e ) {

            }
        }

        throw new IllegalArgumentException(
                "x Não foi possivel converter data : '" + data + "' na linha " + numeroLinha
        );
    }
}
