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
    private int mat_length = 0;
    private int compteur = -1;
    private int objectif_haut;
    private int objectif_bas;
    private long debut,fin;

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
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        //a activer pour avoir les logs dans la console
        System.out.println("node dev !");
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
            //recupération des coordonnées + taille du drapeau
            ImageView champs_photo_flag = findViewById(R.id.flag);
            int[] tab = new int [2];
            champs_photo_flag.getLocationOnScreen(tab);
            int image_flag_x = tab[0];
            int image_flag_y = tab[1];
            int image_flag_height = champs_photo_flag.getHeight();
            int image_flag_width = champs_photo_flag.getWidth();
            System.out.println("---------> image flag : x:" + image_flag_x + ", y:" + image_flag_y + ", height: " + image_flag_height + ", width: " + image_flag_width);

            //recupération du mat
            ImageView champs_photo_mat = findViewById(R.id.mat);
            int image_mat_height = champs_photo_mat.getHeight();

            //recupération des images objectifs
            ImageView champs_photo_objectif_haut = findViewById(R.id.imageView4);
            ImageView champs_photo_objectif_bas = findViewById(R.id.imageView5);

            // initialisation du drapeau et des objectifs
            if (mat_length == 0){
                mat_length = image_mat_height;
                y_position = mat_length - image_flag_height;
                objectif_haut = 0;
                objectif_bas = image_flag_height+image_flag_height +20;
                champs_photo_objectif_haut.setY(objectif_haut);
                champs_photo_objectif_bas.setY(objectif_bas);
            }

            // on bouge le drapeau en fonction de l'action
            if (event1.getY() < event2.getY()){
                y_position-=Math.abs(event1.getY()-event2.getY())/10;
            }else {
                y_position+=Math.abs(event1.getY()-event2.getY())/10;
            }

            // on regarde si ça sort pas du mat
            if(y_position <= 0){
                y_position = 0;
            } else if (y_position >= mat_length - image_flag_height){
                y_position = mat_length - image_flag_height;
            }
            champs_photo_flag.setY(y_position);

            System.out.println("--------->" + objectif_haut + "------------->" + objectif_bas);

            if(verify()){
                if (compteur == 3){
                    Intent intent = new Intent(getApplicationContext(), suivantTouch.class);
                    fin=SystemClock.elapsedRealtime();
                    intent.putExtra(EXTRA_SCORE, (int) (fin-debut));
                    intent.putExtra(EXTRA_CHOIX, choix);
                    startActivity(intent);
                } else{
                    compteur++;
                    Random r = new Random();
                    objectif_haut = r.nextInt(image_mat_height - image_flag_height);
                    objectif_bas = objectif_haut + image_flag_height + 20;
                    champs_photo_objectif_haut.setY(objectif_haut);
                    champs_photo_objectif_bas.setY(objectif_bas);
                }
            }
            return true;
        }

        private boolean verify (){
            return y_position >= objectif_haut && y_position <= objectif_bas;
        }
    }



}
