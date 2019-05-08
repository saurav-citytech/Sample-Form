package com.app.sampleform.v2;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectionInputField extends InputField implements OptionPickListener {

    Section.Field field;

    Dialog dialog;
    ValueChangeListener listener;
    @BindView(R.id.et_field)
    TextInputEditText etField;
    @BindView(R.id.text_input_layout)
    TextInputLayout textInputLayout;
    @BindView(R.id.tv_description)
    AppCompatTextView tvDescription;
    @BindView(R.id.ll_selection)
    LinearLayout llSelection;

    public SelectionInputField(Context context, Section.Field field, ValueChangeListener listener) {
        super(context);
        this.field = field;
        this.listener = listener;
        initViews();

    }

    public SelectionInputField(Context context, AttributeSet attrs, Section.Field field) {
        super(context, attrs);
        this.field = field;
        initViews();
    }

    public SelectionInputField(Context context, AttributeSet attrs, int defStyleAttr, Section.Field field) {
        super(context, attrs, defStyleAttr);
        this.field = field;
        initViews();
    }

    private void initViews() {
        addView(inflate(getContext(), R.layout.layout_pick, null));
        ButterKnife.bind(this);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llSelection.setLayoutParams(params);
        textInputLayout.setHint(field.getDisplayLabel());
        setOnClickListener(v -> {
            pickFromOptions();
        });
        etField.setOnClickListener(v -> pickFromOptions());
    }

    private void pickFromOptions() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_options);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setTitle(field.getDisplayLabel());
        dialog.setCanceledOnTouchOutside(true);
        RecyclerView rvOptions = dialog.findViewById(R.id.rv_options);
        rvOptions.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOptions.setAdapter(new OptionAdapter(field.getValues(), this));
        dialog.show();
    }


    @Override
    public void onOptionPicked(Section.Value value) {
        etField.setText(value.getOption());
        listener.onValueChange(field.getCode(), value.getOption());
        dialog.dismiss();
    }

    @Override
    public void showEmptyWarning() {
        textInputLayout.setError("Required Field");
    }

    @Override
    public void hideWarning() {
        textInputLayout.setError(null);
    }
}
