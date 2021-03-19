package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;

public class EndGameActivity extends AppCompatActivity {

    Button btn_retry, btn_scores;
    ImageButton btn_returnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        int score = getIntent().getIntExtra("user_score", 0);

        btn_retry = findViewById(R.id.button_retry);
        btn_returnMainMenu = findViewById(R.id.button_returnMenu);
        btn_scores = findViewById(R.id.button_scoresEndGame);
        TextView tv_score = findViewById(R.id.score);

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

        startRetryButtonAnimation();
    }


        private void startRetryButtonAnimation() {
            Animation animationUp = AnimationUtils.loadAnimation(this, R.anim.scale_up_text);
            Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.scale_down_text);
            animationUp.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    btn_retry.startAnimation(animationDown);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            animationDown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    btn_retry.startAnimation(animationUp);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            btn_retry.startAnimation(animationUp);
        }


    @Override
    protected void onResume() {
        super.onResume();
        btn_returnMainMenu.setEnabled(true);
        btn_scores.setEnabled(true);
        btn_retry.setEnabled(true);
    }
}