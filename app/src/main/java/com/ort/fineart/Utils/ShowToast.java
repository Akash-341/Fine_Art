package com.ort.fineart.Utils;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {

    public static void ShowMsgToast(String message, Context context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
