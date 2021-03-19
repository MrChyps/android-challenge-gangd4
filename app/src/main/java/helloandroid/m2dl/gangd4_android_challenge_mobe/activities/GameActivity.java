package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Ball;
import helloandroid.m2dl.gangd4_android_challenge_mobe.views.GameView;

public class GameActivity extends Activity implements SensorEventListener, View.OnTouchListener {

   private GameView gv;
   private Sensor lightSensor;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      this.setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);     //  Fixed Portrait orientation

      SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
      Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
      sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);

      Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
      sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);

      SharedPreferences.Editor editor = this.getPreferences(MODE_PRIVATE).edit();
      editor.putFloat("max_light_val", 1);
      editor.apply();

      this.gv = new GameView(this, this);
      this.gv.setOnTouchListener(this);
      setContentView(this.gv);
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