package com.app.aovivoaprendajavazero;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.aovivoaprendajavazero.classes.Cliente;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pego o que o usuário digitou
        String nome = "danillo";//Peguei o que o usuário digitou na tela

        Cliente cliente = new Cliente();
        double saldo = cliente.recuperarSaldo();

    }
}