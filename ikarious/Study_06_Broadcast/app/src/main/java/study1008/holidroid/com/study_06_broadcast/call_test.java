package study1008.holidroid.com.study_06_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class call_test extends BroadcastReceiver {

    private int count =0;
    public static String TYPE_ACTION_PHONE_STATE = "android.intent.action.PHONE_STATE";


    public call_test() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String actionType = intent.getAction();
        Bundle myBundle = intent.getExtras();



        if(actionType.equals(TYPE_ACTION_PHONE_STATE)){
            String state = myBundle.getString(TelephonyManager.EXTRA_STATE);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context, "전화와쪄염 뿌우", Toast.LENGTH_LONG).show();
            }else if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                Toast.makeText(context, "전화받아쪄염 뿌우", Toast.LENGTH_LONG).show();
            }else if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context, "전화끊지마세염 뿌우", Toast.LENGTH_LONG).show();
            }
        }else if (actionType.equals("android.provider.Telephony.SMS_RECEIVED")){
            Toast.makeText(context, "문자왔쪄영 뿌우", Toast.LENGTH_SHORT).show();
        }

    }

}
