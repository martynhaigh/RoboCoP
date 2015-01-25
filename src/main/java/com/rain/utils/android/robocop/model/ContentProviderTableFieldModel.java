package com.rain.utils.android.robocop.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class ContentProviderTableFieldModel {

    public static final String STRING = "string";
    public static final String DOUBLE = "double";
    public static final String INT = "int";
    public static final String BOOLEAN = "boolean";
    public static final String LONG = "long";
    public static final String DATE_TIME = "datetime";

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
        if (mFieldType.equals(INT) || mFieldType.equals(BOOLEAN)) {
            return "INTEGER";
        } else if (mFieldType.equals(LONG) || mFieldName.equals(DOUBLE) || mFieldName.equals(DATE_TIME)) {
            return "NUMERIC";
        } else if (mFieldType.equals(STRING)) {
            return "TEXT";
        } else {
            return "CUSTOM";
        }
    }

    public String getJavaTypeString() {
        String typeLower = mFieldType.toLowerCase();
        if (typeLower.equals(BOOLEAN)) {
            return "boolean";
        } else if (typeLower.equals(INT)) {
            return "int";
        } else if (typeLower.equals(LONG) || typeLower.equals(DOUBLE) || typeLower.equals(DATE_TIME)) {
            return "double";
        } else if (typeLower.equals(STRING)) {
            return "String";
        } else {
            return mFieldType;
        }
    }

    public String getJavaTypeLongString() {
        String typeLower = mFieldType.toLowerCase();
        if (typeLower.equals(BOOLEAN)) {
            return "Boolean";
        } else if (typeLower.equals(INT)) {
            return "Integer";
        } else if (typeLower.equals(DOUBLE)) {
            return "Double";
        } else if (typeLower.equals(LONG) || typeLower.equals(DATE_TIME)) {
            return "Long";
        } else {
            return "String";
        }
    }

    public String getJavaTypeStringGetter() {
        if (mFieldType.equals(INT) || mFieldType.equals(BOOLEAN)) {
            return "getInt";
        } else if (mFieldType.equals(LONG) || mFieldType.equals(DATE_TIME)) {
            return "getLong";
        } else if (mFieldName.equals(DOUBLE)) {
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
