package alexandre.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

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

    public void aleatGame(View view){
        Random rand = new Random();
        if (rand.nextInt(2)==0){ //nextInt(2) pour accéder au jeu peche aussi
            Intent intent = new Intent(getApplicationContext(), GameLumiere.class);
            intent.putExtra(EXTRA_SCORE, score);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), GamePeche.class);
            intent.putExtra(EXTRA_SCORE, score);
            startActivity(intent);
        }

    }
}
