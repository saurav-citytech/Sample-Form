package com.app.sampleform.customviews;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

import com.app.sampleform.R;
import com.app.sampleform.model.FormField;
import com.app.sampleform.model.Section;
import com.app.sampleform.model.SectionField;
import com.app.sampleform.v2.DualInputField;
import com.app.sampleform.v2.Utils;
import com.app.sampleform.v2.ValueChangeListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class FormView extends ScrollView implements ValueChangeListener {

    @BindView(R.id.ll_form)
    LinearLayout llForm;

    List<Section> sections = getSections();
    List<SectionField> sectionFields;
    @BindView(R.id.rl_form)
    RelativeLayout rlForm;



    public FormView(Context context) {
        super(context);
        initViews();
    }

    public FormView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public FormView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FormView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews();
    }

    public abstract List<Section> getSections();


    private void initViews() {
        addView(inflate(getContext(), R.layout.layout_form, null));
        ButterKnife.bind(this);
        rlForm.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        sectionFields = Utils.convertSectionsIntoSectionFields(getContext(), sections, this);
        populateFormFields(sectionFields);
    }




    private void populateFormFields(List<SectionField> sectionFields) {
        for (SectionField sectionField : sectionFields) {
            populateSectionHeader(sectionField.getSectionHeader());
            for (List<FormField> formFields : sectionField.getFormFields()) {
                populateFields(formFields);
            }
        }
    }

    private void populateSectionHeader(String sectionHeader) {
        AppCompatTextView sectionHeaderView = (AppCompatTextView) inflate(getContext(), R.layout.section_header, null);
        sectionHeaderView.setText(sectionHeader);
        llForm.addView(sectionHeaderView);
    }

    private void populateFields(List<FormField> fields) {
        if (fields.size() == 1) {
            Utils.addView(llForm, fields.get(0).getFieldLayout());
        } else {
            populateDualFields(fields);
        }
    }

    private void populateDualFields(List<FormField> fields) {
        llForm.addView(new DualInputField(getContext(), fields));
    }




    protected void validateFormFields() {
        for (int i = 0; i < sectionFields.size(); i++) {
            List<List<FormField>> formFieldsList = sectionFields.get(i).getFormFields();
            for (int j = 0; j < formFieldsList.size(); j++) {
                List<FormField> formFields = formFieldsList.get(j);
                for (int k = 0; k < formFields.size(); k++) {
                    FormField formField = formFields.get(k);
                    if ((formField.getInputValue() == null || formField.getInputValue().isEmpty()) && formField.getField().isRequired()) {
                        formField.getFieldLayout().showEmptyWarning();
                        return;
                    } else {
                        formField.getFieldLayout().hideWarning();
                    }
                }
            }
        }
    }

    @Override
    public void onValueChange(int code, String changedValue) {
        for (int i = 0; i < sectionFields.size(); i++) {
            List<List<FormField>> formFieldsList = sectionFields.get(i).getFormFields();
            for (int j = 0; j < formFieldsList.size(); j++) {
                List<FormField> formFields = formFieldsList.get(j);
                for (int k = 0; k < formFields.size(); k++) {
                    FormField formField = formFields.get(k);
                    if (formField.getField().getCode() == code) {
                        sectionFields.get(i).getFormFields().get(j).get(k).setInputValue(changedValue);
                        return;
                    }
                }
            }
        }
    }

    public void onViewClicked() {
        validateFormFields();
    }
}
