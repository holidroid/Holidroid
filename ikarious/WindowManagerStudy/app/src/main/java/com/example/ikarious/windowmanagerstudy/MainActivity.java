package com.example.ikarious.windowmanagerstudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Objects;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mResultText;
    private Button mStartBtn, mStopBtn, mResultBtn;

    private String mSendMsg;
    private String mNumber = "01040562805";
    private String mRandom;

    private BroadcastReceiver mReceiver = null;
    private IntentFilter mIntentfilter = null;
    private TextView mResultTxt;
    private String temp_msg;
    private EditText mInputEdit = null;
    private String mReceiveNumber ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mInputEdit = (EditText) findViewById(R.id.edit_input_number);

        mResultTxt = (TextView) findViewById(R.id.result_txt);

        mResultBtn = (Button) findViewById(R.id.btn_check_sms);


        mResultBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String input_number = mInputEdit.getText().toString();

                if (input_number.equals(mReceiveNumber)){
                    Toast.makeText(getApplicationContext(), "Success Authentification", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Fail Authentification", Toast.LENGTH_SHORT).show();
                }


            }

        });


        //1. 방송을 들을 수 있는 주체를 만들고
        mReceiver = new BroadcastReceiver() {


            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(getApplicationContext(), "Receive : " + intent.getAction(), Toast.LENGTH_SHORT).show();

                Bundle bundle = intent.getExtras();
                Object messages[] = (Object[])bundle.get("pdus");
                SmsMessage smsMessage[] = new SmsMessage[messages.length];

                for (int i =0; i < messages.length; i++ ){
                    smsMessage[i] = SmsMessage.createFromPdu((byte[])messages[i]);

                }


                Date curDate = new Date(smsMessage[0].getTimestampMillis());
                Log.d("문자 수신 시간", curDate.toString());

                String origNumber = smsMessage[0].getOriginatingAddress();

                String message = smsMessage[0].getDisplayMessageBody().toString();

                temp_msg = "문자 수신 시간 : " + curDate.toString() + "\n" +
                            "발자 번호 : " + origNumber + "\n" +
                            "수신 내용 : " + message;

                mResultTxt.setText(temp_msg);

                //mReceiveNumber =

                String[] m1 = message.split("\\[");
                String[] m2 = m1[1].split("\\]");
                mReceiveNumber = m2[0];
                mInputEdit.setText(m2[0]);


            }

        };

        //2. 어떤 방송을 들을 것인지 정의를 하고
        mIntentfilter = new IntentFilter();
        mIntentfilter.addAction("android.provider.Telephony.SMS_RECEIVED");


        //3. 방송을 지금부터 듣겠다....
//        registerReceiver(mReceiver, mIntentfilter);




        mResultText = (TextView) findViewById(R.id.result_txt);

        mStartBtn = (Button) findViewById(R.id.btn_start_service);
        mStopBtn = (Button) findViewById(R.id.btn_stop_service);


        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);


        int a = (int)(Math.random() * 10000);
        int b = (int)(Math.random() * 10000);
        int c = (int)(Math.random() * 10000);
        int d = (int)(Math.random() * 10000);

        mRandom = a + "" + b + "" + c + "" + d;

        mSendMsg = "Etners Call Registration ....\n" + "Authetification Number [" + mRandom + "]\n";


    }

    @Override
    protected void onResume() {
        super.onResume();
        //방송 재등록
        registerReceiver(mReceiver, mIntentfilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //방송 등록 해지
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {

//        Intent my_service_intent = new Intent(getApplicationContext(), MyService.class);

        switch (v.getId()){

            case R.id.btn_start_service :
                SmsManager sms_manager = SmsManager.getDefault();
                sms_manager.sendTextMessage(mNumber, null, mSendMsg, null, null);

                break;

            case R.id.btn_stop_service :

                break;
        }





    }

}
