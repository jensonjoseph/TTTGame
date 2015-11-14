package com.example.t9422jk.ttt_sms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button invite_btn = null;
    EditText phone_no = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        invite_btn = (Button) findViewById(R.id.invite_btn);
        phone_no = (EditText) findViewById(R.id.phone_no);
        PlayerInfo.oponentNumber = phone_no.getText().toString();
        PlayerInfo.mySymbol = "X";
        PlayerInfo.opponentSymbol = "O";
        PlayerInfo.initialize();
        invite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(phone_no.getText().toString(), null, "%$$$:TIC TAC TOE:INVITE:James", null, null);
            }
        });
    }
}
