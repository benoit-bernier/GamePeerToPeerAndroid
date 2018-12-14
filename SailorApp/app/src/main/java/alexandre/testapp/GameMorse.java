package alexandre.testapp;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameMorse extends AppCompatActivity {

    public static final String EXTRA_NOM = "nom";
    public static final String EXTRA_CHOIX = "choix";
    public static final String EXTRA_SCORE = "score";

    private GestureDetectorCompat mDetector;
    private final String message = "Message : ";
    private String morse = "";

    private String nameOfUser;
    private String choix;


    private long debut, fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_morse);

        Intent previousActivity = getIntent();
        nameOfUser = previousActivity.getStringExtra(suivantChoixMono.EXTRA_NOM);
        choix = previousActivity.getStringExtra("choix");

        mDetector = new GestureDetectorCompat(this, new GameMorse.MyGestureListener());
        debut=SystemClock.elapsedRealtime();
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
                Intent intent = new Intent(getApplicationContext(), suivantTouch.class);
                intent.putExtra(EXTRA_SCORE, (int) (fin-debut));
                intent.putExtra(EXTRA_NOM, nameOfUser);
                intent.putExtra(EXTRA_CHOIX, choix);

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
                Log.d("Durée : ", String.valueOf((int)fin-debut));
                intent.putExtra(EXTRA_SCORE, (int) (fin-debut));
                intent.putExtra(EXTRA_NOM, nameOfUser);
                intent.putExtra(EXTRA_CHOIX, choix);
                startActivity(intent);
            }
        }
    }
}
