package com.ort.fineart.Utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    public VerticalSpaceItemDecoration(Context context, int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // Apply space only on the right side
        /*outRect.right = space;
        outRect.left=space;*/

        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();



        // Apply right margin for the last item
        if (position == itemCount - 1) {
            outRect.top = space;
            outRect.bottom = space * 3; // Triple the space
        } else {
            // Apply space between items
            outRect.top = space;
        }






    }
}