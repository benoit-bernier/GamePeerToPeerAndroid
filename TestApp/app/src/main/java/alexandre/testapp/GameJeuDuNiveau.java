package alexandre.testapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class GameJeuDuNiveau extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "GameJeuDuNiveau";
    private SensorManager mSensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_jeu_du_niveau);

        Log.d(TAG, "onCreate: Initialisation du sensor");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Initialisation OK !");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent sensorEvent){
        Log.d(TAG, "onSensorChanged: X:" + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);
        TextView xValues = findViewById(R.id.game_jeuduniveau_x);
        xValues.setText("xValue : " + sensorEvent.values[0]);
        TextView yValues = findViewById(R.id.game_jeuduniveau_y);
        yValues.setText("yValue : " + sensorEvent.values[1]);
        TextView zValues = findViewById(R.id.game_jeuduniveau_z);
        zValues.setText("zValue : " + sensorEvent.values[2]);
    }

}
