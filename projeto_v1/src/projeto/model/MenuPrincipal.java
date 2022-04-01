package projeto.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {

    private static ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
    private static ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
    private static Pessoa pessoa;
    private static Aluno aluno;

    public static void menu() {

        System.out.println("===================================================================");
        System.out.println("|                         Menu Principal                          |");
        System.out.println("===================================================================");
        System.out.println("| Digite o número a seguir para executar o que você deseja fazer  |");
        System.out.println("|                                                                 |");
        System.out.println("| 1 - Cadastrar nova pessoa/aluno                                 |");
        System.out.println("| 2 - Exibir pessoas ou alunos cadastrados                        |");
        System.out.println("| 3 - Atualizar pessoa ou aluno                                   |");
        System.out.println("| 4 - Deletar pessoa ou aluno                                     |");
        System.out.println("| 5 - Sair                                                        |");
        System.out.println("|                                                                 |");
        System.out.println("===================================================================");
        Scanner entrada = new Scanner(System.in);
        String escolha = entrada.nextLine();

        switch (escolha) {
            case "1":
                cadastrar();
                break;
            case "2":
                tabelas();
                break;
            case "3":
                atualizar();
                break;
            case "4":
                deletar();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                entradaInvalida();
                menu();
                break;

        }

    }

    private static void cadastrar() {
        boolean continua = true;
        boolean cadastroAlunos = true;
        Scanner entradaDados = new Scanner(System.in);
        System.out.println("Digite o nome");
        String nome = entradaDados.nextLine();
        System.out.println("Digite o telefone");
        String telefone = entradaDados.nextLine();

        String notaStringFim = "";
        double notaFim = 0.0;

        while (continua) {

            System.out.println("Digite a nota final do aluno, deixar este campo em branco será cadastrada uma pessoa! Obs.: nota \"0\" será cadastrado um aluno");
            notaStringFim = entradaDados.nextLine();
            if (notaStringFim == "") {

                cadastroAlunos = false;
                break;
            } else {
                try {
                    notaFim = Double.parseDouble(notaStringFim);

                    break;
                } catch (Exception e) {
                    entradaInvalida();
                }
            }

        }

        while (continua) {

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
                System.out.println("Entre com a data  de nascimento no formato dia/mês/ano, ex.: 01/01/2010!");
                TemporalAccessor data = formatter.parse(entradaDados.next());
                if (cadastroAlunos) {
                    aluno = new Aluno(nome, telefone, LocalDate.from(data), notaFim);
                    listaAlunos.add(aluno);
                    
                } else if (!cadastroAlunos) {
                    pessoa = new Pessoa(nome, telefone, LocalDate.from(data));
                    listaPessoas.add(pessoa);
                    
                }
                System.out.println("Cadastrado com sucesso!");
                break;
            } catch (Exception e) {
                entradaInvalida();
            }
        }
        menu();
    }

    private static void tabelas() {
        System.out.println("===================================================================");
        System.out.println("| Qual tabela voce deseja ver?                                    |");
        System.out.println("===================================================================");
        System.out.println("|                                                                 |");
        System.out.println("| 1 - Exibir cadastro de alunos                                   |");
        System.out.println("| 2 - Exibir cadastro de pessoas                                  |");
        System.out.println("| 3 - Voltar para menu principal                                  |");
        System.out.println("|                                                                 |");
        System.out.println("===================================================================");
        Scanner entradaTabela = new Scanner(System.in);

        String escolha = entradaTabela.nextLine();

        switch (escolha) {
            case "1":
                tabelaAlunos();
                voltarMenu();
                break;
            case "2":
                tabelaPessoas();
                voltarMenu();
                break;
            case "3":
                menu();
                break;
            default:
                entradaInvalida();
                tabelas();
                break;
        }

    }

    private static void tabelaAlunos() {
        if (verificaSeTemRegistrosAluno()) {
            System.out.println("===============================================================================================================================================================");
            System.out.println("| ID  |         Nome                             |     Telefone        |   Data de nascimento   |   Data do cadastro   | Data da últma alteração | Nota final |");
            System.out.println("===============================================================================================================================================================");

            for (Aluno obj : listaAlunos) {
                obj.tabelaPrint();
            }
            System.out.println("|                                                                                                                                                             |");
            System.out.println("===============================================================================================================================================================");
        } else {
            System.out.println(msgTabelaAlunosVazio());
        }

    }

    private static void tabelaPessoas() {
        if (verificaSeTemRegistrosPessoa()) {
            System.out.println("==================================================================================================================================================");
            System.out.println("| ID  |         Nome                             |     Telefone        |   Data de nascimento   |   Data do cadastro   | Data da últma alteração |");
            System.out.println("==================================================================================================================================================");

            for (Pessoa obj : listaPessoas) {
                obj.tabelaPrint();
            }

            System.out.println("|                                                                                                                                                |");
            System.out.println("==================================================================================================================================================");

        } else {
            System.out.println(msgTabelaPessoasVazio());
        }

    }

    private static void voltarMenu() {
        System.out.println("Digite qualquer tecla para voltar ao menu principal!");
        Scanner entradaReturn = new Scanner(System.in);
        String voltar = entradaReturn.nextLine();

        menu();
    }

    private static void deletar() {
        System.out.println("===================================================================");
        System.out.println("| Qual tabela voce deseja deletar um registro?                    |");
        System.out.println("===================================================================");
        System.out.println("|                                                                 |");
        System.out.println("| 1 - Deletar um aluno                                            |");
        System.out.println("| 2 - Deletar uma pessoa                                          |");
        System.out.println("| 3 - Cancelar e voltar ao menu principal                         |");
        System.out.println("|                                                                 |");
        System.out.println("===================================================================");
        Scanner entradaTabela = new Scanner(System.in);

        String escolha = entradaTabela.nextLine();

        switch (escolha) {
            case "1":
                deletaAlunos();
                break;
            case "2":
                deletaPessoas();
                break;
            case "3":
                menu();
                break;
            default:
                entradaInvalida();
                deletar();
                break;
        }
    }

    private static void deletaAlunos() {
        if (verificaSeTemRegistrosAluno()) {
            tabelaAlunos();
            Scanner entradaDelete = new Scanner(System.in);
            boolean continua = true;
            int msg = 0;
            while (continua) {

                try {
                    System.out.println("Digite o ID do registro que você deseja deletar!");
                    int escolhaDel = entradaDelete.nextInt();

                    for (int i = 0; i < listaAlunos.size(); i++) {
                        if (listaAlunos.get(i).getIdUnico() == escolhaDel) {
                            listaAlunos.remove(i);
                            msg = 1;
                        }
                    }
                    System.out.println(escolhaDel);
                    break;
                } catch (Exception e) {
                    entradaInvalida();
                    entradaDelete.next();
                }
            }
            switch (msg) {
                case 0:
                    System.out.println("ID inexistente!");
                    break;
                case 1:
                    System.out.println("Registro deletado com sucesso!");
                    break;
            }
        } else {
            System.out.println(msgTabelaAlunosVazio());

        }

        menu();
    }

    private static void deletaPessoas() {
        if (verificaSeTemRegistrosAluno()) {
            tabelaPessoas();
            Scanner entradaDelete = new Scanner(System.in);
            boolean continua = true;
            int msg = 0;
            while (continua) {

                try {
                    System.out.println("Digite o ID do registro que você deseja deletar!");
                    int escolhaDel = entradaDelete.nextInt();

                    for (int i = 0; i < listaPessoas.size(); i++) {
                        if (listaPessoas.get(i).getIdUnico() == escolhaDel) {
                            listaPessoas.remove(i);
                            msg = 1;
                        }
                    }
                    System.out.println(escolhaDel);
                    break;
                } catch (Exception e) {
                    entradaInvalida();
                    entradaDelete.next();
                }
            }
            switch (msg) {
                case 0:
                    System.out.println("ID inexistente!");
                    break;
                case 1:
                    System.out.println("Registro deletado com sucesso!");
                    break;
            }
        } else {
            System.out.println(msgTabelaPessoasVazio());

        }

        menu();
    }

    private static boolean verificaSeTemRegistrosAluno() {
        return listaAlunos.size() > 0;
    }

    private static boolean verificaSeTemRegistrosPessoa() {
        return listaPessoas.size() > 0;
    }

    public static void atualizar() {
        System.out.println("===================================================================");
        System.out.println("| Qual tabela voce deseja atualizar um registro?                  |");
        System.out.println("===================================================================");
        System.out.println("|                                                                 |");
        System.out.println("| 1 - Atualizar aluno                                             |");
        System.out.println("| 2 - Atualizar pessoa                                            |");
        System.out.println("| 3 - Cancelar e voltar ao menu principal                         |");
        System.out.println("|                                                                 |");
        System.out.println("===================================================================");
        Scanner entradaTabela = new Scanner(System.in);

        String escolha = entradaTabela.nextLine();

        switch (escolha) {
            case "1":
                if (verificaSeTemRegistrosAluno()) {
                    atualizaAlunos();
                } else {
                    System.out.println(msgTabelaAlunosVazio());
                }
                break;
            case "2":
                if (verificaSeTemRegistrosAluno()) {
                    atualizaPessoas();
                } else {
                    System.out.println(msgTabelaPessoasVazio());
                }
                break;
            case "3":

                break;
            default:
                entradaInvalida();
                atualizar();
                break;
        }
        menu();
    }

    private static void atualizaAlunos() {
        tabelaAlunos();

        Scanner entradaEdit = new Scanner(System.in);
        Scanner entradaId = new Scanner(System.in);

        boolean continua = true;
        while (continua) {

            try {
                System.out.println("Digite o id do registro que vc deseja editar!");
                int id = entradaId.nextInt();
                if (verificaIdAlunos(id)) {

                    System.out.println("===================================================================");
                    System.out.println("| O que você deseja editar?                                       |");
                    System.out.println("===================================================================");
                    System.out.println("|1 - Nome                                                         |");
                    System.out.println("|2 - Telefone                                                     |");
                    System.out.println("|3 - Data de nascimento                                           |");
                    System.out.println("|4 - Cancelar e voltar ao menu principal                          |");
                    System.out.println("===================================================================");
                    String escolhaEdit = entradaEdit.nextLine();
                    switch (escolhaEdit) {
                        case "1":
                            System.out.println("Digite o novo nome!");
                            String novoNome = entradaEdit.nextLine();
                            listaAlunos.forEach(i -> {
                                if (i.getIdUnico() == id) {
                                    i.setNome(novoNome);
                                    i.setDataUltimaAlteracao(LocalDateTime.now());
                                }
                            });
                            System.out.println("Registro atualizado com sucesso!");
                            menu();
                            break;
                        case "2":
                            System.out.println("Digite o novo telefone!");
                            String novoTelefone = entradaEdit.nextLine();
                            listaAlunos.forEach(i -> {
                                if (i.getIdUnico() == id) {
                                    i.setTelefone(novoTelefone);
                                    i.setDataUltimaAlteracao(LocalDateTime.now());
                                }
                            });
                            System.out.println("Registro atualizado com sucesso!");

                            menu();
                            break;
                        case "3":
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
                            System.out.println("Digite a nova data de nascimento no formato dia/mês/ano, ex.: 01/01/2010!");
                            TemporalAccessor novaData = formatter.parse(entradaEdit.nextLine());
                            listaAlunos.forEach(i -> {
                                if (i.getIdUnico() == id) {
                                    i.setDataNascimento(LocalDate.from(novaData));
                                    i.setDataUltimaAlteracao(LocalDateTime.now());
                                }
                            });
                            System.out.println("Registro atualizado com sucesso!");

                            menu();
                            break;
                        } finally {
                            entradaInvalida();
                            System.out.println("Alteração não realizada!");
                            break;
                        }
                        case "4":
                            menu();
                            break;
                        default:
                            entradaInvalida();
                            break;
                    }
                } else {
                    System.out.println("ID inexistente!");

                }
                break;
            } catch (Exception e) {
                entradaInvalida();
                entradaId.next();
            }

        }
        atualizaAlunos();
    }

    private static void atualizaPessoas() {
        tabelaPessoas();

        Scanner entradaEdit = new Scanner(System.in);
        Scanner entradaId = new Scanner(System.in);

        boolean continua = true;
        while (continua) {

            try {
                System.out.println("Digite o id do registro que vc deseja editar!");
                int id = entradaId.nextInt();
                if (verificaIdPessoas(id)) {

                    System.out.println("===================================================================");
                    System.out.println("| O que você deseja editar?                                       |");
                    System.out.println("===================================================================");
                    System.out.println("|1 - Nome                                                         |");
                    System.out.println("|2 - Telefone                                                     |");
                    System.out.println("|3 - Data de nascimento                                           |");
                    System.out.println("|4 - Cancelar e voltar ao menu principal                          |");
                    System.out.println("===================================================================");
                    String escolhaEdit = entradaEdit.nextLine();
                    switch (escolhaEdit) {
                        case "1":
                            System.out.println("Digite o novo nome!");
                            String novoNome = entradaEdit.nextLine();
                            listaPessoas.forEach(i -> {
                                if (i.getIdUnico() == id) {
                                    i.setNome(novoNome);
                                    i.setDataUltimaAlteracao(LocalDateTime.now());
                                }
                            });
                            System.out.println("Registro atualizado com sucesso!");
                            menu();
                            break;
                        case "2":
                            System.out.println("Digite o novo telefone!");
                            String novoTelefone = entradaEdit.nextLine();
                            listaPessoas.forEach(i -> {
                                if (i.getIdUnico() == id) {
                                    i.setTelefone(novoTelefone);
                                    i.setDataUltimaAlteracao(LocalDateTime.now());
                                }
                            });
                            System.out.println("Registro atualizado com sucesso!");

                            menu();
                            break;
                        case "3":
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
                            System.out.println("Digite a nova data de nascimento no formato dia/mês/ano, ex.: 01/01/2010!");
                            TemporalAccessor novaData = formatter.parse(entradaEdit.nextLine());
                            listaPessoas.forEach(i -> {
                                if (i.getIdUnico() == id) {
                                    i.setDataNascimento(LocalDate.from(novaData));
                                    i.setDataUltimaAlteracao(LocalDateTime.now());
                                }
                            });
                            System.out.println("Registro atualizado com sucesso!");

                            menu();
                            break;
                        } finally {
                            entradaInvalida();
                            System.out.println("Alteração não realizada!");
                            break;
                        }
                        case "4":
                            menu();
                            break;
                        default:
                            entradaInvalida();
                            break;
                    }
                } else {
                    System.out.println("ID inexistente!");

                }
                break;
            } catch (Exception e) {
                entradaInvalida();
                entradaId.next();
            }

        }
        atualizaPessoas();
    }

    private static String msgTabelaAlunosVazio() {
        return "Não há registro de alunos!";
    }

    private static String msgTabelaPessoasVazio() {
        return "Não há registro de pessoas!";
    }

    private static boolean verificaIdAlunos(int id) {
        boolean res = false;
        for (Pessoa obj : listaAlunos) {
            if (obj.getIdUnico() == id) {
                res = true;
                break;
            }
        }
        return res;
    }

    private static boolean verificaIdPessoas(int id) {
        boolean res = false;
        for (Pessoa obj : listaPessoas) {
            if (obj.getIdUnico() == id) {
                res = true;
                break;
            }
        }
        return res;
    }

    private static void entradaInvalida() {
        System.out.println("Entrada inválida!");
    }
}
