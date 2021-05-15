package guaong.quick.core.resovle.bean;

import java.io.Serializable;

public class TableInfo implements Serializable {

    private String tableName;

    private String tableType;

    private String tableCat;

    private String remark;


    public TableInfo(){}

    public TableInfo(String tableName, String tableType, String tableCat, String remark) {
        this.tableName = tableName;
        this.tableType = tableType;
        this.tableCat = tableCat;
        this.remark = remark;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
