package alexandre.testapp;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.Random;


public class GameDrapeau extends AppCompatActivity {

    public static final String EXTRA_SCORE = "score"; // NE PAS MODIFIER
    public static final String EXTRA_CHOIX = "choix";


    private GestureDetectorCompat mDetector;
    private int y_position;
    private boolean deja_joue = false;
    private int compteur = 0;
    private int objectif_haut;
    private int objectif_bas;
    private long debut,fin;
    private int flag_height;

    private String nameOfUser;
    public String choix;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_join_the_cable);
        Intent previousActivity = getIntent();
        choix = previousActivity.getStringExtra(suivantChoixMono.EXTRA_CHOIX);

        debut=SystemClock.elapsedRealtime();
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public void onBackPressed() {
        // on empêche le bouton retour
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        //a activer pour avoir les logs dans la console
        System.out.println("node dev !");
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            //recupération des coordonnées + taille du drapeau
            ImageView image_flag = findViewById(R.id.flag);
            int[] tab = new int [2];
            image_flag.getLocationOnScreen(tab);
            int flag_x = tab[0]; //position en x
            int flag_y = tab[1]; // position en y
            flag_height = image_flag.getHeight();

            //infos du mat
            ImageView champs_photo_mat = findViewById(R.id.mat);
            int mat_height = champs_photo_mat.getHeight();
            int mat_haut = 20; //mat moins la boule
            int mat_bas = mat_height-420; // mat moins le pied

            //recupération des images objectifs
            ImageView image_objectif_haut = findViewById(R.id.imageView4);
            ImageView image_objectif_bas = findViewById(R.id.imageView5);

            // initialisation du drapeau et des objectifs
            if (!deja_joue){
                objectif_haut = 50;
                objectif_bas = objectif_haut+flag_height +20;// 20 --> epaisseur de obj_haut + un peu de marge
                image_objectif_haut.setY(objectif_haut);
                image_objectif_bas.setY(objectif_bas);
                deja_joue=true;
            }
            // position du drapeau selon le fling
            if (event1.getY() < event2.getY()){
                y_position-=Math.abs(event1.getY()-event2.getY())/10;
            }else {
                y_position+=Math.abs(event1.getY()-event2.getY())/10;
            }

            // on regarde si ça sort pas du mat (entre la boule et le pied)
            if(y_position < mat_haut){
                y_position = mat_haut;
            } else if (y_position+flag_height > mat_bas){
                y_position = mat_bas - flag_height;
            }

            image_flag.setY(y_position);

            if(verify()){
                if (compteur == 3){
                    Intent intent = (!choix.equals("entrainement")) ? (new Intent(getApplicationContext(), suivantTouch.class)) : (new Intent(getApplicationContext(), resultatActivity.class));
                    fin=SystemClock.elapsedRealtime();
                    int scoreSend = 105-(int)(fin-debut)/1000;
                    if (scoreSend < 0){
                        scoreSend=0;
                    } else if (scoreSend > 100){
                        scoreSend=100;
                    }
                    intent.putExtra(EXTRA_SCORE, scoreSend);
                    intent.putExtra(EXTRA_CHOIX, choix);
                    startActivity(intent);
                } else{
                    compteur++;
                    Random r = new Random();
                    objectif_haut = r.nextInt(mat_bas-flag_height)+mat_haut; // objectif_haut entre mat_bas et mat_haut
                    objectif_bas = objectif_haut + flag_height + 20;
                    image_objectif_haut.setY(objectif_haut);
                    image_objectif_bas.setY(objectif_bas);
                }
            }
            return true;
        }

        private boolean verify (){
            return (y_position+(flag_height/2) >= objectif_haut && (y_position+(flag_height/2)) <= objectif_bas);
        }
    }



}
