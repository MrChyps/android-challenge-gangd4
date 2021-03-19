package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Ball;
import helloandroid.m2dl.gangd4_android_challenge_mobe.views.GameView;

public class GameActivity extends Activity implements SensorEventListener, View.OnTouchListener {

    private GameView gv;
    private Sensor lightSensor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);     //  Fixed Portrait orientation

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);

        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);

        SharedPreferences.Editor editor = this.getPreferences(MODE_PRIVATE).edit();
        editor.putFloat("max_light_val", 1);
        editor.apply();

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        this.textView = new TextView(this);
        this.textView.setText("3");
        this.textView.setTextColor(Color.BLACK);
        this.textView.setTextSize(200);
        this.textView.setGravity(Gravity.CENTER);
        this.textView.setLayoutParams(params);
        linearLayout.addView(this.textView);

        this.addContentView(linearLayout, params);

        GameActivity gameActivity = this;
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.timer_text_anim);
        new CountDownTimer(4000, 1000) {
            long number;

            public void onTick(long millisUntilFinished) {
                number = ((millisUntilFinished / 1000) + 1);
                if (number == 1) {
                    textView.setText(R.string.go);
                    textView.setTextColor(Color.GREEN);
                } else {
                    textView.setText(String.valueOf(number - 1));
                    switch ((int) number) {
                        case 4:
                            textView.setTextColor(Color.RED);
                            break;
                        case 3:
                            textView.setTextColor(getColor(R.color.orange));
                            break;
                        case 2:
                            textView.setTextColor(Color.YELLOW);
                            break;
                        default:
                            break;
                    }
                }
                textView.startAnimation(anim);
            }

            public void onFinish() {
                gv = new GameView(GameActivity.this, gameActivity);
                gv.setOnTouchListener(gameActivity);
                setContentView(gv);
            }
        }.start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            this.gv.startGame();
        }
        return true;
    }

    public void sendGameOverBallPosition(Ball gameOverPosition) {
        this.backToMainActivity(gameOverPosition);
    }

    public void backToMainActivity(Ball ball) {
        Intent intent = new Intent(this, MainActivity.class);
//      intent.putExtra("x", (int) ball.getX());
//      intent.putExtra("score", (scoreService.getHighScore().getScore() != null) ? scoreService.getHighScore().getScore().intValue() : 0);
        startActivity(intent);
    }
}