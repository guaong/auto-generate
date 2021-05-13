package guaong.quick.core.resovle.bean;

import java.util.Map;

/**
 * 用于存放解析的数据库表的数据
 *
 * @author guaong
 * @version 1.0
 */
public class TableDefinition {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 注释
     */
    private String description;

    /**
     * 存放表中字段
     */
    private Map<String, ColumnDefinition> columnMap;

    /**
     * 生成代码包所需配置信息
     */
    private TableConfigInfo tableConfigInfo;

    public TableDefinition() {
    }

    public TableDefinition(String tableName, String description, Map<String, ColumnDefinition> columnMap, TableConfigInfo tableConfigInfo) {
        this.tableName = tableName;
        this.description = description;
        this.columnMap = columnMap;
        this.tableConfigInfo = tableConfigInfo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, ColumnDefinition> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, ColumnDefinition> columnMap) {
        this.columnMap = columnMap;
    }

    public TableConfigInfo getConfigInfo() {
        return tableConfigInfo;
    }

    public void setConfigInfo(TableConfigInfo tableConfigInfo) {
        this.tableConfigInfo = tableConfigInfo;
    }
}
