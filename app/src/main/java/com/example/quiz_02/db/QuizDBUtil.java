package com.example.quiz_02.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QuizDBUtil {

    private static final String TABLE_NAME = "contador";

    public static void salvarContador(Context context, int novoContador) {
        // Recupera o contador existente
        int contadorExistente = recuperarContador(context);

        // Soma o novo contador ao contador existente
        int contadorAtualizado = contadorExistente + novoContador;


        // Atualiza o contador no banco de dados
        QuizDBHelper dbHelper = new QuizDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("contador", contadorAtualizado);

        if (contadorExistente > 0) {
            // Se o contador existente for maior que zero, atualize-o no banco de dados
            db.update(TABLE_NAME, values, null, null);
        } else {
            // Caso contrário, insira um novo registro no banco de dados
            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

    public static int recuperarContador(Context context) {
        QuizDBHelper dbHelper = new QuizDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT contador FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);


        int contador = 0;
        if (cursor.moveToFirst()) {
            int contadorIndex = cursor.getColumnIndex("contador");
            if (contadorIndex != -1) {
                contador = cursor.getInt(contadorIndex);
            } else {
                // Lida com o caso em que a coluna não foi encontrada
            }
        }

        cursor.close();
        db.close();

        return contador;
    }

    public static void resetContador(Context context) {
        QuizDBHelper dbHelper = new QuizDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Exclui a entrada do contador do banco de dados
        db.delete(TABLE_NAME, null, null);

        db.close();
    }


}

