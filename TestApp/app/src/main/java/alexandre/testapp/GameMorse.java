package alexandre.testapp;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameMorse extends AppCompatActivity {

    private GestureDetectorCompat mDetector;
    private String DEBUG_TAG = "test_GameMorse";
    private String message = "Message : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_morse);
        mDetector = new GestureDetectorCompat(this, new GameMorse.MyGestureListener());
    }

    public void restart(View view){
        message = "Message : ";
        TextView champs_message = findViewById(R.id.game_morse_message);
        champs_message.setText(message);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Pressure";
        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
            TextView champs_type = findViewById(R.id.game_morse_type);
            champs_type.setText("Last symbol : .");
            TextView champs_message = findViewById(R.id.game_morse_message);
            message = message + ".";
            champs_message.setText(message);
            return true;
        }
        @Override
        public void onLongPress(MotionEvent event) {
            Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
            TextView champs_type = findViewById(R.id.game_morse_type);
            champs_type.setText("Last symbol : _");
            TextView champs_message = findViewById(R.id.game_morse_message);
            message = message + "_";
            champs_message.setText(message);
        }
    }
}
