package projeto.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Pessoa {

    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
    private LocalDateTime dataUltimaAlteracao;
    private int idUnico;
    private static int idGeral;
    private boolean deveExibir;

    public Pessoa(String nome, String telefone, LocalDate dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = LocalDate.now();
        this.dataUltimaAlteracao = LocalDateTime.now();
        this.idGeral++;
        this.idUnico = this.idGeral;
        this.deveExibir = true;

    }

    public void tabelaPrint() {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        System.out.print("| " + idUnico);
        String tamID = String.valueOf(idUnico) ;
        /*
        if (idUnico < 9) {
            tamID = 1;
        } else {
            tamID = 2;
        }*/
        for (int i = tamID.length(); i <= 3; i++) {
            System.out.print(" ");
        }

        System.out.print("| " + nome);
        for (int i = nome.length(); i < 41; i++) {
            System.out.print(" ");
        }

        System.out.print("|   " + telefone);
        for (int i = telefone.length(); i < 15; i++) {
            System.out.print(" ");
        }
        System.out.print("   |       " + formatterDate.format(dataNascimento) + " ");

        System.out.print("      |      " + formatterDate.format(dataCadastro));

        System.out.println("      |    " + formatterDateTime.format(dataUltimaAlteracao) + "    |");

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public int getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(int idUnico) {
        this.idUnico = idUnico;
    }

    public static int getIdGeral() {
        return idGeral;
    }

    public static void setIdGeral(int idGeral) {
        Pessoa.idGeral = idGeral;
    }

    public boolean isDeveExibir() {
        return deveExibir;
    }

    public void setDeveExibir(boolean deveExibir) {
        this.deveExibir = deveExibir;
    }

}
