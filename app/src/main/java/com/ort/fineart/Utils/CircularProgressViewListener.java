package com.ort.fineart.Utils;

public class CircularProgressViewListener {
    /**
     * Called when setProgress(float currentProgress) is called (determinate only)
     *
     * @param currentProgress The progress that was set.
     */
    void onProgressUpdate(float currentProgress) {

    }

    /**
     * Called when this view finishes animating to the updated progress. (Determinate only)
     *
     * @param currentProgress The progress that was set and this view has reached in its animation.
     */
    void onProgressUpdateEnd(float currentProgress) {

    }

    /**
     * Called when resetAnimation() is called.
     */
    void onAnimationReset() {

    }

    /**
     * Called when you switch between indeterminate and determinate modes.
     *
     * @param isIndeterminate true if mode was set to indeterminate, false otherwise.
     */
    void onModeChanged(boolean isIndeterminate) {

    }
}
