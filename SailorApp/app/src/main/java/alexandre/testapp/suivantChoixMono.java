package alexandre.testapp;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.TextView;

        import java.util.Arrays;
        import java.util.List;
        import java.util.Random;

public class suivantChoixMono extends AppCompatActivity {
    public static final String EXTRA_NOM = "nom";
    public static final String EXTRA_CHOIX = "choix";
    public static final String EXTRA_SCORE = "score";

    public String nameOfUser;
    public String choix;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivant_choix_mono);
        Intent previousActivity = getIntent();
        nameOfUser = previousActivity.getStringExtra(PageAccueil.EXTRA_NAME);
        choix = previousActivity.getStringExtra("choix");
        TextView textView = findViewById(R.id.textViewName);
        textView.setText("Bienvenue "+nameOfUser+", vous avez choisi le mode "+choix);
    }

    public void MonoSendJEU1(View view) {
        List<Class<? extends android.support.v7.app.AppCompatActivity>> ClassList = Arrays.asList(GameTapeLeClou.class, GameMorse.class, GameDrapeau.class);
        Random rand = new Random();
        int nbr = rand.nextInt(ClassList.size());
        Intent intent = new Intent(this, ClassList.get(nbr));
        intent.putExtra(EXTRA_NOM, nameOfUser);
        intent.putExtra(EXTRA_CHOIX, choix);
        intent.putExtra(EXTRA_SCORE, 0);
        startActivity(intent);
    }
}
