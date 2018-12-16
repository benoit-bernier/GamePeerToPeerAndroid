package alexandre.testapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameTapeLeClou extends AppCompatActivity {
    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";

    private MediaPlayer mediaPlayer;

    private String choix;

    private int compteur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent previousActivity = getIntent();
        choix = previousActivity.getStringExtra("choix");

        DialogFragment dialog = new RuleTapTap();
        dialog.show(getSupportFragmentManager(), "RuleTapTap");

        setContentView(R.layout.activity_game_tape_le_clou);
    }

    final CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            Intent intent = (!choix.equals("entrainement")) ? (new Intent(getApplicationContext(), suivantTouch.class)) : (new Intent(getApplicationContext(), resultatActivity.class));
            intent.putExtra(EXTRA_SCORE, compteur);
            intent.putExtra(EXTRA_CHOIX, choix);
            mediaPlayer.stop();
            startActivity(intent);
        }
    };

    /** Called when the hammer is pressed */
    public void imagePressed(View view) {
        mediaPlayer = MediaPlayer.create(GameTapeLeClou.this, R.raw.marteau);
        mediaPlayer.start();
        if (compteur == 0){
            countDownTimer.start();
        }
        compteur++;
        TextView score = findViewById(R.id.game_tapeleclou_score);
        score.setText("score : " + compteur);
        }

}
