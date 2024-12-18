package com.application.aulafrasesdodia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] frases = {
            "Frase 01",
            "Frase 02",
            "Frase 03"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*public void gerarFraseAleatoria(View view){

        String[] frases = {
                "Frase 01",
                "Frase 02",
                "Frase 03",
                "Frase 04",
        };

        int numeroAleatorio = new Random().nextInt(4);//0,1,2,3

        TextView texto = findViewById(R.id.textFrase);
        texto.setText( frases[numeroAleatorio] );

    }*/

    public void gerarFrase(View view){

        TextView texto = findViewById(R.id.textResultado);
        int numeroAleatorio = new Random().nextInt(3);
        String frase = frases[ numeroAleatorio ];

        texto.setText( frase );

    }

    public void exibirTodas(View view){

        TextView texto = findViewById(R.id.textResultado);

        String textResultado = "";

        for (  String frase : frases ){
            textResultado = textResultado + frase + "\n";
        }

        texto.setText( textResultado );

    }
}