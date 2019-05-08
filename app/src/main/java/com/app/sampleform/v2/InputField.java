package com.app.sampleform.v2;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public abstract class InputField extends LinearLayout {
    public InputField(Context context) {
        super(context);
    }

    public InputField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InputField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public abstract void showEmptyWarning();

    public abstract void hideWarning();
}
