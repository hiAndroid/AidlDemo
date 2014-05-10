package com.demo.service.impl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.demo.service.MathService;

/**
 * Created by wuzhong on 14-5-10.
 */
public class MathServiceImpl extends Service {

    @Override
    public IBinder onBind(Intent intent) {

        Log.d("[provider]", "mathserviceImpl onBind");


        return new MathService.Stub(){

            @Override
            public int add(int arg1, int arg2) throws RemoteException {

                Log.d("[provider]", "arg1 is " + arg1 + " arg2 is " + arg2);

                int result = arg1 + arg2;

                Log.d("[provider]", "math result is " + result);

                return result;
            }
        };
    }


}
