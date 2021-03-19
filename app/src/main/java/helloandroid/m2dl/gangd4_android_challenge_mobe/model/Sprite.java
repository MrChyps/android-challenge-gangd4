package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Sprite {
   public static final int RADIUS = 35;

   private float x;
   private float y;

   private final int width;
   private final int height;

   public Sprite(int width, int height) {
      this.width = width;
      this.height = height;
      this.x = width - (RADIUS * 3);
      this.y = height / 2f;
   }

   public void draw(Canvas canvas, Paint paint) {
      canvas.drawCircle(x, y, RADIUS * 2, paint);
   }

   public int getWidth() {
      return width;
   }

   public int getHeight() {
      return height;
   }

   public float getX() {
      return x;
   }

   public void setX(float x) {
      if (x > this.width + RADIUS) {
         x = this.width - RADIUS;
      } else if (x < RADIUS) {
         x = RADIUS;
      }
      this.x = x;
   }

   public float getY() {
      return y;
   }

}