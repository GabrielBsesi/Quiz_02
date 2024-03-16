package com.example.quiz_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quiz_02.db.QuizDBUtil;


public class MainActivity extends AppCompatActivity {

    private Button button_start, reset_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Button start_button = findViewById(R.id.button_start);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irParaProximaTela();
            }
        });



        Button resetButton = findViewById(R.id.reset_Button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetContador();
            }
        });


    }
    private void irParaProximaTela() {
        Intent intent = new Intent(this, Quest1Activity.class);
        // Passa o valor do contador como extra para a próxima atividade
        //intent.putExtra("respostasCorretas", respostasCorretas);
        startActivity(intent);
    }


    private void resetContador() {
        QuizDBUtil.resetContador(getApplicationContext());
        // Você pode adicionar qualquer outra lógica aqui após redefinir o contador
    }

}