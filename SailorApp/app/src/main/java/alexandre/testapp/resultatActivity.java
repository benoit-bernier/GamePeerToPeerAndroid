package alexandre.testapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultatActivity extends AppCompatActivity{

    public static final String EXTRA__CHOIX = "choix";
    private int score;
    private String choix;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        score = intent.getIntExtra(QCMActivity.EXTRA_SCORE, -1);
        choix = intent.getStringExtra(QCMActivity.EXTRA_CHOIX);
        //load the correct layout
        if (choix.equals("multijoueur")){
            setContentView(R.layout.activity_resultat);
        } else {
            setContentView(R.layout.activity_resultat_solo);
        }

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView4);
        textView.setText("Bravo "+EnterName.myName+", votre score est de "+score);
        if (choix.equals("multijoueur")){

            DialogFragment dialog = new RuleScore();
            dialog.show(getSupportFragmentManager(), "RuleScore");

            final TextView textView2 = findViewById(R.id.textView12);
            final Button button = findViewById(R.id.button8);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    textView2.setText("score envoyé... En attente du score de votre adversaire...");
                    String msg = String.valueOf(score);
                    try {
                        LauncherP2P.sendReceive.write(msg.getBytes());

                    } catch (NullPointerException e) {
                        textView2.setText("Oups ! Vous n'êtes plus appareillé avec votre ami !");
                        mediaPlayer = MediaPlayer.create(resultatActivity.this, R.raw.looser);
                        mediaPlayer.start();
                    }
                    if (!LauncherP2P.opponentScore.equals("pas de score")){
                        if (Integer.valueOf(LauncherP2P.opponentScore) < score) {
                            textView2.setText("Vous avez gagné !");
                            mediaPlayer = MediaPlayer.create(resultatActivity.this, R.raw.clapping);
                            mediaPlayer.start();
                        } else {
                            textView2.setText("Vous avez perdu !");
                            mediaPlayer = MediaPlayer.create(resultatActivity.this, R.raw.looser);
                            mediaPlayer.start();
                        }
                    }
                }
            });
        }
    }

    public void sendNew(View view) {
        Intent nextPage = new Intent(this, suivantChoixMono.class);
        nextPage.putExtra(EXTRA__CHOIX, choix);
        LauncherP2P.opponentScore = "pas de score";
        LauncherP2P.myScore = 0;
        startActivity(nextPage);
    }

    public void sendAccueil(View view) {
        Intent nextPage = new Intent(this, PageAccueil.class);
        startActivity(nextPage);
    }

}