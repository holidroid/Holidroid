package study1021.holidroid.com.study_08_service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }


    //최초 실행될 때 한번만 호출
    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(getApplicationContext(),"Service-OnCreate", Toast.LENGTH_SHORT).show();

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.always_on_top_view_not_touch, null);

        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,

                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,

                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,

                PixelFormat.TRANSLUCENT);

        mManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mManager.addView(mView, mParams);

    }

    //서비스가 불러졌을
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(getApplicationContext(),"Service-OnStartCommand", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
