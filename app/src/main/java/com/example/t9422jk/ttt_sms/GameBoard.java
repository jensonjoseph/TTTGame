package com.example.t9422jk.ttt_sms;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameBoard extends AppCompatActivity {

    //Let me see if this works
    //Well it worked. Lets confirm!

    Button[][] btn = new Button[4][4];
    Button terminateBtn = null;
    Button restartBtn = null;
    TextView turnlable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn[0][0] = (Button) findViewById(R.id.btn_0_0);
        btn[0][1] = (Button) findViewById(R.id.btn_0_1);
        btn[0][2] = (Button) findViewById(R.id.btn_0_2);
        btn[0][3] = (Button) findViewById(R.id.btn_0_3);

        btn[1][0] = (Button) findViewById(R.id.btn_1_0);
        btn[1][1] = (Button) findViewById(R.id.btn_1_1);
        btn[1][2] = (Button) findViewById(R.id.btn_1_2);
        btn[1][3] = (Button) findViewById(R.id.btn_1_3);

        btn[2][0] = (Button) findViewById(R.id.btn_2_0);
        btn[2][1] = (Button) findViewById(R.id.btn_2_1);
        btn[2][2] = (Button) findViewById(R.id.btn_2_2);
        btn[2][3] = (Button) findViewById(R.id.btn_2_3);

        btn[3][0] = (Button) findViewById(R.id.btn_3_0);
        btn[3][1] = (Button) findViewById(R.id.btn_3_1);
        btn[3][2] = (Button) findViewById(R.id.btn_3_2);
        btn[3][3] = (Button) findViewById(R.id.btn_3_3);

        restartBtn = (Button) findViewById(R.id.restart_button);
        terminateBtn = (Button) findViewById(R.id.terminate_button);
        turnlable = (TextView) findViewById(R.id.turnLable);

        final Intent intent = getIntent();
        if (intent.hasExtra("MYSYMBOL")) {
            String symbol = intent.getStringExtra("MYSYMBOL");
            //String symbol = PlayerInfo.opponentSymbol;
            String i_value = intent.getStringExtra("btn_i");
            String j_value = intent.getStringExtra("btn_j");

            int _i = Integer.parseInt(i_value);
            int _j = Integer.parseInt(j_value);

            btn[_i][_j].setText(symbol);
            PlayerInfo.opponentCell[_i][_j].symbol = PlayerInfo.opponentSymbol;
            btn[_i][_j].setEnabled(false);
            turnlable.setText("Its your turn");
            enableValidCells();
        }else if(intent.hasExtra("TERMINATE")){
            finish();
        }

        updateCells();


        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerInfo.reset();
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESTART:" + PlayerInfo.mySymbol, null, null);
                Intent i = new Intent(v.getContext(), GameBoard.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                v.getContext().startActivity(i);
            }
        });

        terminateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:TERMINATE:" + PlayerInfo.mySymbol, null, null);
                Intent i = new Intent(v.getContext(), GameBoard.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                v.getContext().startActivity(i);
                Toast.makeText(v.getContext(), "TERMINATING GAME...", Toast.LENGTH_LONG).show();
                finish();
            }
        });


        btn[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0, j = 0;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;

                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);

                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0, j = 1;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0, j = 2;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0, j = 3;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });


        btn[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1, j = 0;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1, j = 1;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1, j = 2;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 1, j = 3;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });


        btn[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 2, j = 0;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 2, j = 1;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 2, j = 2;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 2, j = 3;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });


        btn[3][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = 3, j = 0;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 3, j = 1;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 3, j = 2;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });
        btn[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 3, j = 3;
                btn[i][j].setText(PlayerInfo.mySymbol);
                PlayerInfo.myCell[i][j].symbol = PlayerInfo.mySymbol;
                btn[i][j].setEnabled(false);
                btn[i][j].setBackgroundColor(Color.MAGENTA);
                SmsManager manager = SmsManager.getDefault();
                if (PlayerInfo.amIWinner()) {
                    Toast.makeText(getBaseContext(), "You WIN *** CONGRATS!!!", Toast.LENGTH_LONG).show();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:RESULT:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                } else {
                    turnlable.setText("Its opponets ");
                    disableCells();
                    manager.sendTextMessage(PlayerInfo.oponentNumber, null, "%$$$:TIC TAC TOE:SELECTED:" + PlayerInfo.mySymbol + ":" + i + ":" + j, null, null);
                }

            }
        });


    }

    public void disableCells() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                btn[i][j].setEnabled(false);
            }
        }
    }

    public void enableValidCells() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (PlayerInfo.myCell[i][j].symbol.equals("")) {
                    btn[i][j].setEnabled(true);
                }
            }
        }
    }

    public void updateCells() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!PlayerInfo.myCell[i][j].symbol.equals("")) {
                    btn[i][j].setText(PlayerInfo.myCell[i][j].symbol);
                    btn[i][j].setEnabled(false);
                    btn[i][j].setBackgroundColor(Color.MAGENTA);
                } else if (!PlayerInfo.opponentCell[i][j].symbol.equals("")) {
                    btn[i][j].setText(PlayerInfo.opponentCell[i][j].symbol);
                    btn[i][j].setEnabled(false);
                    btn[i][j].setBackgroundColor(Color.YELLOW);
                } else {
                    btn[i][j].setText("");
                    btn[i][j].setEnabled(true);
                }
            }
        }
        if (PlayerInfo.amIWinner()) {
            System.out.println("I WIN");
        }

    }
}
