package com.app.sampleform.customviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class NormalField extends TextInputLayout {
    @BindView(R.id.et_field)
    TextInputEditText etField;
    @BindView(R.id.text_input_layout)
    TextInputLayout textInputLayout;

    public NormalField(Context context) {
        super(context);
        initView();
    }

    public NormalField(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NormalField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        Section.Field field = setField();
        View view = inflate(getContext(),R.layout.normal_field,null);
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(view);

        ButterKnife.bind(this);
        textInputLayout.setHint(field.getDisplayLabel());
        etField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public abstract Section.Field setField();
}
