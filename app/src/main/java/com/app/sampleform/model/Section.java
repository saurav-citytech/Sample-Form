package com.app.sampleform.model;

import java.util.List;

public class Section {
    String sectionHeader;
    List<List<Field>> fields;

    public String getSectionHeader() {
        return sectionHeader;
    }

    public void setSectionHeader(String sectionHeader) {
        this.sectionHeader = sectionHeader;
    }

    public List<List<Field>> getFields() {
        return fields;
    }

    public void setFields(List<List<Field>> fields) {
        this.fields = fields;
    }

    public class Field{
        String displayLabel,dataType,defaultValue,description;
        int code;
        boolean isRequired;
        List<Value> values;

        public String getDisplayLabel() {
            return displayLabel;
        }

        public void setDisplayLabel(String displayLabel) {
            this.displayLabel = displayLabel;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public boolean isRequired() {
            return isRequired;
        }

        public void setRequired(boolean required) {
            isRequired = required;
        }

        public List<Value> getValues() {
            return values;
        }

        public void setValues(List<Value> values) {
            this.values = values;
        }
    }

    public class Value{
        String option,value;

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
