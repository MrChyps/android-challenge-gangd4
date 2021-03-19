package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;

public class EndGameActivity extends AppCompatActivity {

   private Button btn_retry, btn_scores;
   private ImageButton btn_returnMainMenu;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_end_game);

      int score = getIntent().getIntExtra("user_score", 0);

      btn_retry = findViewById(R.id.button_retry);
      btn_returnMainMenu = findViewById(R.id.button_returnMenu);
      btn_scores = findViewById(R.id.button_scoresEndGame);
      TextView tv_score = findViewById(R.id.tv_score);

      tv_score.setText(String.valueOf(score));

      btn_retry.setOnClickListener(v -> {
         btn_retry.setEnabled(false);
         Intent intent = new Intent(EndGameActivity.this, GameActivity.class);
         this.startActivity(intent);
      });
      btn_scores.setOnClickListener(v -> {
         btn_scores.setEnabled(false);
         Intent intent = new Intent(EndGameActivity.this, ScoreActivity.class);
         this.startActivity(intent);
      });
      btn_returnMainMenu.setOnClickListener(v -> {
         btn_returnMainMenu.setEnabled(false);
         Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
         this.startActivity(intent);
      });
   }

   @Override
   protected void onResume() {
      super.onResume();
      btn_returnMainMenu.setEnabled(true);
      btn_scores.setEnabled(true);
      btn_retry.setEnabled(true);
   }
}