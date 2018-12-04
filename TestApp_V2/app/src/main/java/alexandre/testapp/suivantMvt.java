package alexandre.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class suivantMvt extends AppCompatActivity{
    public static final String EXTRA_SCORE = "scoreAfterSensor";
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivant_sensor);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        score = intent.getIntExtra(EXTRA_SCORE, 0);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView11);
        textView.setText("Votre score est de "+score);
    }

    public void SENSORsendQCM(View view) {
        Intent intent = new Intent(this, QCMActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        startActivity(intent);
    }
}