package helloandroid.m2dl.gangd4_android_challenge_mobe.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextPaint;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import helloandroid.m2dl.gangd4_android_challenge_mobe.GameThread;
import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Ball;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Color;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

   private Boolean isLaunched;
   private final GameThread thread;
   private Ball ball;
   private Color backgroundColor;
   private Color ballColor;

   private List<Float> redBounds;
   private List<Float> yellowBounds;
   private List<Float> greenBounds;

   private long timePassed;
   private static final long SPEED_BOUND = 600;

   private final GameActivity gameActivity;
   private final TextPaint textPaint;

   public GameView(Context context, GameActivity gameActivity) {
      super(context);
      this.thread = new GameThread(getHolder(), this);

      this.redBounds = new ArrayList<>();
      this.yellowBounds = new ArrayList<>();
      this.greenBounds = new ArrayList<>();

      initGame(this.getContext());
      this.gameActivity = gameActivity;
      this.isLaunched = false;
      setFocusable(true);
      getHolder().addCallback(this);

      this.backgroundColor = new Color(255, 255, 255, 255);
      this.ballColor = new Color(255, 0, 0, 0);

      this.textPaint = new TextPaint();
      this.textPaint.setAntiAlias(true);
      this.textPaint.setTextAlign(Paint.Align.CENTER);
      this.textPaint.setTextSize(200);
      this.textPaint.setColor(android.graphics.Color.BLACK);
   }

   @Override
   public void draw(Canvas canvas) {
      super.draw(canvas);
      resetLists();
      if (canvas != null) {
         Paint paint = new Paint();
         canvas.drawARGB(this.backgroundColor.getAlpha(), this.backgroundColor.getRed(), this.backgroundColor.getGreen(), this.backgroundColor.getBlue());

         // Red zone
         paint.setColor(android.graphics.Color.RED);
         int vertFact = 150;
         int horFact = 100;
         float left = getWidth() / 2f;
         float top = (getHeight() / 2f) - vertFact;
         float right = (getWidth() / 4f) - horFact;
         float bottom = (getHeight() / 2f) + vertFact;
         canvas.drawRect(left, top, right, bottom, paint);
         redBounds.add(right);
         redBounds.add(left);

         // Yellow zone
         paint.setColor(android.graphics.Color.YELLOW);
         left -= horFact;
         top = (getHeight() / 2f) - vertFact;
         right += horFact;
         bottom = (getHeight() / 2f) + vertFact;
         canvas.drawRect(left, top, right, bottom, paint);
         yellowBounds.add(right);
         yellowBounds.add(left);

         // Green zone
         paint.setColor(android.graphics.Color.GREEN);
         left -= (horFact * 2);
         top = (getHeight() / 2f) - vertFact;
         right += (horFact * 2);
         bottom = (getHeight() / 2f) + vertFact;
         canvas.drawRect(left, top, right, bottom, paint);
         greenBounds.add(left);
         greenBounds.add(right);

         paint.setAntiAlias(true);
         paint.setStrokeWidth(6f);
         paint.setStyle(Paint.Style.STROKE);
         paint.setStrokeJoin(Paint.Join.ROUND);
         paint.setColor(android.graphics.Color.GRAY);
         canvas.drawLine(0, (int) top, getWidth(), (int) top, paint);
         canvas.drawLine(0, (int) bottom, getWidth(), (int) bottom, paint);

         paint.setARGB(this.ballColor.getAlpha(), this.ballColor.getRed(), this.ballColor.getGreen(), this.ballColor.getBlue());
         this.ball.draw(canvas, paint);
      }
   }

   private void resetLists() {
      this.redBounds.clear();
      this.yellowBounds.clear();
      this.greenBounds.clear();
   }

   public boolean checkIfInRed(float y) {
      if (redBounds.size() == 2 && yellowBounds.size() == 2) {
         return (y >= redBounds.get(0) && y < yellowBounds.get(0))
                 || (y >= yellowBounds.get(1) && y <= redBounds.get(1));
      }
      return false;
   }

   public boolean checkIfInYellow(float y) {
      if (yellowBounds.size() == 2 && greenBounds.size() == 2) {
         return (y >= yellowBounds.get(0) && y < greenBounds.get(0))
                 || (y >= greenBounds.get(1) && y <= yellowBounds.get(1));
      }
      return false;
   }

   public boolean checkIfInGreen(float y) {
      if (greenBounds.size() == 2) {
         return (y >= greenBounds.get(0) && y < greenBounds.get(1));
      }
      return false;
   }

   @Override
   public void surfaceCreated(SurfaceHolder holder) {
      this.thread.setRunning(true);
      if (this.thread.getState() == Thread.State.NEW) {
         this.thread.start();
      }
   }

   @Override
   public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
   }

   @Override
   public void surfaceDestroyed(SurfaceHolder holder) {
      boolean retry = true;
      while (retry) {
         try {
            thread.setRunning(false);
            thread.join();
         } catch (InterruptedException ignore) {
         }
         retry = false;
      }
   }

   /**
    * Initialise les paramètres de la partie.
    *
    * @param context
    */
   public Ball initGame(Context context) {
      WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
      Point screenSize = new Point();
      Display display = wm.getDefaultDisplay();
      display.getSize(screenSize);
      Ball oldBall = this.ball;
      this.ball = new Ball(screenSize.x, screenSize.y);
      this.isLaunched = false;
      return oldBall;
   }

   public void startGame() {
      this.isLaunched = true;
   }

   public void update(long elapsed) {
      this.ball.updateBall(elapsed /*+ (timePassed / 10) // TODO acceleration off for now */);
      if (this.ball.isBallOutOfBounds()) {
         Ball oldBall = initGame(this.getContext());
//         this.gameActivity.sendGameOverBallPosition(oldBall);
//         this.thread.setGameOver(true);
         Toast.makeText(this.getContext(), "Game over !", Toast.LENGTH_LONG).show();
      }
   }

   public Boolean getIsLaunched() {
      return this.isLaunched;
   }

   public void increaseScore() {
      if (this.timePassed <= SPEED_BOUND) {
         this.timePassed++;
      }
   }

}