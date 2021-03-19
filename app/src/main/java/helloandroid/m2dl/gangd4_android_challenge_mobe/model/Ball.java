package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.graphics.drawable.Drawable;

public class Ball extends Sprite {

   private Drawable icon;

   public Ball(int width, int height) {
      super(width, height);
   }

   public void updateBall(long elapsed) {
      float x = getX();
      x -= elapsed;
      setX(x);
   }

   public boolean isBallOutOfBounds() {
      return (getX() - Sprite.RADIUS <= 0
              || getX() + Sprite.RADIUS >= getWidth()
              || getY() - Sprite.RADIUS <= 0
              || getY() + Sprite.RADIUS >= getHeight());
   }
}

