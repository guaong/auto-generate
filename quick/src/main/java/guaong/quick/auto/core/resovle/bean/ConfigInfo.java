package guaong.quick.auto.core.resovle.bean;


/**
 * 生成代码包所需配置信息
 * {@link guaong.quick.auto.core.resovle.SingleDBConfig}可以从application.xml中获取数据库相关配置
 *
 * @author guaong
 * @version 1.0
 */
public class ConfigInfo {

    // 包路径 例如:"guaong.quick.auto"
    private String packageUrl;

    // 导入的路径 例如:"D:/quick/{tableName}"
    private String exportUrl;

    // 数据库表名称
    private String tableName;

    // 数据库url 例如:"jdbc:mysql://localhost:3306/quick"
    private String dbUrl;

    // 连接数据库的用户名
    private String username;

    // 连接数据库的密码
    private String password;

    // 数据库驱动 例如:"com.mysql.cj.jdbc.Driver"
    private String driver;

    public ConfigInfo() {
    }

    public ConfigInfo(String packageUrl, String exportUrl, String tableName, String dbUrl, String username, String password, String driver) {
        this.packageUrl = packageUrl;
        this.exportUrl = exportUrl;
        this.tableName = tableName;
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
        this.driver = driver;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
