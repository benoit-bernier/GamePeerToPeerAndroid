package bzh.esir.iot.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    private int score=0;

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
        Button button = findViewById(R.id.button4); //TODO: trouver comment chopper le nom du bouton cliqué
        button.setText("faux");
    }

    public void bonneReponse(View view){
        score++;
        Intent intent = new Intent(this, bonneReponse.class);
        startActivity(intent);
        System.out.println(score);
    }
}
