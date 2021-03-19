package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        Button btn_retry = findViewById(R.id.button_retry);
        Button btn_returnMainMenu = findViewById(R.id.button_returnMenu);
        Button btn_scores = findViewById(R.id.button_scoresEndGame);

        btn_retry.setOnClickListener(v -> {
            Intent intent = new Intent(EndGameActivity.this, GameActivity.class);
            this.startActivity(intent);
        });
        btn_scores.setOnClickListener(v -> {
            Intent intent = new Intent(EndGameActivity.this, ScoreActivity.class);
            this.startActivity(intent);
        });
        btn_returnMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
            this.startActivity(intent);
        });
    }
}