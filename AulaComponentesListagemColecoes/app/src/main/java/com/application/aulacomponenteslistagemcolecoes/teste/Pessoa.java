package com.application.aulacomponenteslistagemcolecoes.teste;

public class Pessoa {

    private String nome = "";
    private int idade = 0;

    public void salvarTelefone( String... telefones ){
        for ( String telefone : telefones ){
            System.out.println("telefone: " + telefone);
        }
    }

    public String getNome() {
        System.out.println("get: " + nome);
        return nome.toUpperCase();
    }

    public void setNome(String nome) {
        System.out.println("set: " + nome);
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
