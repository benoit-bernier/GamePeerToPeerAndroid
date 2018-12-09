package alexandre.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class resultatActivity extends AppCompatActivity{
    private int score;
    private String nameOfUser, choix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        score = intent.getIntExtra(QCMActivity.EXTRA_SCORE, -1);
        nameOfUser = intent.getStringExtra(QCMActivity.EXTRA_NOM);
        choix = intent.getStringExtra(QCMActivity.EXTRA_CHOIX);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView4);
        textView.setText("Bravo "+nameOfUser+", votre score est de "+score+" en mode "+choix);
    }
}