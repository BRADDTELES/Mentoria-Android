package com.app.aovivoaprendajavazero.classes;

/*
Modificadores de acesso
public, private, protected, default(padrão)
* */

public class Cliente {

    private double saldo;
    public String nome;

    void cadastrar(){

    }

    public double recuperarSaldo(){
        saldo = 100;
        return saldo;
    }

}
