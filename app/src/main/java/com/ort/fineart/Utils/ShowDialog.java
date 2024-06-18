package com.ort.fineart.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.ort.fineart.R;

public class ShowDialog {
    public static final String TAG = "Utils";
    private static Dialog dialog;
    private static CustomCircularProgressView ivProgressBar;

    public static void showCustomProgressDialog(Context context, boolean isCancel) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        if (((AppCompatActivity) context).isFinishing()) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.circuler_progerss_bar_two);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ivProgressBar = (CustomCircularProgressView) dialog.findViewById(R.id.ivProgressBarTwo);
            ivProgressBar.startAnimation();
            dialog.setCancelable(isCancel);
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(params);
            dialog.getWindow().setDimAmount(0);
            dialog.show();
        }
    }



    public static void hideCustomProgressDialog() {
        try {
            if (dialog != null && ivProgressBar != null) {
                dialog.dismiss();
            }
        } catch (Exception e) {
        }
    }
}
