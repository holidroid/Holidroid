package study1021.holidroid.com.study_08_service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button mServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);


        mServiceBtn = (Button) findViewById(R.id.start_service);

        mServiceBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //서비스를 실행하기 위한 코드 2줄
                Intent startIntent = new Intent(getApplicationContext(), MyService.class);
                startService(startIntent);


            }

        });
    }


}
