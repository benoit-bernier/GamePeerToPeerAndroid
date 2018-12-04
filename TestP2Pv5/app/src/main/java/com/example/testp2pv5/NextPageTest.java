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

    TextView read_msg_box;
    Button buttonSend;
    EditText writeMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page_test);
        //read_msg_box = findViewById(R.id.readMsg);
        read_msg_box = findViewById(R.id.myText);
        buttonSend = findViewById(R.id.button3);
        writeMsg = findViewById(R.id.myText);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = writeMsg.getText().toString();
                LauncherP2P.sendReceive.write(msg.getBytes());
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
                    read_msg_box.setText(tempMsg);
                    break;
            }
            return true;
        }
    });
}
