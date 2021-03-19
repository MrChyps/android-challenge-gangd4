package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Observer;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class MaskScreen extends IAction implements SensorEventListener {

    private static final float MAX_LIGHT_BOUND = 60;

    public MaskScreen(GameActivity gameActivity) {
        super(gameActivity, "Mask the front camera", ActionType.MASK_SCREEN, Type.INSTANT, "icon");
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);

        SensorManager sensorManager = (SensorManager) gameActivity.getSystemService(gameActivity.SENSOR_SERVICE);
        Sensor lightSensor = (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        SensorManager sensorManager = (SensorManager) gameActivity.getSystemService(gameActivity.SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT
                && event.values[0] <= MAX_LIGHT_BOUND) {
            Log.i("Action","Ecran masqué");
            setChanged();
            this.notifyObservers(true);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
