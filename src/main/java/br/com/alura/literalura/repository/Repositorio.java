package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Repositorio extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma = :idioma")
    List<Livro> findLivrosPorIdioma(String idioma);


    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND a.anoFalecimento IS NULL")
    List<Autor> findAutoresPorAnoVivo(int ano);


    @Query("SELECT a FROM Autor a")
    List<Autor> findAllAutores();

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}

