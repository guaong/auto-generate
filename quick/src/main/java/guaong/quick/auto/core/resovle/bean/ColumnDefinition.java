package guaong.quick.auto.core.resovle.bean;


/**
 * 用于存放解析的数据库表的列字段的内容
 *
 * @author guaong
 * @version 1.0
 * @see TableDefinition 属于其中的一部分
 */
public class ColumnDefinition {

    private String columnName;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 注释
     */
    private String description;

    /**
     * 是否主键
     */
    private boolean isPrimaryKey = false;

    public ColumnDefinition() {
    }

    public ColumnDefinition(String columnName, String type, String description, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.type = type;
        this.description = description;
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    @Override
    public String toString() {
        return "ColumnDefinition{" +
                "columnName='" + columnName + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", isPrimaryKey=" + isPrimaryKey +
                '}';
    }
}
