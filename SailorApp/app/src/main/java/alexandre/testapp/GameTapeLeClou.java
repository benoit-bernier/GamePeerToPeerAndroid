package alexandre.testapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Math.exp;

public class GameTapeLeClou extends AppCompatActivity {
    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";

    private String choix;

    private int compteur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent previousActivity = getIntent();
        choix = previousActivity.getStringExtra("choix");

        setContentView(R.layout.activity_game_tape_le_clou);
        countDownTimer.start();
    }

    final CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            Intent intent = new Intent(getApplicationContext(), suivantTouch.class);
            intent.putExtra(EXTRA_SCORE, compteur);
            intent.putExtra(EXTRA_CHOIX, choix);
            startActivity(intent);
        }
    };

    /** Called when the hammer is pressed */
    public void imagePressed(View view) {
        compteur++;
        TextView score = findViewById(R.id.game_tapeleclou_score);
        score.setText("score : " + compteur);
        }

}
