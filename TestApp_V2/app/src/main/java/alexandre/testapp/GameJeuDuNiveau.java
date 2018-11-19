package alexandre.testapp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Math.exp;

public class GameJeuDuNiveau extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "GameJeuDuNiveau";
    private SensorManager mSensorManager;
    private Sensor accelerometer;

    private boolean arme = false;
    private boolean capture = false;
    private boolean found = false;
    private int compteur =0;
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
                        MediaPlayer mediaPlayer;
                        switch (rand){
                            case 1 :
                                mediaPlayer = MediaPlayer.create(GameJeuDuNiveau.this, R.raw.gull);
                                mediaPlayer.start();
                                break;
                            case 2 : mediaPlayer = MediaPlayer.create(GameJeuDuNiveau.this, R.raw.gull2);
                                mediaPlayer.start();
                                break;
                            default: mediaPlayer = MediaPlayer.create(GameJeuDuNiveau.this, R.raw.laught);
                                mediaPlayer.start();
                        }
                    }
                }

                public void onFinish() {
                    TextView count = findViewById(R.id.game_jeuduniveau_compteur);
                    count.setText("Prises : " + String.valueOf(compteur));
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
                MediaPlayer mediaPlayer = MediaPlayer.create(GameJeuDuNiveau.this, R.raw.clapping);
                mediaPlayer.start();
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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Log.d(TAG, "onCreate: Initialisation du sensor");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Initialisation OK !");
        MediaPlayer mediaPlayer = MediaPlayer.create(GameJeuDuNiveau.this, R.raw.waves);
        mediaPlayer.start();
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
