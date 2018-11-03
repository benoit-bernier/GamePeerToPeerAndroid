package alexandre.testapp;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;


public class GameJoinTheCable extends AppCompatActivity {

    private GestureDetectorCompat mDetector;
    private String DEBUG_TAG = "test_GameJoinTheCable";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_join_the_cable);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        //récupération de la position de l'image
        ImageView champs_photo = findViewById(R.id.game_jointhecable_electricity);
        TextView champs_x = findViewById(R.id.textView8);
        champs_x.setText("x : " + champs_photo.getX());
        TextView champs_y = findViewById(R.id.textView11);
        champs_y.setText("y : " + champs_photo.getY());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            TextView champs_text = findViewById(R.id.textView5);
            champs_text.setText("E : valeur de x : " + String.valueOf(event.getX()));
            TextView champs_text2 = findViewById(R.id.textView4);
            champs_text2.setText("E : valeur de y : " + String.valueOf(event.getY()));
            TextView champs_text3 = findViewById(R.id.textView6);
            champs_text3.setText("none");
            TextView champs_text4 = findViewById(R.id.textView7);
            champs_text4.setText("none");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
            TextView champs_text = findViewById(R.id.textView5);
            champs_text.setText("E1 : valeur de x : " + String.valueOf(event1.getX()));
            TextView champs_text2 = findViewById(R.id.textView4);
            champs_text2.setText("E1 : valeur de y : " + String.valueOf(event1.getY()));
            TextView champs_text3 = findViewById(R.id.textView6);
            champs_text3.setText("E2 : valeur de x : " + String.valueOf(event2.getX()));
            TextView champs_text4 = findViewById(R.id.textView7);
            champs_text4.setText("E2 : valeur de y : " + String.valueOf(event2.getY()));
            return true;
        }
    }



}
