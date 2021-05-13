package guaong.quick.core.resovle.bean;

public class DBConfigInfo {

    // 数据库url 例如:"jdbc:mysql://localhost:3306/quick"
    private String dbUrl;

    // 连接数据库的用户名
    private String username;

    // 连接数据库的密码
    private String password;

    // 数据库驱动 例如:"com.mysql.cj.jdbc.Driver"
    private String driver;

    public DBConfigInfo() {
    }

    public DBConfigInfo(String dbUrl, String username, String password, String driver) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
        this.driver = driver;
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
