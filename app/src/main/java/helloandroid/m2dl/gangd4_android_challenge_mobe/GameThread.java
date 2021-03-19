package helloandroid.m2dl.gangd4_android_challenge_mobe;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import helloandroid.m2dl.gangd4_android_challenge_mobe.views.GameView;

public class GameThread extends Thread {

   private final SurfaceHolder surfaceHolder;
   private final GameView gameView;
   private boolean running;
   private boolean gameOver;

   public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
      super();
      this.surfaceHolder = surfaceHolder;
      this.gameView = gameView;
      this.gameOver = false;
   }

   public void setRunning(boolean isRunning) {
      running = isRunning;
   }

   @Override
   public void run() {
      long time = System.currentTimeMillis();
      while (running) {
         if (!gameOver || gameView.getIsLaunched()) {
            Canvas canvas = null;
            try {
               canvas = this.surfaceHolder.lockCanvas();
               long now = System.currentTimeMillis();
               long elapsed = now - time;
               synchronized (surfaceHolder) {
                  this.gameView.update(elapsed);
                  this.gameView.draw(canvas);
               }
//               this.gameView.increaseScore();
               time = now;
               Thread.sleep(5);
            } catch (Exception e) {
               e.printStackTrace();
            } finally {
               if (canvas != null) {
                  try {
                     surfaceHolder.unlockCanvasAndPost(canvas);
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
               }
            }
         }
      }
   }

   public void setGameOver(boolean gameOver) {
      this.gameOver = gameOver;
   }
}
