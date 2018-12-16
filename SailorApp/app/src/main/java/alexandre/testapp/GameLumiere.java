package alexandre.testapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GameLumiere extends AppCompatActivity implements SensorEventListener {

    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";

    private String choix;

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

        DialogFragment dialog = new RuleFlashlight();
        dialog.show(getSupportFragmentManager(), "RuleFlashlight");

        debut=SystemClock.elapsedRealtime();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, myLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent sensorEvent){
        Log.d("capteur lum", "Puissance lumière : " + sensorEvent.values[0]);
        TextView xValues = findViewById(R.id.game_jeuduhulk);
        xValues.setText("Tu es à " + sensorEvent.values[0] +", il faut 2500 pour pouvoir voir tous les détails!");

        TextView opacity = findViewById(R.id.textView6);
        opacity.getBackground().setAlpha(255 - Math.round(sensorEvent.values[0])/10);

        if (sensorEvent.values[0]>2550){
            fin = SystemClock.elapsedRealtime();
            Intent intent = (!choix.equals("entrainement")) ? (new Intent(getApplicationContext(), suivantMvt.class)) : (new Intent(getApplicationContext(), resultatActivity.class));
            int scoreSend = 105-(int)(fin-debut)/1000+scoreInit;
            if (scoreSend < 0){
                scoreSend=0;
            } else if (scoreSend > 100){
                scoreSend=100;
            }
            intent.putExtra(EXTRA_SCORE, scoreSend+scoreInit);
            intent.putExtra(EXTRA_CHOIX, choix);
            mSensorManager.unregisterListener(this, myLight);
            startActivity(intent);
        }
    }
}
