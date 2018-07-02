package com.example.eltobgy.spotlight.utlis;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Eltobgy on 31-May-18.
 */

public class Helper {
    //logs, toasts, repeated methods
    public static void showLog(String tag,String msg){
        Log.e(tag,msg);
    }
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
