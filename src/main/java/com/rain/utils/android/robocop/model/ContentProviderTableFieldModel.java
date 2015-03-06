package com.rain.utils.android.robocop.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class ContentProviderTableFieldModel {


    @SerializedName("key")
    private String mKeyType;

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

    public ContentProviderTableFieldModel(String keyType, String fieldType, String fieldName, String fieldUnique, String fieldSerialized, String fieldDefault) {
        mKeyType = keyType;
        mFieldType = fieldType;
        mFieldName = fieldName;
        mFieldUnique = fieldUnique;
        mFieldSerialized = fieldSerialized;
        mFieldDefault = fieldDefault;
    }

    public String getKeyType() {
        return mKeyType;
    }

    public String getFieldType() {
        return mFieldType;
    }

    public String getFieldName() {
        return mFieldName;
    }

    public String getFieldDefault() {

//        if (mFieldType.equals(StringUtils.BOOLEAN)) {
//            if(mFieldDefault.toLowerCase().equals("true")) {
//                return "1";
//            }
//            return "0";
//        } else

        if (mFieldType.equals(StringUtils.STRING)) {
            return "\"" + mFieldDefault + "\"";
        }
        return mFieldDefault;
    }

    public boolean hasDefaultSet() { return mFieldDefault != null && !mFieldDefault.equals(""); }

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

    public String getJavaParcelReader() {
        return StringUtils.getJavaParcelMethodGenerator(mFieldType, "read");
    }

    public String getJavaParcelWriter() {
        return StringUtils.getJavaParcelMethodGenerator(mFieldType, "write");
    }


    public String getJavaSerializableReader() {
        return StringUtils.getJavaSerializableMethodGenerator(mFieldType, "read");
    }

    public String getJavaSerializableWriter() {
        return StringUtils.getJavaSerializableMethodGenerator(mFieldType, "write");
    }

    public String getJavaTypeStringRead() {
        return StringUtils.getJavaTypeStringGetter(mFieldType, "read");
    }

    public String getJavaTypeStringGet() {
        return StringUtils.getJavaTypeStringGetter(mFieldType, "get");

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

    public boolean isPrimaryKey() {
        return "primary".equals(mKeyType);
    }
}
