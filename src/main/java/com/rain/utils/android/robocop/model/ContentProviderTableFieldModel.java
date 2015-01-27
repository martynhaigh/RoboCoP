package com.rain.utils.android.robocop.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class ContentProviderTableFieldModel {



    @SerializedName("type")
    private String mFieldType;

    @SerializedName("name")
    private String mFieldName;

    @SerializedName("unique")
    private String mFieldUnique = "";

    @SerializedName("serialized")
    private String mFieldSerialized = "";

    @SerializedName("default")
    private String mFieldDefault = "";

    public ContentProviderTableFieldModel(String fieldType, String fieldName, String fieldUnique, String fieldSerialized, String fieldDefault) {
        mFieldType = fieldType;
        mFieldName = fieldName;
        mFieldUnique = fieldUnique;
        mFieldSerialized = fieldSerialized;
        mFieldDefault = fieldDefault;
    }

    public String getFieldType() {
        return mFieldType;
    }

    public String getFieldName() {
        return mFieldName;
    }

    public String getFieldDefault() {
        return mFieldDefault;
    }

    public String getConstantString() {
        return StringUtils.getConstantString(mFieldName);
    }

    public String getTypeString() {
        return StringUtils.getTypeString(mFieldType);
    }

    public String getJavaTypeString() {
        return StringUtils.getJavaTypeString(mFieldType);
    }

    public String getJavaTypeLongString() {
        return StringUtils.getJavaTypeLongString(mFieldType);
    }

    public String getJavaTypeStringGetter() {
        if (mFieldType.equals(StringUtils.INT) || mFieldType.equals(StringUtils.BOOLEAN)) {
            return "getInt";
        } else if (mFieldType.equals(StringUtils.LONG) || mFieldType.equals(StringUtils.DATE_TIME)) {
            return "getLong";
        } else if (mFieldName.equals(StringUtils.DOUBLE)) {
            return "getDouble";
        } else {
            return "getString";
        }
    }

    public String getUniqueConstraint() {
        Set<String> validConstraints = new HashSet<String>();
        validConstraints.add("ABORT");
        validConstraints.add("FAIL");
        validConstraints.add("IGNORE");
        validConstraints.add("NONE");
        validConstraints.add("REPLACE");
        validConstraints.add("ROLLBACK");

        if (validConstraints.contains(mFieldUnique)) {
            return mFieldUnique;
        }
        return "";
    }

    public String getFieldSerialized() {
        if (mFieldSerialized != "" && mFieldSerialized != null) {
            return mFieldSerialized;
        }
        return mFieldName;
    }

    public boolean isCustom() {
        return "CUSTOM".equals(getTypeString());
    }
    public boolean isDateTime() {
        return "DATETIME".equals(getTypeString());
    }

    public String getPrivateVariableName() {
        return StringUtils.getPrivateVariableName(mFieldName);
    }

    public String getNameAsTitleCase() {
        return StringUtils.convertToTitleCase(mFieldName);
    }


}