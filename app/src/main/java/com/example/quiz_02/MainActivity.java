package com.example.quiz_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quiz_02.db.QuizDBUtil;


public class MainActivity extends AppCompatActivity {

    private Button button_start;

    private TextView tvRespostasCorretas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int contador = QuizDBUtil.recuperarContador(getApplicationContext());

        // Exibe o contador em um TextView
        tvRespostasCorretas = findViewById(R.id.tvRespostasCorretas);
        tvRespostasCorretas.setText("Acertou: " + contador + "/10");



        Button start_button = findViewById(R.id.button_start);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaProximaTela();
            }
        });





    }
    private void irParaProximaTela() {
        Intent intent = new Intent(this, Quest1Activity.class);
        // Passa o valor do contador como extra para a próxima atividade
        //intent.putExtra("respostasCorretas", respostasCorretas);
        QuizDBUtil.resetContador(getApplicationContext());
        startActivity(intent);
    }



}