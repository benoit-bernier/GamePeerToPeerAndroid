package alexandre.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QCMActivity extends AppCompatActivity {
    private int scoreQCM = 0;
    private int score;
    private String choix;

    private long debut,fin;
    private final String[][] QuestionsReponses = new String[][]{
            new String[]{"Capitale de la France ?", "Paris", "Rennes", "Brest", "button4"},
            new String[]{"Route du ...", "Punch", "Rhum", "Whisky", "button5"},
            new String[]{"Une marée dure :", "5 heures", "6 heures", "8 heures", "button5"}
    };
    private int compteurQuestions = 0;

    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";


    //@Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcm);

        Intent intent = getIntent();
        score = intent.getIntExtra(suivantMvt.EXTRA_SCORE, 0);
        choix = intent.getStringExtra(suivantMvt.EXTRA_CHOIX);
        debut = SystemClock.elapsedRealtime();

        DialogFragment dialog = new RuleQCM();
        dialog.show(getSupportFragmentManager(), "RuleQCM");
    }

    @Override
    public void onBackPressed() {
        // on empêche le bouton retour
    }

    public void verif4(View view) {
        verifyReponse("button4");
    }
    public void verif5(View view) {
        verifyReponse("button5");
    }
    public void verif6(View view) {
        verifyReponse("button6");
    }

    public void verifyReponse(String PressedButton) {
        if (QuestionsReponses[compteurQuestions][4] == PressedButton) {
            scoreQCM++;
        }
        if (compteurQuestions<QuestionsReponses.length-1){
            compteurQuestions++;
            TextView question = findViewById(R.id.textView);
            question.setText(QuestionsReponses[compteurQuestions][0]);

            Button button4 = findViewById(R.id.button4);
            button4.setText(QuestionsReponses[compteurQuestions][1]);

            Button button5 = findViewById(R.id.button5);
            button5.setText(QuestionsReponses[compteurQuestions][2]);

            Button button6 = findViewById(R.id.button6);
            button6.setText(QuestionsReponses[compteurQuestions][3]);
        } else {
            fin=SystemClock.elapsedRealtime();
            Intent intent = new Intent(this, resultatActivity.class);
            intent.putExtra(EXTRA_SCORE, scoreQCM*33+score+1);
            // Vérif du choix ici, pour lancer l'activité adéquate.
            intent.putExtra(EXTRA_CHOIX, choix);
            startActivity(intent);
        }
    }
}
