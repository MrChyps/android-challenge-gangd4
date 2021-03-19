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

   private boolean cycleStart;

   private enum Scores {
      SUPER(3),
      GOOD(2),
      CLOSE(1);

      private final int value;

      Scores(int value) {
         this.value = value;
      }

      public int getValue() {
         return value;
      }
   }

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
      this.cycleStart = true;
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
         boolean isRed = this.cycleStart && this.gv.checkIfInRed();
         if (isRed) {
            this.gv.addScore(Scores.CLOSE.getValue());
            this.cycleStart = false;
            this.startNewCycle();
         }

         boolean isYellow = this.cycleStart && this.gv.checkIfInYellow();
         if (isYellow) {
            this.gv.addScore(Scores.GOOD.getValue());
            this.cycleStart = false;
            this.startNewCycle();
         }

         boolean isGreen = this.cycleStart && this.gv.checkIfInGreen();
         if (isGreen) {
            this.gv.addScore(Scores.SUPER.getValue());
            this.cycleStart = false;
            this.startNewCycle();
         }

         if (!isRed && !isYellow && !isGreen) {
            this.cycleStart = false;
            this.backToEndGameActivity(null);
         }
      }
      return true;
   }

   public void startNewCycle() {
      this.gv.initGame(this.gv.getContext());
      this.cycleStart = true;
   }

   public void backToEndGameActivity(Ball ball) {
      Intent intent = new Intent(this, EndGameActivity.class);
      intent.putExtra("user_score", this.gv.getUserScore());
      startActivity(intent);
   }

   public void setCycleStart(boolean cycleStart) {
      this.cycleStart = cycleStart;
   }
}