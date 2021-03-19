package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.hardware.Sensor;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public abstract class Orientation extends IAction {

    public static final int SENSOR_ORIENTATION_TYPE = 27;

    public static final float NORMAL_ORIENTATION = (float) 1.0;
    public static final float RIGHT_ORIENTATION = (float) 0.0;
    public static final float LEFT_ORIENTATION = (float) 2.0;

    private Sensor orientationSensor;
    public Orientation (GameActivity gameActivity, String description, ActionType actionType, String icon) {
        super(gameActivity, description, actionType, Type.INSTANT, icon);
    }

    public Sensor getOrientationSensor() {
        return orientationSensor;
    }

    public void setOrientationSensor(Sensor orientationSensor) {
        this.orientationSensor = orientationSensor;
    }
}
