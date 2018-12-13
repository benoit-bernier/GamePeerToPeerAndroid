package com.example.testp2pv5;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.testp2pv5.LauncherP2P.MESSAGE_READ;

public class NextPageTest extends AppCompatActivity {

    TextView read_msg_box, myscore_box, compteur_box;
    Button buttonSend;
    EditText writeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page_test);
        //read_msg_box = findViewById(R.id.readMsg);
        read_msg_box = findViewById(R.id.myText);
        myscore_box = findViewById(R.id.readMsg2);
        compteur_box = findViewById(R.id.readMsg3);
        buttonSend = findViewById(R.id.button3);
        writeMsg = findViewById(R.id.myText);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = writeMsg.getText().toString();
                LauncherP2P.sendReceive.write(msg.getBytes());
                //on simule un jeu fait par moi (pas l'opposant MOI)
                LauncherP2P.myScore+=25;
            }
        });
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_READ:
                    byte[] readBuff = (byte[]) msg.obj;
                    String tempMsg = new String(readBuff, 0, msg.arg1);
                    LauncherP2P.compteur++;
                    LauncherP2P.opponentScore = LauncherP2P.opponentScore + Integer.parseInt(tempMsg);
                    read_msg_box.setText(String.valueOf(LauncherP2P.opponentScore));
                    myscore_box.setText(String.valueOf(LauncherP2P.myScore));
                    compteur_box.setText(String.valueOf(LauncherP2P.compteur));
            }
            return true;
        }
    });
}
