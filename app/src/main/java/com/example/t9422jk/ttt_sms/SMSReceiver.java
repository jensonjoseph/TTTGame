package com.example.t9422jk.ttt_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by T9422JK on 11/11/2015.
 */
public class SMSReceiver extends BroadcastReceiver {
    static IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

    public SMSReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            SmsMessage[] smsMessages = getMessagesFromIntent(intent);
            if (smsMessages != null) {
                for (int i = 0; i < smsMessages.length; i++) {
                    String phoneNumber = smsMessages[i].getOriginatingAddress();
                    PlayerInfo.oponentNumber = phoneNumber;
                    String message = smsMessages[i].getMessageBody();
                    //Toast.makeText(context, phoneNumber + ": " + message, Toast.LENGTH_LONG).show();

                }
            }

            String[] gameParams = smsMessages[0].getMessageBody().split(":");
            if (gameParams != null) {
                if (gameParams[0].equals("%$$$")) {
                    String gameName = gameParams[1];
                    String action = gameParams[2];

                    if (action.equals("INVITE")) {
                        Intent i = new Intent(context, Respond.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        i.putExtra("player_name", "Jenson");
                        i.putExtra("game_name", "TicTacToe");
                        context.startActivity(i);

                    } else if (action.equals("ACCEPTED")) {
                        //Toast.makeText(context, PlayerInfo.oponentNumber + " has accepted your challenge", Toast.LENGTH_LONG).show();

                        PlayerInfo.initialize();

                        Intent i = new Intent(context, GameBoard.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        context.startActivity(i);


                    } else if (action.equals("DENIED")) {
                        Intent i = new Intent(context, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        context.startActivity(i);
                        Toast.makeText(context, PlayerInfo.oponentNumber + " has declined your challenge", Toast.LENGTH_LONG).show();

                    } else if (action.equals("SELECTED")) {
                        //"%$$$:TIC TAC TOE:SELECTED:X:" + i + ":" + j
                        Intent i = new Intent(context, GameBoard.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        i.putExtra("MYSYMBOL", gameParams[3]);
                        i.putExtra("btn_i", gameParams[4]);
                        i.putExtra("btn_j", gameParams[5]);
                        context.startActivity(i);


                    } else if (action.equals("RESULT")) {
                        Toast.makeText(context, "Opponent WINS *** better luck next time", Toast.LENGTH_LONG).show();
                    }else if (action.equals("TERMINATE")) {
                        Toast.makeText(context, "TERMINATING GAME...", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(context, GameBoard.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        i.putExtra("TERMINATE", gameParams[3]);
                        context.startActivity(i);

                    } else if (action.equals("RESTART")) {
                        PlayerInfo.reset();
                        Intent i = new Intent(context, GameBoard.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        context.startActivity(i);
                    }

                } else {
                    Toast.makeText(context, "Irrelevant message", Toast.LENGTH_LONG).show();
                }
            }


        }
    }

    public static SmsMessage[] getMessagesFromIntent(Intent intent) {
        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
        byte[][] pduObjs = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++) {
            pduObjs[i] = (byte[]) messages[i];
        }
        byte[][] pdus = new byte[pduObjs.length][];
        int pduCount = pdus.length;
        SmsMessage[] msgs = new SmsMessage[pduCount];
        for (int i = 0; i < pduCount; i++) {
            pdus[i] = pduObjs[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);
        }
        return msgs;
    }
}
