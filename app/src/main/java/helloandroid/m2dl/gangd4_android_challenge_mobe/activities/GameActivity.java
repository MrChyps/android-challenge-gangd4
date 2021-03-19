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

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import helloandroid.m2dl.gangd4_android_challenge_mobe.model.ActionType;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Ball;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.IAction;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.MaskScreen;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Touch;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.TurnLeft;
import helloandroid.m2dl.gangd4_android_challenge_mobe.views.GameView;

public class GameActivity extends Activity implements View.OnTouchListener, Observer {


   private GameView gv;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              WindowManager.LayoutParams.FLAG_FULLSCREEN);
      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      this.setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);     //  Fixed Portrait orientation

//      SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//      Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//      sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
//
//      Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//      sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);


      SharedPreferences.Editor editor = this.getPreferences(MODE_PRIVATE).edit();
      editor.putFloat("max_light_val", 1);
      editor.apply();

      this.gv = new GameView(this, this);
      this.gv.setOnTouchListener(this);
      setContentView(this.gv);
   }

   @Override
   public boolean onTouch(View view, MotionEvent motionEvent) {
      if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
         this.gv.startGame();
      }
      IAction turnLeft = new MaskScreen(this);
      turnLeft.addObserver(this);
      return true;
   }

   public void sendGameOverBallPosition(Ball gameOverPosition) {
      this.backToMainActivity(gameOverPosition);
   }

   public void backToMainActivity(Ball ball) {
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
   }

   @Override
   public void update(Observable o, Object arg) {
      System.out.println("RESULT SUCCESSSS ? " + arg);
//      if(((IAction)o).getType() == currentAction.getType()){
//         defineTiming(); //position de la balle et où elle est
//         currentAction.deleteObserver(this);
//         currentAction = getNextAction();
//         currentAction.addObserver(this);
      // Vibrer
//      }
      switch (((IAction)o).getActionType()) {
         case TURN_LEFT:
            System.out.println("SUCCESSSSSS");
            o.deleteObserver(this);;
            this.gv.setOnTouchListener(null);
            IAction touch = new Touch(this);
            touch.addObserver(this);
            break;
         case TOUCH:
            System.out.println("SUCCESSSSSS V22222");
            o.deleteObserver(this);
            break;
         case MASK_SCREEN:
            System.out.println("SUCCESSSSSS");
            o.deleteObserver(this);
      }
   }

   public GameView getGv() {
      return gv;
   }
}