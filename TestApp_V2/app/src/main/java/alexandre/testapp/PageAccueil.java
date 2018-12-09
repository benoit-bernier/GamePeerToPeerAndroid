package alexandre.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PageAccueil extends AppCompatActivity {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA__CHOIX = "choix";
    public String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageaccueil);

        // Get the Intent that started this activity and extract the string
        Intent previousactivity = getIntent();
        String UserName = previousactivity.getStringExtra(EnterName.EXTRA_NAME);
        nom=UserName;

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(UserName);
    }

    /** Called when the user taps the "Mode solo" button */
    public void sendSolo(View view) {
        Log.d("Page Acuueil", "working");
        Intent nextPage = new Intent(this, suivantChoixMono.class);
        nextPage.putExtra(EXTRA_NAME, nom);
        nextPage.putExtra(EXTRA__CHOIX, "solo");
        startActivity(nextPage);
    }

    /** Called when the user taps the "APropos button */
    public void sendAPropos(View view) {
        Intent nextPage = new Intent(this, APropos.class);
        startActivity(nextPage);
    }

    /** Called when the user taps the "Multijoueur button */
    public void sendMulti(View view) {
        Intent nextPage = new Intent(this, LauncherP2P.class);
        nextPage.putExtra(EXTRA_NAME, nom);
        nextPage.putExtra(EXTRA__CHOIX, "multijoueur");
        startActivity(nextPage);
    }


}