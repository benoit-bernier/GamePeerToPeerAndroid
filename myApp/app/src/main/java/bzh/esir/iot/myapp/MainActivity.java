package bzh.esir.iot.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    private int score=0;
    private final String[][] QuestionsReponses = new String[][] {
            new String[] {"Capitale de la France ?", "Paris", "Rennes", "Brest"},
            new String[] {"Route du ...", "Rhum", "Punch", "Whisky"}
    };
    private int compteurQuestions=0;

    //@Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Plutot choisir de créer une fonction "check reponse", qui prend la BDD et qui incrémente score en cas de bonne réponse.
        

    public void mauvaiseReponse(View view){
        //Intent intent = new Intent(this, mauvaiseReponse.class);
        //startActivity(intent);
        TextView question = findViewById(R.id.textView);
        question.setText(QuestionsReponses[compteurQuestions][0]);

        Button button4 = findViewById(R.id.button4);
        button4.setText(QuestionsReponses[compteurQuestions][1]);

        Button button5 = findViewById(R.id.button5);
        button5.setText(QuestionsReponses[compteurQuestions][2]);

        Button button6 = findViewById(R.id.button6);
        button6.setText(QuestionsReponses[compteurQuestions][3]);

        compteurQuestions++;
    }

    public void bonneReponse(View view){
        score++;
        TextView question = findViewById(R.id.textView);
        question.setText(QuestionsReponses[compteurQuestions][0]);

        Button button4 = findViewById(R.id.button4);
        button4.setText(QuestionsReponses[compteurQuestions][1]);

        Button button5 = findViewById(R.id.button5);
        button5.setText(QuestionsReponses[compteurQuestions][2]);

        Button button6 = findViewById(R.id.button6);
        button6.setText(QuestionsReponses[compteurQuestions][3]);

        compteurQuestions++;
    }
}
