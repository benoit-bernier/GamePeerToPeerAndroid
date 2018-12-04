package alexandre.testapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GameLumiere extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "GameLumiere";
    private static final String EXTRA_SCORE = "scoreAfterSensor";
    private SensorManager mSensorManager;
    private Sensor myLight;
    private int scoreInit;
    private long debut;
    private long fin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_jeu_du_hulk);

        Intent previousActivity = getIntent();
        scoreInit = previousActivity.getIntExtra(suivantTouch.EXTRA_SCORE,0);
        debut=SystemClock.elapsedRealtime();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, myLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent sensorEvent){
        //Log.d(TAG, "Puissance lumière : " + sensorEvent.values[0]);
        TextView xValues = findViewById(R.id.game_jeuduhulk);
        xValues.setText("IT'S OVER  " + sensorEvent.values[0] +" !!");
        if (sensorEvent.values[0]>2500){
            fin = SystemClock.elapsedRealtime();
            Intent intent = new Intent(getApplicationContext(), suivantMvt.class);
            intent.putExtra(EXTRA_SCORE, (int)(fin-debut)+scoreInit);
            startActivity(intent);
        }
    }
}
