package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class Point {

    private float x;
    private float y;
    private static final float DIFF_X_Y = 10;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point() {

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setXY(float x, float y) {
        setX(x);
        setY(y);
    }

    public boolean almostEquals(Point point) {
        return Math.abs(x - point.getX()) < Point.DIFF_X_Y && Math.abs(y - point.getY()) < Point.DIFF_X_Y;
    }

}