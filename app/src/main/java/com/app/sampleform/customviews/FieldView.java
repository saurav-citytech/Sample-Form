package com.app.sampleform.customviews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;

import butterknife.BindView;
import butterknife.ButterKnife;

abstract class FieldView extends LinearLayout {
    private static final String STRING = "String";
    @BindView(R.id.fl_text_field)
    FrameLayout flTextField;

    public FieldView(Context context) {
        super(context);
        initView();
    }

    public FieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FieldView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        addView(inflate(getContext(), R.layout.item_field, null));
        ButterKnife.bind(this);
        addField(setField());
    }

    private void addField(Section.Field setField) {
        Section.Field field = setField;
        if (field.getDataType().equals(STRING))
            flTextField.addView(new NormalField(getContext()) {
                @Override
                public Section.Field setField() {
                    return field;
                }
            });
        else
            flTextField.addView(new SelectionField(getContext()) {
                @Override
                public Section.Field setField() {
                    return field;
                }
            });
    }


    abstract Section.Field setField();
}
