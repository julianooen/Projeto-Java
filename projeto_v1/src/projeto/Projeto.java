package projeto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import projeto.model.MenuPrincipal;
import projeto.model.Pessoa;

public class Projeto {

    public static void main(String[] args) {
        /*
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        TemporalAccessor data = formatter.parse("01/01/2000");
        Pessoa pessoa = new Pessoa("juca","1234567",LocalDate.from(data));
        Pessoa pessoa1 = new Pessoa("juca111","1234567",LocalDate.from(data));
        
        pessoa.tabelaPrint();
        pessoa1.tabelaPrint();
*/
        
        MenuPrincipal.menu();
    }

}
