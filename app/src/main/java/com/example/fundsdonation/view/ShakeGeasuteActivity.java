package com.example.fundsdonation.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fundsdonation.R;
import com.example.fundsdonation.ShakeDetector;

public class ShakeGeasuteActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "ShakeGeasuteActivity";
    private SensorManager sensorManager;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_geasute);

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                Log.d(TAG,"onShake: "+count);
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
                handleShakeEvent(count);
            }
        });
    }

    private void handleShakeEvent(int count) {
        Log.d(TAG, "shake count: "+count);

    }

    class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
           // Calling Surya
            switch (v.getId())
            {
                case R.id.adjust_height:
                    break;
                case R.id.adjust_width:
                    // I am doing my code Kalyan
                    break;
            }

        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}
