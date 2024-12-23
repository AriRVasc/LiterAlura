package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroRecord(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Integer numeroDownloads,
        @JsonAlias("languages") String idioma,
        @JsonAlias("authors") AutorRecord autor  // Apenas um autor
) {
}
