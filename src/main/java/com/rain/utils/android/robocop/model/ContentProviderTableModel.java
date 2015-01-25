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

    public ContentProviderTableModel(String tableName) {
        mTableName = tableName;
    }

    public List<ContentProviderTableFieldModel> getFields() {
        return mFields;
    }

    public void addField(String fieldName, String type, String fieldUnique, String fieldSerialized, String fieldDefault) {
        mFields.add(new ContentProviderTableFieldModel(type, fieldName, fieldUnique, fieldSerialized, fieldDefault));
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
}
