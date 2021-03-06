
package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.ActionStop;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.ActionType;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Ball;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.IAction;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.MaskScreen;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Shake;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.SwipeLeft;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.SwipeRight;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Touch;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.TurnLeft;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.TurnRight;
import helloandroid.m2dl.gangd4_android_challenge_mobe.views.GameView;

public class GameActivity extends Activity implements View.OnTouchListener, Observer {

   private GameView gv;
   private Sensor lightSensor;
   private TextView textView;
   private Vibrator vibrator;

   private boolean cycleStart;

   private List<IAction> actionsQueue;

   public enum Scores {
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

      this.actionsQueue = new ArrayList<>();

      this.initListOfRandomActions(10);

      SharedPreferences.Editor editor = this.getPreferences(MODE_PRIVATE).edit();
      editor.putFloat("max_light_val", 1);
      editor.apply();

      this.vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);

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
            actionsQueue.get(0).addObserver(GameActivity.this);
            gv.setDescription(actionsQueue.get(0).getDescription());
            System.out.println(actionsQueue.get(0).getDescription());
            setContentView(gv);
         }
      }.start();
      this.cycleStart = true;
   }

   @Override
   public boolean onTouch(View view, MotionEvent motionEvent) {
      if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
         this.gv.startGame();

         boolean isRed = this.cycleStart && this.gv.checkIfInRed();
         if (isRed) {
            this.gv.addScore(Scores.CLOSE.getValue());
            vibrator.vibrate(200);
            this.cycleStart = false;
            this.startNewCycle();
         }

         boolean isYellow = this.cycleStart && this.gv.checkIfInYellow();
         if (isYellow) {
            this.gv.addScore(Scores.GOOD.getValue());
            vibrator.vibrate(200);
            this.cycleStart = false;
            this.startNewCycle();
         }

         boolean isGreen = this.cycleStart && this.gv.checkIfInGreen();
         if (isGreen) {
            this.gv.addScore(Scores.SUPER.getValue());
            vibrator.vibrate(200);
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
      ViewGroup vg = (ViewGroup) (gv.getParent());
      vg.removeView(gv);
   }

   public void setCycleStart(boolean cycleStart) {
      this.cycleStart = cycleStart;
   }

   private void initListOfRandomActions(int number) {
      Random rand = new Random();
      IAction action = null;
      for (int i = 0; i < number; i++) {
         int index = rand.nextInt(8);
         ActionType type = ActionType.values()[index];
         switch (type) {
            case SWIPE_RIGHT:
               action = new SwipeRight(this);
               break;
            case SWIPE_LEFT:
               action = new SwipeLeft(this);
               break;
            case TOUCH:
               action = new Touch(this);
               break;
            case TURN_RIGHT:
               action = new TurnRight(this);
               break;
            case TURN_LEFT:
               action = new TurnLeft(this);
               break;
            case SHAKE:
               action = new Shake(this);
               break;
            case MASK_SCREEN:
               action = new MaskScreen(this);
               break;
            case STOP:
               action = new ActionStop(this);
               break;
            default:
               break;
         }
         this.actionsQueue.add(action);
      }
   }

   public IAction popActionFromList() {
      IAction action = null;
      if (!this.actionsQueue.isEmpty()) {
         action = this.actionsQueue.get(0);
         System.out.println("POPPED : " + action);
         this.actionsQueue.remove(0);
      }
      if (this.actionsQueue.isEmpty()) {
         this.initListOfRandomActions(10);
      }
      return action;
   }

   @Override
   public void update(Observable o, Object arg) {
      System.out.println("RESULT SUCCESSSS ? " + arg);
      IAction currentAction = this.popActionFromList();
      if (((IAction) o).getType() == currentAction.getType()) {
         System.out.println(actionsQueue.get(0).getActionType());
         if (currentAction.getType().equals(ActionType.STOP)) {
            gv.die();
         } else {
            defineTiming(currentAction); // position de la balle et o?? elle est
            currentAction.deleteObserver(this);
            currentAction = this.actionsQueue.get(0);
            currentAction.addObserver(this);
            this.gv.setDescription(currentAction.getDescription());
            // Vibrer
         }
      }
   }

   private void defineTiming(IAction currentAction) {
      this.gv.setDescription(currentAction.getDescription());
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

   public List<IAction> getActionsQueue() {
      return actionsQueue;
   }

   public GameView getGv() {
      return gv;
   }

}
