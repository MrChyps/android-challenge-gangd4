package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.graphics.drawable.Icon;
import android.view.MotionEvent;
import android.view.View;

import java.util.Observer;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class DrawCircle extends TouchScreen {

    private int nbMove;

    public DrawCircle(GameActivity gameActivity) {
        super(gameActivity,"Draw a Circle", ActionType.DRAW_CIRCLE, "icon");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println(motionEvent.getX());
                pointTouchDown.setXY(motionEvent.getX(), motionEvent.getY());
                System.out.println("Touchscreen ACTION_DOWN :    "+ pointTouchDown.getY() + pointTouchDown.getX());
                nbMove = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                nbMove++;
                System.out.println("Touchscreen ACTION_MOVE : "+ nbMove);
                break;
            case MotionEvent.ACTION_UP:
                pointTouchUp.setXY(motionEvent.getX(), motionEvent.getY());
                System.out.println("Touchscreen ACTION_UP : "+ pointTouchUp.getX() + pointTouchUp.getY());
                return isACircle();
        }
        return false;
    }

    public boolean isACircle() {
        System.out.println("Touchscreen isACircle : "+  (nbMove > TouchScreen.MIN_MOVE  && pointTouchDown.almostEquals(pointTouchUp)));
        return nbMove > TouchScreen.MIN_MOVE  && pointTouchDown.almostEquals(pointTouchUp);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);

    }
}
