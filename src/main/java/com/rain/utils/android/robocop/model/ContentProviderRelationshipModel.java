package com.rain.utils.android.robocop.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created with IntelliJ IDEA.
 * User: dustin
 * Date: 2/4/14
 * Time: 4:52 PM
 */
public class ContentProviderRelationshipModel {

    public static final String RELATIONSHIP_TYPE_TO_ONE = "to_one";
    public static final String RELATIONSHIP_TYPE_TO_MANY = "to_many";
    public static final String RELATIONSHIP_TYPE_MANY_TO_MANY = "many_to_many";

    @SerializedName("type")
    private String mReferenceType;

    @SerializedName("name")
    private String mCustomName;

    @SerializedName("left_table")
    private String mLeftTableName;

    @SerializedName("left_field_name")
    private String mLeftFieldName;

    @SerializedName("left_field_type")
    private String mLeftFieldType;

    @SerializedName("right_table")
    private String mRightTableName;

    private ContentProviderTableModel mLeftTableModel;
    private ContentProviderTableModel mRightTableModel;
    private ContentProviderTableFieldModel leftTableField;
    private ContentProviderTableFieldModel rightTableField;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ContentProviderRelationshipModel)) {
            System.out.println("not a ContentProviderRelationshipModel");
            return false;
        }
        ContentProviderRelationshipModel other = (ContentProviderRelationshipModel) obj;


        //System.out.println(other.getLeftTableName() + "==" + getLeftTableName() + " = " + other.getLeftTableName().equals(getLeftTableName()));
        //System.out.println(other.getRightTableName() + "==" + getRightTableName() + " = " + other.getRightTableName().equals(getRightTableName()));
        //System.out.println(other.getReferenceType() + "==" + getReferenceType() + " = " + other.getReferenceType().equals(getReferenceType()));

        return other.getLeftTableName().equals(getLeftTableName()) && other.getRightTableName().equals(getRightTableName());
    }

    public ContentProviderRelationshipModel(String referenceType, String customName, String leftTableName, String rightTableName) {
        mReferenceType = referenceType;
        mCustomName = customName;
        mLeftTableName = leftTableName;
        mRightTableName = rightTableName;
    }

    public String getReferenceType() {
        return mReferenceType;
    }


    public ContentProviderTableModel getLeftTableModel() {
        return mLeftTableModel;
    }

    public void setLeftTableModel(ContentProviderTableModel leftTableModel) {
        mLeftTableModel = leftTableModel;
    }

    public String getLeftTableName() {
        return mLeftTableName;
    }

    public String getLeftTableClassName() {
        return StringUtils.convertToTitleCase(mLeftTableName);
    }

    public String getLeftTableConstantName() {
        return StringUtils.getConstantString(mLeftTableName);
    }

    public String getLeftTableFieldName() {
        return mLeftFieldName;
    }
    public String getLeftTableFieldNameAsTitleCase() {
        return StringUtils.convertToTitleCase(mLeftFieldName);
    }
    public String getRightTableFieldName() {
        return mLeftTableName + "_" + mLeftFieldName;
    }

    public void setLeftTableFieldName(String leftFieldName) {
        mLeftFieldName = leftFieldName;
    }

    public String getLeftTableFieldType() {
        return (mLeftFieldType != null) ? StringUtils.getJavaTypeString(mLeftFieldType) : "Long";
    }

    public String getLeftTableFieldDBTypeString() {
        return (mLeftFieldType != null) ? StringUtils.getTypeString(mLeftFieldType) : "INTEGER";
    }

    public String getLeftTableForeignKey() {
        return StringUtils.getConstantString(mLeftTableName) + "_" + leftTableField.getConstantString();
    }


    public ContentProviderTableModel getRightTableModel() {
        return mRightTableModel;
    }

    public void setRightTableModel(ContentProviderTableModel rightTableModel) {
        mRightTableModel = rightTableModel;
    }

    public String getRightTableName() {
        return mRightTableName;
    }

    public String getRightTableClassName() {
        return StringUtils.convertToTitleCase(mRightTableName);
    }

    public String getRightTableConstantName() {
        return StringUtils.getConstantString(mRightTableName);
    }


    public String getCustomName() {
        return mCustomName;
    }

    public void setCustomName(String customName) {
        mCustomName = customName;
    }

    public String getForeignKeyNameForTable(ContentProviderTableModel table) {
        if (table == null) return null;
        if (leftTableField == null) {
            throw new IllegalArgumentException(getLeftTableName() + " -> " + getRightTableName() + " (" + table.getTableName() + ")");
        }
        if (mReferenceType.equals(RELATIONSHIP_TYPE_TO_MANY) && mRightTableModel == table) {
            return mLeftTableModel.getTableConstantName() + "_" + leftTableField.getConstantString();
        }
        return null;
    }

    public String getForeignKeyPrivateVariableNameForTable(ContentProviderTableModel table) {
        String constantName = getForeignKeyNameForTable(table);
        if (constantName == null) return null;
        constantName = StringUtils.convertToTitleCase(constantName);
        return "m" + constantName;
    }

    public String getForeignKeyVariableNameForTable(ContentProviderTableModel table) {
        String constantName = getForeignKeyNameForTable(table);
        if (constantName == null) return null;
        return StringUtils.convertToCamelCase(constantName);
    }

    public String getForeignKeyVariableAsTitleCase(ContentProviderTableModel table) {
        String constantName = getForeignKeyNameForTable(table);
        if (constantName == null) return null;
        return StringUtils.convertToTitleCase(constantName);
    }

    public void setLeftTableField(ContentProviderTableFieldModel leftTableField) {
        if(leftTableField == null) {
            throw new IllegalArgumentException(getLeftTableName() + " -> " + getRightTableName());
        }
        this.leftTableField = leftTableField;
        this.setLeftTableFieldName(leftTableField.getFieldName());
        this.setLeftTableFieldType(leftTableField.getFieldType());
    }


    public ContentProviderTableFieldModel getleftTableField() {
        return leftTableField;
    }
    public void setLeftTableFieldType(String leftFieldType) {
        this.mLeftFieldType = leftFieldType;
    }

    public String toString() {
        return getLeftTableName() + " -> " + getRightTableName();
    }

    public String getRightTableFieldType() {
        if(leftTableField == null) {
            throw new IllegalArgumentException(toString() + " - Cannot get right field type as left table field not defined.");

        }
        return leftTableField.getFieldType();
    }
}
