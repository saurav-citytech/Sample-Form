package com.app.sampleform.model;

import java.util.List;

public class SectionField {
    String sectionHeader;
    List<List<FormField>> formFields;

    public String getSectionHeader() {
        return sectionHeader;
    }

    public void setSectionHeader(String sectionHeader) {
        this.sectionHeader = sectionHeader;
    }

    public List<List<FormField>> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<List<FormField>> formFields) {
        this.formFields = formFields;
    }
}
