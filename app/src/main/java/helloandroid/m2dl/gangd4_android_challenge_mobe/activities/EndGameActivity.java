package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;

public class EndGameActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_end_game);

      int score = getIntent().getIntExtra("user_score", 0);
      Button btn_retry = findViewById(R.id.button_retry);
      ImageButton btn_returnMainMenu = findViewById(R.id.button_returnMenu);
      Button btn_scores = findViewById(R.id.button_scoresEndGame);
      TextView tv_score = findViewById(R.id.tv_score);

      tv_score.setText(String.valueOf(score));

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