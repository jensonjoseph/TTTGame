package com.example.t9422jk.ttt_sms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Respond extends AppCompatActivity {
    //LayoutInflater inflater;
    TextView invitationMessage = null;
    Button acceptBtn = null;
    Button denyBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respond);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //inflater = LayoutInflater.from(this)
        Intent i = getIntent();
        String player_name = i.getStringExtra("player_name");
        String game_name = i.getStringExtra("game_name");
        acceptBtn = (Button) findViewById(R.id.accept_btn);
        denyBtn = (Button) findViewById(R.id.deny_btn);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlayerInfo.mySymbol="O";
                PlayerInfo.opponentSymbol="X";


                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:ACCEPTED:Johny", null, null);

                Intent i = new Intent(v.getContext(), GameBoard.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                v.getContext().startActivity(i);
            }
        });

        denyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:DENIED:Johny", null, null);
                finish();
            }
        });


        invitationMessage = (TextView) findViewById(R.id.invitation_message);
        invitationMessage.setText(player_name + " is inviting you to play " + game_name);

        //Toast.makeText(getBaseContext(), player_name, Toast.LENGTH_LONG).show();
    }

}
