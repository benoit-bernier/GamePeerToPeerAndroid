package bzh.esir.iot.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class mauvaiseReponse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonne_reponse);
        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mauvaise_reponse);
        Button button = findViewById(R.id.button4);
        button.setTextColor(0x00ff00);
    /*
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
    */
    }
}
