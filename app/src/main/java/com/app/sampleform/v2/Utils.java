package com.app.sampleform.v2;

import android.content.Context;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.sampleform.model.Form;
import com.app.sampleform.model.FormField;
import com.app.sampleform.model.Section;
import com.app.sampleform.model.SectionField;

import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String NORMAL = "String";
    private static final String DATE = "Date";
    private static final String DROP_DOWN = "DropDown";


    public static void addView(ViewGroup viewGroup, InputField field) {
        viewGroup.addView(field);
    }

    public static List<SectionField> convertSectionsIntoSectionFields(Context context, List<Section> sections, ValueChangeListener listener) {
        List<SectionField> sectionFieldList = new ArrayList<>();
        for (Section section : sections) {
            SectionField sectionField = new SectionField();
            sectionField.setSectionHeader(section.getSectionHeader());
            List<List<FormField>> formFiledsList = new ArrayList<>();
            for (List<Section.Field> fields : section.getFields()) {
                List<FormField> formFields = new ArrayList<>();
                if (fields != null) {
                    for (Section.Field field : fields) {
                        FormField formField = new FormField();
                        formField.setField(field);
                        formField.setFieldLayout(getRespectiveLayoutForField(field, context,listener));
                        formFields.add(formField);
                    }
                    formFiledsList.add(formFields);
                }
            }
            sectionField.setFormFields(formFiledsList);
            sectionFieldList.add(sectionField);
        }
        return sectionFieldList;
    }

    private static InputField getRespectiveLayoutForField(Section.Field field, Context context, ValueChangeListener listener) {
        switch (field.getDataType()) {
            case DATE:
                return new DateInputField(context, field,listener);
            case DROP_DOWN:
                return new SelectionInputField(context, field,listener);
            default:
                return new NormalInputField(context, field,listener);

        }

    }

}
