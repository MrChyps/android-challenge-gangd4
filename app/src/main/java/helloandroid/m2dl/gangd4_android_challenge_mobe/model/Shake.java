package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Observer;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class Shake extends IAction implements SensorEventListener {
    private static final float MIN_SHAKE_SPEED = 200;

    private float lastX = -0.1F;
    private float lastY = -0.1F;
    private float lastZ = -0.1F;
    private long lastTime;

    public Shake(GameActivity gameActivity) {
        super(gameActivity, "Shake the tablet", ActionType.SHAKE, Type.INSTANT, "icon");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long now = System.currentTimeMillis();
            if ((now - lastTime) > 100) {
                long diffTime = (now - lastTime);

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float speed = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000;

                Log.d("sensor", "shake detected w/ speed: " + speed);
                if (speed > MIN_SHAKE_SPEED) {
                    Log.i("Action", "Tablette secouée");
                    setChanged();
                    this.notifyObservers(true);
                }
                lastX = x;
                lastY = y;
                lastZ = z;
                lastTime = now;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        SensorManager sensorManager = (SensorManager) gameActivity.getSystemService(gameActivity.SENSOR_SERVICE);
        Sensor accelerometer = (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        SensorManager sensorManager = (SensorManager) gameActivity.getSystemService(gameActivity.SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }
}
