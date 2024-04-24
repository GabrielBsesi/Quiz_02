package com.example.quiz_02;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz_02.db.QuizDBUtil;

public class Quest4Activity extends AppCompatActivity {

    private Button button1, button2, button3, button4, backButton, nextButton;
    private TextView tvRespostasCorretas;
    private int respostaCorreta = 4; // Número do botão correto
    private int respostasCorretas = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest4);


        int contador = QuizDBUtil.recuperarContador(getApplicationContext());

        // Exibe o contador em um TextView
        tvRespostasCorretas = findViewById(R.id.tvRespostasCorretas);
        tvRespostasCorretas.setText("Acertou: " + contador + "/10");

        // Encontrando botões pelo ID
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        // Encontrando o textView pelo ID
        //tvRespostasCorretas = findViewById(R.id.tvRespostasCorretas);

        int numeroEnviado = getIntent().getIntExtra("respostasCorretas", 0);

        // Somando o número enviado com o número do botão correto
        respostaCorreta += numeroEnviado;


        // Configurando os onClickListeners para cada botão
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(button1, 1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(button2, 2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(button3, 3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(button4, 4);
            }
        });

        Button backButton = findViewById(R.id.homeButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTela();
            }
        });


        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProximaTela();
            }
        });

    }

    // Método para verificar se a resposta está correta e atualizar as cores dos botões e contadores
    private void verificarResposta(Button button, int numeroBotao) {
        if (numeroBotao == respostaCorreta) {
            // Correto: botão verde
            button.setBackgroundColor(Color.GREEN);
            respostasCorretas++;
            int contador = respostasCorretas;
            QuizDBUtil.salvarContador(getApplicationContext(), contador);

            int contadorS = QuizDBUtil.recuperarContador(getApplicationContext());

            // Atualizar o contador na tela
            tvRespostasCorretas.setText("Acertou: " + contadorS + "/10");
        } else {
            // Errado: botão vermelho
            button.setBackgroundColor(Color.RED);
            // Altera a cor do botão correto para verde
            switch (respostaCorreta) {
                case 1:
                    //button1.setBackgroundColor(Color.GREEN);
                    break;
                case 2:
                    //button2.setBackgroundColor(Color.GREEN);
                    break;
                case 3:
                    //button3.setBackgroundColor(Color.GREEN);
                    break;
                case 4:
                    //button4.setBackgroundColor(Color.GREEN);
                    break;
            }
        }
        // Desativar os botões após clicar em um deles
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }

    // Método para ir para a próxima tela e passar os dados dos contadores

    private void voltarTela() {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("respostasCorretas", respostasCorretas);
        //QuizDBUtil.menosContador(getApplicationContext());
        QuizDBUtil.resetContador(getApplicationContext());
        startActivity(intent);
    }

    private void ProximaTela() {
        Intent intent = new Intent(this, Quest5Activity.class);
        //intent.putExtra("respostasCorretas", respostasCorretas);
        startActivity(intent);
    }
}