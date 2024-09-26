package com.application.aulapedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selecionarPedra( View view ){
        vereficarGanhador("pedra");
    }

    public void selecionarPapel( View view ){
        vereficarGanhador("papel");
    }

    public void selecionarTesoura( View view ){
        vereficarGanhador("tesoura");
    }

    private void vereficarGanhador( String escolhaUsuario ){
        System.out.println("Item clicado: " + escolhaUsuario);
    }

}