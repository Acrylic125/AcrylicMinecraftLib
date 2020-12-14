package com.acrylic.universal.utils;

/**
 * Adapted from {@link com.acrylic.text.TextTemplate}
 */
public abstract class TextTemplate {

    private String valueFormat = "";
    private String suffixFormat = "";

    public String getValueFormat() {
        return valueFormat;
    }

    public void setValueFormat(String valueFormat) {
        this.valueFormat = valueFormat;
    }

    public String getSuffixFormat() {
        return suffixFormat;
    }

    public void setSuffixFormat(String suffixFormat) {
        this.suffixFormat = suffixFormat;
    }
}
