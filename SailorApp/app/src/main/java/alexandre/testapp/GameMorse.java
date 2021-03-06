package alexandre.testapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameMorse extends AppCompatActivity {

    public static final String EXTRA_CHOIX = "choix";
    public static final String EXTRA_SCORE = "score";

    private GestureDetectorCompat mDetector;
    private final String message = "Message : ";
    private String morse = "";

    private String choix;

    private MediaPlayer mediaPlayer;

    private long debut, fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_morse);

        Intent previousActivity = getIntent();
        choix = previousActivity.getStringExtra("choix");

        mediaPlayer = MediaPlayer.create(GameMorse.this, R.raw.morse);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        DialogFragment dialog = new RuleMorse();
        dialog.show(getSupportFragmentManager(), "RuleMorse");

        mDetector = new GestureDetectorCompat(this, new GameMorse.MyGestureListener());
        debut=SystemClock.elapsedRealtime();
    }

    @Override
    public void onBackPressed() {
        // on empêche le bouton retour
    }

    public void restart(View view){
        morse = "";
        TextView champs_message = findViewById(R.id.game_morse_message);
        champs_message.setText(message+morse);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Pressure";
        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
            TextView champs_type = findViewById(R.id.game_morse_type);
            champs_type.setText("Last symbol : .");
            TextView champs_message = findViewById(R.id.game_morse_message);
            morse += ".";
            champs_message.setText(message+morse);
            if(morse.equals("...___...")){ // == ne fonctionne pas
                fin =SystemClock.elapsedRealtime();
                Log.d("Durée : ", String.valueOf((int)fin-debut));
                Intent intent = (!choix.equals("entrainement")) ? (new Intent(getApplicationContext(), suivantTouch.class)) : (new Intent(getApplicationContext(), resultatActivity.class));
                int scoreSend = 110-(int)(fin-debut)/1000;
                if (scoreSend < 0){
                    scoreSend=0;
                } else if (scoreSend > 100){
                    scoreSend=100;
                }
                Log.d("Durée : ", String.valueOf(scoreSend));
                intent.putExtra(EXTRA_SCORE, scoreSend);
                intent.putExtra(EXTRA_CHOIX, choix);
                mediaPlayer.stop();
                startActivity(intent);
            }
            return true;
        }
        @Override
        public void onLongPress(MotionEvent event) {
            Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
            TextView champs_type = findViewById(R.id.game_morse_type);
            champs_type.setText("Last symbol : _");
            TextView champs_message = findViewById(R.id.game_morse_message);
            morse += "_";
            champs_message.setText(message+morse);
            if(morse.equals("...___...")){
                fin=SystemClock.elapsedRealtime();
                Intent intent = new Intent(getApplicationContext(), suivantTouch.class);
                int scoreSend = 100-(int)(2*(fin-debut))/1000;
                if (scoreSend < 0){
                    scoreSend=0;
                } else if (scoreSend > 100){
                    scoreSend=100;
                }
                Log.d("Durée : ", String.valueOf(scoreSend));
                intent.putExtra(EXTRA_SCORE, scoreSend);
                intent.putExtra(EXTRA_CHOIX, choix);
                startActivity(intent);
            }
        }
    }
}
