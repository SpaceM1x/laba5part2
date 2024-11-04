package com.example.laba5part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private int actTurn = 1;
    private int FPChoice;
    private int SPChoice;
    private TextView turnText;
    private Button rock;
    private Button paper;
    private Button scissors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelayout);

        turnText = findViewById(R.id.textView);
        rock = findViewById(R.id.button3);
        paper = findViewById(R.id.button5);
        scissors = findViewById(R.id.button4);

        // Установка начального значения текста для хода
        turnText.setText(String.valueOf(actTurn));

        rock.setOnClickListener(v -> onClick(rock));
        paper.setOnClickListener(v -> onClick(paper));
        scissors.setOnClickListener(v -> onClick(scissors));
    }

    private String resultOfTheGame(int FP, int SP) {
        String result = "";
        if (FP == 1) {
            if (SP == 2) {
                result = "2";
            }
            if (SP == 3) {
                result = "1";
            }
        }
        if (FP == 2) {
            if (SP == 3) {
                result = "2";
            }
            if (SP == 1) {
                result = "1";
            }
        }
        if (FP == 3) {
            if (SP == 1) {
                result = "2";
            }
            if (SP == 2) {
                result = "1";
            }
        }
        return result;
    }

    public void onClick(Button button) {
        int buttonId = button.getId(); // Получаем ID кнопки

        if (actTurn == 1) {
            // Устанавливаем выбор первого игрока
            if (buttonId == R.id.button3) {
                FPChoice = 1; // Камень
            } else if (buttonId == R.id.button5) {
                FPChoice = 3; // Ножницы
            } else if (buttonId == R.id.button4) {
                FPChoice = 2; // Бумага
            }
            actTurn++;
            turnText.setText(String.valueOf(actTurn)); // Обновляем текст для следующего хода
        } else if (actTurn == 2) {
            // Устанавливаем выбор второго игрока
            if (buttonId == R.id.button3) {
                SPChoice = 1; // Камень
            } else if (buttonId == R.id.button5) {
                SPChoice = 3; // Ножницы
            } else if (buttonId == R.id.button4) {
                SPChoice = 2; // Бумага
            }
            actTurn = 1; // Возвращаем к первому ходу
            turnText.setText(String.valueOf(actTurn)); // Обновляем текст

            // После второго хода определяем результат
            String result = resultOfTheGame(FPChoice, SPChoice);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("message", result);
            setResult(Activity.RESULT_OK, resultIntent); // Возвращаем результат
            finish(); // Завершаем активность
        }
    }
}
