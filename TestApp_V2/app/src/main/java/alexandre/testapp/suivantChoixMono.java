package alexandre.testapp;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.TextView;

public class suivantChoixMono extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "bzh.esir.iot.myapp.MESSAGE";
    public String nameOfUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivant_choix_mono);
        Intent previousActivity = getIntent();
        String userName = previousActivity.getStringExtra(PageAccueil.EXTRA_NAME);
        nameOfUser = userName;
        TextView textView = findViewById(R.id.textViewName);
        textView.setText("Bienvenue "+userName+",");
    }

    public void MonoSendJEU1(View view) {
        Intent intent = new Intent(this, GameTapeLeClou.class);
        //intent.putExtra(EXTRA_MESSAGE, nameOfUser);
        startActivity(intent);
    }

    public void MonoSendJEU2(View view) {
        Intent intent = new Intent(this, GameMorse.class);
        //intent.putExtra(EXTRA_MESSAGE, nameOfUser);
        startActivity(intent);
    }

    public  void MonoSendJEU3(View view){
        Intent intent = new Intent(this, GameDrapeau.class);
        //intent.putExtra(EXTRA_MESSAGE, nameOfUser);
        startActivity(intent);
    }
}
