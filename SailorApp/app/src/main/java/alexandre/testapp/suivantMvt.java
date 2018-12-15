package alexandre.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class suivantMvt extends AppCompatActivity{
    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";
    int score;
    private String choix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivant_sensor);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        score = intent.getIntExtra(EXTRA_SCORE, 0);
        choix=intent.getStringExtra(EXTRA_CHOIX);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView11);
        textView.setText("Votre score provisoire est de "+score);
    }

    public void SENSORsendQCM(View view) {
        Intent intent = new Intent(this, QCMActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        intent.putExtra(EXTRA_CHOIX, choix);
        startActivity(intent);
    }
}