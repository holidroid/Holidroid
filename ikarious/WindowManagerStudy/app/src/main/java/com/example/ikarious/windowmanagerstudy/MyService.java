package com.example.ikarious.windowmanagerstudy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    private View mView;
    private WindowManager mManager;
    WindowManager.LayoutParams mParams;

    private float mTouchX;
    private float mTouchY;
    private int mViewX;
    private int mViewY;
    private Button mSendBtn;
    private EditText mSMSMsg, mSMSNumber;
    private int mRandom;


    //최초 기동시 한번만 호출
    @Override
    public void onCreate() {
        super.onCreate();

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.my_window_layout, null);
        mSendBtn = (Button) mView.findViewById(R.id.btn_send_sms);
        mSMSMsg = (EditText) mView.findViewById(R.id.edit_sms_msg);
        mSMSNumber = (EditText) mView.findViewById(R.id.edit_sms_number);



                mParams = new WindowManager.LayoutParams (
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                PixelFormat.TRANSLUCENT
        );

        mManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mManager.addView(mView, mParams);

        mSendBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                String sms_msg = mSMSMsg.getText().toString();
                String sms_number = mSMSNumber.getText().toString();

                if(sms_msg.isEmpty() && sms_number.isEmpty()){
                    Toast.makeText(getApplicationContext(), "SMS MSG AND NUMBER...",Toast.LENGTH_SHORT ).show();
                    return;
                }

                SmsManager sms_manager = SmsManager.getDefault();
                sms_manager.sendTextMessage(sms_number, null, sms_msg, null, null);

                mSMSMsg.setText("");
                Toast.makeText(getApplicationContext(), "SUCCESS SEND MESSAGE", Toast.LENGTH_SHORT).show();

            }
        });



        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        mTouchX = event.getRawX();
                        mTouchY = event.getRawY();
                        mViewX = mParams.x;
                        mViewY = mParams.y;
                        break;

                    case  MotionEvent.ACTION_UP :
                        break;

                    case  MotionEvent.ACTION_MOVE :
                        int x = (int) (event.getRawX() - mTouchX);
                        int y = (int) (event.getRawY() - mTouchY);
                        mParams.x = mViewX + x;
                        mParams.y = mViewY + y;
                        mManager.updateViewLayout(mView, mParams);
                        break;
                }


                return true;
            }
        });


        Toast.makeText(getApplicationContext(), "Service = OnCreate() Calling...", Toast.LENGTH_SHORT).show();



    }

    //Service 기동 될 떄마다 호출되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(), "Service = onStartCommand() Calling...", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mManager != null) {
            mManager.removeView(mView);
            mView = null;
            mManager = null;
        }

        Toast.makeText(getApplicationContext(), "Service = onDestroy() Calling...", Toast.LENGTH_SHORT).show();

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
