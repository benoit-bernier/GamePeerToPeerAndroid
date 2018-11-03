package alexandre.testapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GameJeuDuHulk extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "GameJeuDuHulk";
    private SensorManager mSensorManager;
    private Sensor myLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_jeu_du_hulk);

        Log.d(TAG, "onCreate: Initialisation du sensor");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, myLight, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Initialisation OK !");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent sensorEvent){
        Log.d(TAG, "puissance lumière : " + sensorEvent.values[0]);
        TextView xValues = findViewById(R.id.game_jeuduhulk);
        xValues.setText("IT'S OVER  " + sensorEvent.values[0] +" !!");
    }
}
