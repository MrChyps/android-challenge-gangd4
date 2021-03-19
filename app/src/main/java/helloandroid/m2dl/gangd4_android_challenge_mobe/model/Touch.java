package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.graphics.drawable.Icon;
import android.view.MotionEvent;
import android.view.View;

import java.util.Observer;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class Touch extends TouchScreen {

    private boolean isWaitingClick = false;

    public Touch(GameActivity gameActivity) {
        super(gameActivity,"Tap the screen", ActionType.TOUCH,"icon");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        System.out.println("ACTION");
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            if(isWaitingClick) {
                System.out.println("TOUCHED WHILE WAITING");
                setChanged();
                this.notifyObservers(true);
            } else {
                System.out.println("TOUCHED BUT NOT WAITING");
            }
        }
        return true;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        System.out.println("Touch abonné");
        gameActivity.getGv().setOnTouchListener(this);
        isWaitingClick = true;
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        gameActivity.getGv().setOnTouchListener(null);
        isWaitingClick = false;
    }
}
