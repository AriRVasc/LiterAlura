package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.model.LivroRecord;
import br.com.alura.literalura.repository.Repositorio;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private final Repositorio repositorio;

    private final String ENDERECO_API = "https://gutendex.com/books?search=";

    public Principal(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    Escolha o número da sua opção:
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos por determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivros();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void processarLivros(List<LivroRecord> records) {
        for (LivroRecord record : records) {
            Autor autor = record.autor() != null ? new Autor(record.autor()) : null;

            Livro livro = new Livro(record.titulo(), record.numeroDownloads(), autor, record.idioma());
            repositorio.save(livro);

            System.out.println("Livro salvo: " + livro);
        }
    }

    private void buscarLivros() {
        System.out.println("Digite o título do livro para busca:");
        var titulo = leitura.nextLine();

        // Consultar livros na API
        var json = consumo.obterDados(ENDERECO_API + titulo.replace(" ", "+"));
        LivroRecord[] records = conversor.converteJsonParaLivroRecords(json);

        if (records == null || records.length == 0) {
            System.out.println("Nenhum livro encontrado com o título: " + titulo);
        } else {
            System.out.println("Livro encontrado");
            processarLivros(List.of(records));
        }
    }


    private void listarLivrosRegistrados() {
        List<Livro> livros = repositorio.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = repositorio.findAllAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Digite o ano para buscar autores vivos:");
        int ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> autores = repositorio.findAutoresPorAnoVivo(ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano informado.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o código do idioma (ex: en para inglês, pt para português):");
        String idioma = leitura.nextLine();
        List<Livro> livros = repositorio.findLivrosPorIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma informado.");
        } else {
            livros.forEach(System.out::println);
        }
    }
}
