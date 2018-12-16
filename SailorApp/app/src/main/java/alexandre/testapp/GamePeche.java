package alexandre.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Math.exp;

public class GamePeche extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "GamePeche";

    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";

    private SensorManager mSensorManager;
    private Sensor accelerometer;

    private String choix;
    private int score;

    private boolean arme,capture, found = false;
    private int compteur =0;
    private long debut,fin;
    private Handler myHandler;

    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            //mise en place d'un timer
            Random r = new Random();
            int time = r.nextInt(4  ) + 2;
            final CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
                public void onTick(long millisUntilFinished) {
                    if (arme && capture && !found){
                        arme = false;
                        capture = false;
                        found = true;
                        compteur +=1;
                        Random r = new Random();
                        int rand = r.nextInt(3 );
                        Log.d(TAG, "onTick : " + rand);
                        MediaPlayer mediaPlayerAmbiance;
                        switch (rand){
                            case 1 :
                                mediaPlayerAmbiance = MediaPlayer.create(GamePeche.this, R.raw.gull);
                                mediaPlayerAmbiance.start();
                                break;
                            case 2 :
                                mediaPlayerAmbiance = MediaPlayer.create(GamePeche.this, R.raw.gull2);
                                mediaPlayerAmbiance.start();
                                break;
                            default:
                                mediaPlayerAmbiance = MediaPlayer.create(GamePeche.this, R.raw.laught);
                                mediaPlayerAmbiance.start();
                        }
                    }
                }

                public void onFinish() {
                    TextView count = findViewById(R.id.game_jeuduniveau_compteur);
                    count.setText("Nombres de prises : " + String.valueOf(compteur));
                }
            };
            if (compteur < 5){
                countDownTimer.start(); //lancement du timer
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // init du vibreur
                v.vibrate(5*Math.round(exp(time)));
                arme = false;
                capture = false;
                found = false;
                myHandler.postDelayed(this,(time + 1) * 1000); // on relance le handler
            } else {
                TextView count = findViewById(R.id.game_jeuduniveau_compteur);
                count.setText("Woaw quel pêcheur !");
                MediaPlayer mediaPlayer = MediaPlayer.create(GamePeche.this, R.raw.clapping);
                mediaPlayer.start();

                fin=SystemClock.elapsedRealtime();
                Intent intent = (!choix.equals("entrainement")) ? (new Intent(getApplicationContext(), suivantMvt.class)) : (new Intent(getApplicationContext(), resultatActivity.class));
                int scoreSend = 140-(int)(fin-debut)/1000+score;
                if (scoreSend < 0){
                    scoreSend=0;
                } else if (scoreSend > 100){
                    scoreSend=1000;
                }
                intent.putExtra(EXTRA_SCORE, scoreSend);
                intent.putExtra(EXTRA_CHOIX, choix);

                startActivity(intent);
            }
        }
    };

    public void onPause() {
        super.onPause();
        if(myHandler != null)
            myHandler.removeCallbacks(myRunnable); // On arrete le callback
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_jeu_du_niveau);

        Intent previousActivity = getIntent();
        score = previousActivity.getIntExtra(suivantTouch.EXTRA_SCORE,0);
        choix = previousActivity.getStringExtra(suivantTouch.EXTRA_CHOIX);

        DialogFragment dialog = new RulePeche();
        dialog.show(getSupportFragmentManager(), "RulePeche");

        debut=SystemClock.elapsedRealtime();

        Log.d(TAG, "onCreate: Initialisation du sensor");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Initialisation OK !");
        //MediaPlayer mediaPlayer = MediaPlayer.create(GamePeche.this, R.raw.waves);
        //mediaPlayer.start();
        //initialisation de la boucle tout les x millisecondes
        myHandler = new Handler();
        myHandler.postDelayed(myRunnable,5000);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent sensorEvent){
        //Log.d(TAG, "onSensorChanged: X:" + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);
        if ((sensorEvent.values[1] > -2 && sensorEvent.values[1] < 2) && sensorEvent.values[0] > 8){
            arme = true;
        } else if (arme && ((sensorEvent.values[0] > -2 && sensorEvent.values[0] < 2) && sensorEvent.values[1] > 8)){
            capture = true;
        }

    }

}
