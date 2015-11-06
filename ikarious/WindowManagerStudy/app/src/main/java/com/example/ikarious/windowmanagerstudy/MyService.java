package com.example.ikarious.windowmanagerstudy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

    //최초 기동시 한번만 호
    @Override
    public void onCreate() {
        super.onCreate();

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.my_window_layout, null);

                mParams = new WindowManager.LayoutParams (
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        mManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mManager.addView(mView, mParams);

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
