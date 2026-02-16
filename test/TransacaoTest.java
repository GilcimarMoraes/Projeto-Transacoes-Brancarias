import model.Transacao;
import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoTest {

    public static void main(String[] args) {
        System.out.println( "Teste 1: Criar Transação" );

        Transacao t = new Transacao( "1520", "0001", "SANTANDER",
                "JOAO", "DEPOSITO", LocalDateTime.now(),
                new BigDecimal("1000.00") );

        System.out.println( "Transação criada com sucesso!" + t );
    }
}
