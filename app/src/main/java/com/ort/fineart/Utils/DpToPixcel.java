package com.ort.fineart.Utils;

import android.content.Context;

public class DpToPixcel {
    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
