package alexandre.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LauncherGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_game);
    }

    /** Called when the user taps the "Tape Tape Hammer" button */
    public void sendAGameTapeLeClou(View view) {
        Intent nextPage = new Intent(this, GameTapeLeClou.class);
        startActivity(nextPage);
    }

    /** Called when the user taps the "Join the cable" button */
    public void sendJoinTheCable(View view) {
        Intent nextPage = new Intent(this, GameDrapeau.class);
        startActivity(nextPage);
    }

    /** Called when the user taps the "Le jeu du niveau" button */
    public void sendJeuDuNiveau(View view) {
        Intent nextPage = new Intent(this, GamePeche.class);
        startActivity(nextPage);
    }

    /** Called when the user taps the "Le jeu du hulk" button */
    public void sendJeuDuHulk(View view) {
        Intent nextPage = new Intent(this, GameLumiere.class);
        startActivity(nextPage);
    }

    /** Called when the user taps the "Le bô morse O */
    public void sendJeuMorse(View view) {
        Intent nextPage = new Intent(this, GameMorse.class);
        startActivity(nextPage);
    }

    public void sendQCM(View view){
        Intent nextPage = new Intent(this, QCMActivity.class);
        startActivity(nextPage);
    }
}
