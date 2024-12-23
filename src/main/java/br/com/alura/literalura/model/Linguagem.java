package br.com.alura.literalura.model;

public enum Linguagem {
    EN("Inglês"),
    PT("Português"),
    FR("Francês"),
    FI("Finlandês"),
    ES("Espanhol"),
    DE("Alemão");

    private final String descricao;

    Linguagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}