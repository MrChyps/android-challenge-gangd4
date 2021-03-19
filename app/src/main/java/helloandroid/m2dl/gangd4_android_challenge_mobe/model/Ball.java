package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class Ball extends Sprite {

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

