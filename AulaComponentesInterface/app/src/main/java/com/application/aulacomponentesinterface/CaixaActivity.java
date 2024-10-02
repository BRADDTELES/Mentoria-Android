package com.application.aulacomponentesinterface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class CaixaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caixa);

        Button botao = findViewById( R.id.botao_salvar );
        TextInputEditText email = findViewById( R.id.edit_email );
        botao.setOnClickListener(v -> {
                String emailUsuario = email.getText().toString();
                System.out.println("Email selecionando: " + emailUsuario);
        });

    }

}