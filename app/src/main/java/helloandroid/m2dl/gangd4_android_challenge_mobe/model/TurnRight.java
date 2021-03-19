package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.graphics.drawable.Icon;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Observer;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class TurnRight extends Orientation implements SensorEventListener {

    private ActionType actionType;
    private String icon;

    public TurnRight(GameActivity gameActivity) {
        super(gameActivity,"Turn tablet right", ActionType.TURN_RIGHT, "icon");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == SENSOR_ORIENTATION_TYPE
                && event.values[0] == RIGHT_ORIENTATION) {
            Log.i("Action","Orientation droite");
            setChanged();
            this.notifyObservers(true);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);

        SensorManager sensorManager = (SensorManager) gameActivity.getSystemService(gameActivity.SENSOR_SERVICE);
        setOrientationSensor(sensorManager.getDefaultSensor(SENSOR_ORIENTATION_TYPE));
        sensorManager.registerListener(this, getOrientationSensor(), SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        SensorManager sensorManager = (SensorManager) gameActivity.getSystemService(gameActivity.SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }
}
