package alexandre.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class suivantTouch extends AppCompatActivity {
    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";
    public static final String EXTRA_NOM = "nom";

    int score;

    private String nameOfUser, choix;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivant_touch);

        Intent previousActivity = getIntent();
        score = previousActivity.getIntExtra("score",0); //scoreTouch est le nom de l'extraIntent score
        nameOfUser = previousActivity.getStringExtra("nom");
        choix = previousActivity.getStringExtra("choix");

        TextView champs_score= findViewById(R.id.scoreTempTouch);
        champs_score.setText("Votre score est de "+score);
    }

    public void aleatGame(View view){
        /*Random rand = new Random();
        if (rand.nextInt(2)==0){ //nextInt(2) pour accéder au jeu peche aussi
            Intent intent = new Intent(getApplicationContext(), GameLumiere.class);
            intent.putExtra(EXTRA_SCORE, score);
            intent.putExtra(EXTRA_NOM, nameOfUser);
            intent.putExtra(EXTRA_CHOIX, choix);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), GamePeche.class);
            intent.putExtra(EXTRA_SCORE, score);
            intent.putExtra(EXTRA_NOM, nameOfUser);
            intent.putExtra(EXTRA_CHOIX, choix);
            startActivity(intent);
        }
        */

        Intent intent = new Intent(getApplicationContext(), suivantMvt.class);
        intent.putExtra(EXTRA_SCORE, score);
        intent.putExtra(EXTRA_NOM, nameOfUser);
        intent.putExtra(EXTRA_CHOIX, choix);

        startActivity(intent);

    }
}
