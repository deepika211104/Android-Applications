package com.example.stepcountandstepdetector;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.stepcountandstepdetector.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView tv1,tv2;
    SensorManager sensorManager;
    Sensor sensor1,sensor2;
    WebView wv1,wv2;

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
        wv1=(WebView)findViewById(R.id.wv1);
        wv2=(WebView)findViewById(R.id.wv2);
        sensorManager=(SensorManager) getSystemService((Context.SENSOR_SERVICE));
        sensor1=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensor2=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            int changedvalue1= (int) sensorEvent.values[0];
            tv1.setText("count: " +changedvalue1);
            wv2.loadUrl("https://api.thingspeak.com/update?api_key=13NK985PQTZGYQAY&field1="+changedvalue1);

        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            float changedvalue2 = sensorEvent.values[0];
            tv2.setText("Detector: " +changedvalue2);
            wv2.loadUrl("https://api.thingspeak.com/update?api_key=HA0IKDUM88XN7U7N&field2="+changedvalue2);
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