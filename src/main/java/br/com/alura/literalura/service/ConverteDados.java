package br.com.alura.literalura.service;

import br.com.alura.literalura.model.LivroRecord;
import br.com.alura.literalura.model.Linguagem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            if (json == null || json.isEmpty()) {
                return null; // Lida com entradas vazias ou nulas
            }
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao deserializar JSON para o tipo " + classe.getName(), e);
        }
    }

    public Set<Linguagem> converteLinguagensParaSet(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            System.out.println("Lista de idiomas está nula ou vazia.");
            return Collections.emptySet();
        }

        System.out.println("Idiomas encontrados: " + idiomas); // Log para verificar a lista de idiomas recebida

        return idiomas.stream()
                .map(Linguagem::valueOf)  // A constante precisa estar correta
                .collect(Collectors.toSet());
    }

    private Linguagem toLinguagem(String idioma) {
        try {
            return Linguagem.valueOf(idioma.toUpperCase()); // Garantindo que a string seja em maiúsculo
        } catch (IllegalArgumentException e) {
            System.out.println("Valor de idioma inválido: " + idioma); // Log de erro
            return null; // Retorna null ou um valor default se necessário
        }
    }

    public LivroRecord[] converteJsonParaLivroRecords(String json) {
        try {
            if (json == null || json.isEmpty()) {
                return new LivroRecord[0]; // Retorna um array vazio se o JSON estiver vazio
            }
            var resultado = mapper.readTree(json);
            var books = resultado.path("results"); // Pega a array "results"
            return mapper.convertValue(books, LivroRecord[].class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao deserializar JSON para LivroRecord[]", e);
        }
    }

}
