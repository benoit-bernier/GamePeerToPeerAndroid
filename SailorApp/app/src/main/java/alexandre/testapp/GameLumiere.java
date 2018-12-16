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

    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";

    private String nameOfUser, choix;

    private SensorManager mSensorManager;
    private Sensor myLight;
    private int scoreInit;
    private long debut, fin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_jeu_du_hulk);

        Intent previousActivity = getIntent();
        scoreInit = previousActivity.getIntExtra(suivantTouch.EXTRA_SCORE,0);
        choix = previousActivity.getStringExtra(EXTRA_CHOIX);

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
            Intent intent = (!choix.equals("entrainement")) ? (new Intent(getApplicationContext(), suivantMvt.class)) : (new Intent(getApplicationContext(), resultatActivity.class));
            intent.putExtra(EXTRA_SCORE, (int)(fin-debut)+scoreInit);
            intent.putExtra(EXTRA_CHOIX, choix);

            startActivity(intent);
        }
    }
}
