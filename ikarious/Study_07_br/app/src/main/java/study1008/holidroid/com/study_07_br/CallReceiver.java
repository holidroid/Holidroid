package study1008.holidroid.com.study_07_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
    public CallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Call State !!!", Toast.LENGTH_LONG).show();

        String actionType = intent.getAction();
        Bundle myBundle = intent.getExtras();

        if(actionType.equals("android.intent.ACTION_PHONE_STATE")){
            String state = myBundle.getString(TelephonyManager.EXTRA_STATE);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context, "받아라", Toast.LENGTH_SHORT).show();
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                Toast.makeText(context, "전화 고마해", Toast.LENGTH_SHORT).show();
            } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context, "끊었네", Toast.LENGTH_SHORT).show();
            }
        }else if (actionType.equals("android.provider.Telephony.SMS_RECEIVED")){
            Toast.makeText(context, "문자왔쪄영 뿌우", Toast.LENGTH_SHORT).show();

            Object[] pdusObj = (Object[]) myBundle.get("pdus");

            SmsMessage[] sms = new SmsMessage[pdusObj.length];
            String str = "";

            for (int i=0; i < pdusObj.length; i++) {

                sms[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                str = str + "FROM : "+ sms[i].getDisplayOriginatingAddress();
                str = str + "Body : "+ sms[i].getDisplayMessageBody();
                Toast.makeText(context, str, Toast.LENGTH_LONG).show();
            }


        }






    }
}
