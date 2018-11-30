package alexandre.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnterName extends AppCompatActivity {
    public static final String EXTRA_NAME = "default_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent nextPage = new Intent(this, PageAccueil.class);
        EditText editText = (EditText) findViewById(R.id.editName);
        String nameToPrint = editText.getText().toString();
        nextPage.putExtra(EXTRA_NAME, nameToPrint);
        startActivity(nextPage);
    }
}
