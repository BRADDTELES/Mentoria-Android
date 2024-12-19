package com.app.revisaoaprendakotlinzero;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class AnimalJava {

}

class PassaroJava extends AnimalJava {

}

public class JavaActivity extends AppCompatActivity {

    String nome;//null

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        int total = 10;
        for (int i = 0; i < total; i++) {
            System.out.println("Iteracao: " + i);
        }

        /*if ( nome != null ){
            System.out.println( nome.length() );
        }*/

    }
}