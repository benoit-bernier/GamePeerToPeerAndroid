package alexandre.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class suivantTouch extends AppCompatActivity {
    public static final String EXTRA_SCORE = "ScoreAfterTouch";
    int score;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivant_touch);
        Intent previousActivity = getIntent();
        score = previousActivity.getIntExtra("scoreTouch",0); //scoreTouch est le nom de l'extraIntent score
        TextView champs_score= findViewById(R.id.scoreTempTouch);
        champs_score.setText("Votre score est de "+score);
    }

    public void sendJEU1(View view) {
        Intent intent = new Intent(this, GameLumiere.class);
        intent.putExtra(EXTRA_SCORE, score);
        startActivity(intent);
    }

    public void sendJEU2(View view) {
        Intent intent = new Intent(this, GamePeche.class);
        intent.putExtra(EXTRA_SCORE, score);
        startActivity(intent);
    }
}
