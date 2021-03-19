package helloandroid.m2dl.gangd4_android_challenge_mobe.model;


import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Observer;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public abstract class TouchScreen extends IAction implements View.OnTouchListener {

    public static final int MIN_MOVE = 30;
    protected Point pointTouchDown;
    protected Point pointTouchUp;
    protected GestureDetector gestureDetector;

    public TouchScreen(GameActivity gameActivity, String description, ActionType actionType, String icon) {
        super(gameActivity, description, actionType, Type.INSTANT, icon);
        pointTouchDown = new Point();
        pointTouchUp = new Point();
        gestureDetector = new GestureDetector(gameActivity, new GestureListener());
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        System.out.println("Touch abonné");
        gameActivity.getGv().setOnTouchListener(this);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        gameActivity.getGv().setOnTouchListener(null);
    }

    protected final class GestureListener extends
            GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onClick();
            return super.onSingleTapUp(e);
        }
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }
        @Override
        public void onLongPress(MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                }
                else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeDown();
                        } else {
                            onSwipeUp();
                        }
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return false;
        }
    }

    protected void onClick() {};

    protected  void onDoubleClick(){};

    protected  void onLongClick(){};

    protected  void onSwipeRight() {};

    protected  void onSwipeLeft() {};

    protected  void onSwipeDown() {};

    protected  void onSwipeUp() {};

}
