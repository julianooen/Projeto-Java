package projeto.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Aluno extends Pessoa {

    private double notaFim;

    public Aluno(String nome, String telefone, LocalDate dataNascimento, double notaFim) {
        super(nome, telefone, dataNascimento);
        this.notaFim = notaFim;
    }

    @Override
    public void tabelaPrint() {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        System.out.print("| " + super.getIdUnico());
        String tamID = String.valueOf(super.getIdUnico());
        /*if (super.getIdUnico() < 9) {
            tamID = 1;
        } else {
            tamID = 2;
        }*/
        for (int i = tamID.length(); i <= 3; i++) {
            System.out.print(" ");
        }

        System.out.print("| " + super.getNome());
        for (int i = super.getNome().length(); i < 41; i++) {
            System.out.print(" ");
        }

        System.out.print("|   " + super.getTelefone());
        for (int i = super.getTelefone().length(); i < 15; i++) {
            System.out.print(" ");
        }
        System.out.print("   |       " + formatterDate.format(super.getDataNascimento()) + " ");

        System.out.print("      |      " + formatterDate.format(super.getDataCadastro()));

        System.out.print("      |    " + formatterDateTime.format(super.getDataUltimaAlteracao()) + "    |");
        String tamNotaFim = String.valueOf(this.notaFim);
        System.out.print("   " + this.notaFim);
        for (int i = tamNotaFim.length(); i <= 8; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

}
