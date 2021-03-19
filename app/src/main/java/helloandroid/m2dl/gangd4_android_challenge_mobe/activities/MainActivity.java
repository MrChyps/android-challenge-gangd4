package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;
import helloandroid.m2dl.gangd4_android_challenge_mobe.services.ScoreService;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TextView tv_MainTitle;
    private Button btn_play;
    private Button btn_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ScoreService.initDatabase(this);
        this.context = this;

        tv_MainTitle = findViewById(R.id.tv_ActivityName);
        btn_play = findViewById(R.id.play_button);
        btn_score = findViewById(R.id.scores_button);

        btn_play.setOnClickListener(v -> {
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            List<Sensor> listSensor=
                    sensorManager.getSensorList(Sensor.TYPE_ALL);
            for (Sensor s : listSensor) {
                Log.i("SENSORLIST",s.getName() +", "+s.getStringType()+", "+ s.getType());
            }
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