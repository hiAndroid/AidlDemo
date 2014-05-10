package com.demo.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.demo.service.MathService;
import com.demo.service.ServiceDemoAidl;

/**
 * Created by wuzhong on 14-5-10.
 */
public class MathConsumerActivity extends Activity {

    private MathService mathService;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //TODO 启动service
        startService();

        validateAIDL();

    }

    private void validateAIDL() {
        if(null != mathService){

            Log.e("[consumer]", "start math service");

            try {

                int result = mathService.add(1,2);

//                Toast.makeText(this, "result is " + result, 20000);
                Log.d("[consumer]","result is " + result);

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }

    private void startService() {
        Intent intent = new Intent("com.demo.service.MATH.START");
        bindService(intent, initConnection(), Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    private ServiceConnection initConnection() {

        return new ServiceConnection(){
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // TODO Auto-generated method stub

                mathService = MathService.Stub.asInterface(service);

                validateAIDL();

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // TODO Auto-generated method stub
                mathService = null;
            }

        };
    }
}