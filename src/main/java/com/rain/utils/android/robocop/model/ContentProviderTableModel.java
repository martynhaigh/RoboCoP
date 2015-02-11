package com.rain.utils.android.robocop.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class ContentProviderTableModel {

    @SerializedName("name")
    private String mTableName;

    @SerializedName("members")
    private List<ContentProviderTableFieldModel> mFields = new ArrayList<ContentProviderTableFieldModel>();
    private boolean mUsesDateTime;


    private ContentProviderTableFieldModel mPrimaryKey;

    public ContentProviderTableModel(String tableName) {
        mTableName = tableName;
    }

    public List<ContentProviderTableFieldModel> getFields() {
        return mFields;
    }

    public void addField(ContentProviderTableFieldModel field) {
        if (field.isPrimaryKey()) {
            if (!hasPrimaryKey()) {
                setPrimaryKey(field);
            }
        }
        mFields.add(0, field);
    }

    public void addField(String keyType, String fieldName, String type, String fieldUnique, String fieldSerialized, String fieldDefault) {
        addField(new ContentProviderTableFieldModel(keyType, type, fieldName, fieldUnique, fieldSerialized, fieldDefault));
    }

    public String getTableName() {
        return mTableName;
    }


    public String getTableClassName() {
        return StringUtils.convertToTitleCase(mTableName);
    }

    public String getTableConstantName() {
        return StringUtils.getConstantString(mTableName);
    }

    public void setUsesDateTime(boolean usesDateTime) {
        mUsesDateTime = usesDateTime;
    }

    public boolean isUsesDateTime() {
        return mUsesDateTime;
    }

    @Override
    public boolean equals(Object o) {
        return ((ContentProviderTableModel) o).getTableName().equals(getTableName());
    }

    public boolean hasPrimaryKey() throws IllegalStateException {
        boolean primaryKeyFound = false;
        for (ContentProviderTableFieldModel field : mFields) {
            if (field.isPrimaryKey()) {
                if (primaryKeyFound) {
                    throw new IllegalStateException("Can't have more than one primary key in " + getTableName() + " [" + getPrimaryKey().getFieldName() + " - " + field.getFieldName() + "]");
                }
                setPrimaryKey(field);
                primaryKeyFound = true;
            }
        }

        return primaryKeyFound;
    }

    public void setPrimaryKey(ContentProviderTableFieldModel field) {
        mPrimaryKey = field;
    }

    public ContentProviderTableFieldModel getPrimaryKey() {
        return mPrimaryKey;
    }
}
