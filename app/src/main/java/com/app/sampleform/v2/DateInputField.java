package com.app.sampleform.v2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.app.sampleform.R;
import com.app.sampleform.model.Section;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateInputField extends InputField implements DatePickerDialog.OnDateSetListener {

    Section.Field field;
    ValueChangeListener listener;


    @BindView(R.id.ll_selection)
    LinearLayout llSelection;
    @BindView(R.id.et_field)
    TextInputEditText etField;
    @BindView(R.id.text_input_layout)
    TextInputLayout textInputLayout;
    @BindView(R.id.tv_description)
    AppCompatTextView tvDescription;

    public DateInputField(Context context, Section.Field field, ValueChangeListener listener) {
        super(context);
        this.field = field;
        this.listener = listener;
        initViews();
    }


    public DateInputField(Context context, AttributeSet attrs, Section.Field field) {
        super(context, attrs);
        this.field = field;
        initViews();
    }

    public DateInputField(Context context, AttributeSet attrs, int defStyleAttr, Section.Field field) {
        super(context, attrs, defStyleAttr);
        this.field = field;
        initViews();
    }


    private void initViews() {
        addView(inflate(getContext(), R.layout.layout_pick, null));
        ButterKnife.bind(this);
        llSelection.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textInputLayout.setHint(field.getDisplayLabel());
        etField.setOnClickListener(v -> setDatePicker());
    }

    private void setDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String formattedDate = String.valueOf(year).concat("/")
                .concat(String.valueOf(month + 1).concat("/")
                        .concat(String.valueOf(dayOfMonth)));
        etField.setText(formattedDate);
        listener.onValueChange(field.getCode(), formattedDate);
    }


    @Override
    public void showEmptyWarning() {
        textInputLayout.setError("Required Field");
    }

    @Override
    public void hideError() {
        textInputLayout.setError(null);
    }
}
