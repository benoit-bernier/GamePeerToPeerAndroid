package alexandre.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameTapeLeClou extends AppCompatActivity {

    private int compteur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_tape_le_clou);
    }

    /** Called when the hammer is pressed */
    public void imagePressed(View view) {
        compteur++;
        TextView score = findViewById(R.id.game_tapeleclou_score);
        score.setText("score : " + compteur);
    }
}
