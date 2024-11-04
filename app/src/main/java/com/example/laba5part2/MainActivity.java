package com.example.laba5part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MainActivity extends AppCompatActivity {

    private TextView playerCount1;
    private int playerCount11;
    private TextView playerCount2;
    private int playerCount22;
    private TextView counts;
    private Button startGame;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Настройка отступов для Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playerCount11 = 0;
        playerCount22 = 0;

        playerCount1 = findViewById(R.id.textView8);
        playerCount2 = findViewById(R.id.textView10);
        counts = findViewById(R.id.textView3);
        startGame = findViewById(R.id.button);

        // Установка начального значения счетчиков
        playerCount1.setText(String.valueOf(playerCount11));
        playerCount2.setText(String.valueOf(playerCount22));

        // Установка обработчика нажатия на кнопку
        startGame.setOnClickListener(this::onClick);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        String message = result.getData().getStringExtra("message");
                        handleReceivedMessage(message);
                    }
                }
        );
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        launcher.launch(intent);
    }

    private void handleReceivedMessage(String message) {
        if (message != null) {
            if (message.equals("1")) {
                playerCount11++;
            } else if (message.equals("2")) {
                playerCount22++;
            }
            playerCount2.setText(String.valueOf(playerCount11));
            playerCount1.setText(String.valueOf(playerCount22));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("message")) {
            String message = intent.getStringExtra("message");
            handleReceivedMessage(message);
            // Очищаем Intent от данных, чтобы избежать повторной обработки
            intent.removeExtra("message");
        }
    }
}
