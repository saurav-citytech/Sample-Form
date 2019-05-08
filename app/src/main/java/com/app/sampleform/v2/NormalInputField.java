package com.app.sampleform.v2;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.appcompat.widget.AppCompatTextView;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NormalInputField extends InputField {
    @BindView(R.id.et_field)
    TextInputEditText etField;
    @BindView(R.id.text_input_layout)
    TextInputLayout textInputLayout;
    @BindView(R.id.tv_description)
    AppCompatTextView tvDescription;

    Section.Field field;
    ValueChangeListener listener;

    @BindView(R.id.ll_single_field)
    LinearLayout llSingleField;

    public NormalInputField(Context context, Section.Field field, ValueChangeListener listener) {
        super(context);
        this.field = field;
        this.listener = listener;
        initViews();
    }

    public NormalInputField(Context context, AttributeSet attrs, Section.Field field) {
        super(context, attrs);
        this.field = field;
        initViews();
    }


    public NormalInputField(Context context, AttributeSet attrs, int defStyleAttr, Section.Field field) {
        super(context, attrs, defStyleAttr);
        this.field = field;
        initViews();
    }

    private void initViews() {

        addView(inflate(getContext(), R.layout.item_single_field, null));
        ButterKnife.bind(this);

        llSingleField.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textInputLayout.setHint(field.getDisplayLabel());
        addTextChangeListener();
        setInputType();
    }

    private void setInputType() {

    }

    private void addTextChangeListener() {
        etField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listener.onValueChange(field.getCode(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void showEmptyWarning() {
        textInputLayout.setError("Required Field");
    }

    @Override
    public void hideError() {
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }


}
