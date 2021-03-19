package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class Color {
   private int alpha;
   private int red;
   private int green;
   private int blue;

   public Color(int alpha, int red, int green, int blue) {
      this.alpha = alpha;
      this.red = red;
      this.green = green;
      this.blue = blue;
   }

   public int getAlpha() {
      return alpha;
   }

   public int getRed() {
      return red;
   }

   public int getGreen() {
      return green;
   }

   public int getBlue() {
      return blue;
   }

   public void setAlpha(int alpha) {
      if (alpha < 0) {
         alpha = 0;
      } else if (alpha > 255) {
         alpha = 255;
      }
      this.alpha = alpha;
   }
}
