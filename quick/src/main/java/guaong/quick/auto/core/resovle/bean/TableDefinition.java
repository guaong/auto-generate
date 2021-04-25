package guaong.quick.auto.core.resovle.bean;

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
    private ConfigInfo configInfo;

    public TableDefinition() {
    }

    public TableDefinition(String tableName, String description, Map<String, ColumnDefinition> columnMap, ConfigInfo configInfo) {
        this.tableName = tableName;
        this.description = description;
        this.columnMap = columnMap;
        this.configInfo = configInfo;
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

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }
}
