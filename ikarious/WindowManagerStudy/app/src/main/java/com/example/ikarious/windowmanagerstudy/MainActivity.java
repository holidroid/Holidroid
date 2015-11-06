package com.example.ikarious.windowmanagerstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mResultText;
    private Button mStartBtn, mStopBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mResultText = (TextView) findViewById(R.id.result_txt);

        mStartBtn = (Button) findViewById(R.id.btn_start_service);
        mStopBtn = (Button) findViewById(R.id.btn_stop_service);


        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent my_service_intent = new Intent(getApplicationContext(), MyService.class);

        switch (v.getId()){

            case R.id.btn_start_service :
                startService(my_service_intent);
                mResultText.setText("Start Service !");

                break;

            case R.id.btn_stop_service :
                stopService(my_service_intent);
                mResultText.setText("Stop Service !");

                break;
        }



    }

}
