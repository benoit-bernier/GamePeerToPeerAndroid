package alexandre.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static alexandre.testapp.LauncherP2P.MESSAGE_READ;


public class resultatActivity extends AppCompatActivity{

    private int score;
    private String nameOfUser, choix;
    Button buttonSend;
    TextView compteur_box;

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

        compteur_box = findViewById(R.id.readMsg3);


        buttonSend = findViewById(R.id.sendScore);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LauncherP2P.sendReceive.write((String.valueOf(score)).getBytes());
            }
        });
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_READ:
                    byte[] readBuff = (byte[]) msg.obj;
                    String tempMsg = new String(readBuff, 0, msg.arg1);
                    TextView scoreEnnemi = findViewById(R.id.scoreEnnemi);
                    scoreEnnemi.setText(tempMsg);
            }
            return true;
        }
    });
}