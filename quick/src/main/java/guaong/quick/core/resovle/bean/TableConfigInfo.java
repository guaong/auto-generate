package guaong.quick.core.resovle.bean;


/**
 * 生成代码包所需配置信息
 * {@link guaong.quick.core.resovle.SingleDBConfig}可以从application.xml中获取数据库相关配置
 *
 * @author guaong
 * @version 1.0
 */
public class TableConfigInfo extends DBConfigInfo{

    // 数据库表名称
    private String tableName;

    // 包路径 例如:"guaong.quick.auto"
    private String packageUrl;

    // 导入的路径 例如:"D:/quick/{tableName}"
    private String exportUrl;

    public TableConfigInfo(){}

    public TableConfigInfo(DBConfigInfo dbConfigInfo) {
        super(dbConfigInfo.getDbUrl(), dbConfigInfo.getUsername(), dbConfigInfo.getPassword(), dbConfigInfo.getDriver());
    }

    public TableConfigInfo(String packageUrl, String exportUrl, String tableName, String dbUrl, String username, String password, String driver) {
        super(dbUrl, username, password, driver);
        this.tableName = tableName;
        this.packageUrl = packageUrl;
        this.exportUrl = exportUrl;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public String getExportUrl() {
        return exportUrl;
    }

    public void setExportUrl(String exportUrl) {
        this.exportUrl = exportUrl;
    }

}
