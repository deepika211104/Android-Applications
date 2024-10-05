package com.example.lightandproximity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView tv1,tv2;
    SensorManager sensorManager;
    Sensor sensor1,sensor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv1=(TextView) findViewById(R.id.tv1);
        tv2=(TextView) findViewById(R.id.tv2);
        sensorManager=(SensorManager) getSystemService((Context.SENSOR_SERVICE));
        sensor1=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor2=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            int changedvalue1= (int) sensorEvent.values[0];
            tv1.setText("Pressure: " +changedvalue1);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float changedvalue2 = sensorEvent.values[0];
            tv2.setText("proximity: " + changedvalue2);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        super.onResume();
        if (sensor1 != null) {
            sensorManager.registerListener(this, sensor1, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensor2 != null) {
            sensorManager.registerListener(this, sensor2, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this,sensor1);
        sensorManager.unregisterListener(this,sensor2);
    }
}