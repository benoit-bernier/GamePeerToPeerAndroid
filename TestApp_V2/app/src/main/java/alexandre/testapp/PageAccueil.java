package alexandre.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PageAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageaccueil);

        // Get the Intent that started this activity and extract the string
        Intent previousactivity = getIntent();
        String message = previousactivity.getStringExtra(MainActivity.EXTRA_NAME);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }

    /** Called when the user taps the "Mode solo" button */
    public void sendSolo(View view) {
        Intent nextPage = new Intent(this, LauncherGame.class);
        startActivity(nextPage);
    }

    /** Called when the user taps the "APropos button */
    public void sendAPropos(View view) {
        Intent nextPage = new Intent(this, APropos.class);
        startActivity(nextPage);
    }


}