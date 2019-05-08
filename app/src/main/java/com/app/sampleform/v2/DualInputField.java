package com.app.sampleform.v2;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.app.sampleform.R;
import com.app.sampleform.model.FormField;
import com.app.sampleform.model.Section;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DualInputField extends LinearLayout {
    @BindView(R.id.fl_first_field)
    FrameLayout flFirstField;
    @BindView(R.id.fl_second_field)
    FrameLayout flSecondField;

    List<FormField> fields;
    @BindView(R.id.ll_dual_field)
    LinearLayout llDualField;

    public DualInputField(Context context, List<FormField> fields) {
        super(context);
        this.fields = fields;
        initViews();
    }

    public DualInputField(Context context, @Nullable AttributeSet attrs, List<FormField> fields) {
        super(context, attrs);
        this.fields = fields;
        initViews();
    }

    public DualInputField(Context context, @Nullable AttributeSet attrs, int defStyleAttr, List<FormField> fields) {
        super(context, attrs, defStyleAttr);
        this.fields = fields;
        initViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DualInputField(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, List<FormField> fields) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.fields = fields;
        initViews();
    }

    private void initViews() {
        addView(inflate(getContext(), R.layout.item_dual_field, null));
        ButterKnife.bind(this);
        llDualField.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Utils.addView(flFirstField, fields.get(0).getFieldLayout());
        Utils.addView(flSecondField, fields.get(1).getFieldLayout());
    }
}
