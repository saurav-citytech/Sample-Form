package com.app.sampleform.model;

import android.widget.LinearLayout;

import com.app.sampleform.v2.InputField;

public class FormField {
    Section.Field field;
    InputField fieldLayout;
    String inputValue;

    public Section.Field getField() {
        return field;
    }

    public void setField(Section.Field field) {
        this.field = field;
    }

    public InputField getFieldLayout() {
        return fieldLayout;
    }

    public void setFieldLayout(InputField fieldLayout) {
        this.fieldLayout = fieldLayout;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }
}
