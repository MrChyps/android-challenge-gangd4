package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv_MainTitle;
    private Button btn_play;
    private Button btn_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_MainTitle = findViewById(R.id.tv_ActivityName);
        btn_play = findViewById(R.id.play_button);
        btn_score = findViewById(R.id.scores_button);

        btn_play.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
            MainActivity.this.startActivity(myIntent);
        });

        btn_score.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
            MainActivity.this.startActivity(myIntent);
        });

        startMainTitleAnimation();
    }

    //Start the main menu title animation
    private void startMainTitleAnimation() {
        Animation animationUp = AnimationUtils.loadAnimation(this, R.anim.scale_up_text);
        Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.scale_down_text);
        animationUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn_play.startAnimation(animationDown);
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
                btn_play.startAnimation(animationUp);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        btn_play.startAnimation(animationUp);
    }
}