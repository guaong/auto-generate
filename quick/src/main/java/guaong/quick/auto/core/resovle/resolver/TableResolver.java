package guaong.quick.auto.core.resovle.resolver;

import guaong.quick.auto.core.exception.NotFoundException;
import guaong.quick.auto.core.resovle.SingleDBConfig;
import guaong.quick.auto.core.resovle.bean.TableDefinition;
import guaong.quick.auto.core.resovle.bean.ConfigInfo;
import guaong.quick.auto.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

/**
 * <h5>数据库表内容解析器</h5>
 * 用于解析单数据源数据
 *
 * @author guaong
 * @version 1.0 2021/3
 * @see MySQLTableResolver 子类,用于解析MySQL数据库表信息
 */
public abstract class TableResolver {

    //配置文件中配置的单数据源配置信息
    @Autowired
    private SingleDBConfig config;

    /**
     * 根据配置文件中的单数据源的信息,解析数据库表中的信息
     *
     * @param tableName 数据库表名
     * @return 生成代码相关信息
     */
    public TableDefinition resolve(String tableName) {
        return this.resolve(tableName, null);
    }

    /**
     * 根据配置文件中的单数据源的信息,解析数据库表中的信息,路径手动配置
     * @param tableName 数据库表名
     * @param exportUrl 导出文件路径 例如:"D:/quick"
     * @return 生成代码相关信息
     */
    public TableDefinition resolve(String tableName, String exportUrl){
        ConfigInfo configInfo = checkAndConfig(tableName);
        if (StringUtil.hasText(exportUrl)){
            configInfo.setExportUrl(exportUrl + "/" + tableName);
        }
        return this.resolve(configInfo);
    }


    /**
     * 根据手动配置的单数据库配置信息,解析数据库表中的信息
     *
     * @param configInfo
     * @return 生成代码相关信息
     */
    public TableDefinition resolve(ConfigInfo configInfo) {
        return this.doResolve(configInfo);
    }


    private ConfigInfo checkAndConfig(String tableName) {
        ConfigInfo configInfo = new ConfigInfo();
        String driver = config.getDriver();
        if (driver == null) throw new NotFoundException("Not found spring.datasource.driver-class-name");
        String url = config.getUrl();
        if (url == null) throw new NotFoundException("Not found spring.datasource.url");
        String username = config.getUsername();
        if (username == null) throw new NotFoundException("Not found spring.datasource.username");
        String password = config.getPassword();
        if (password == null) throw new NotFoundException("Not found spring.datasource.password");
        configInfo.setDriver(driver);
        configInfo.setDbUrl(url);
        configInfo.setUsername(username);
        configInfo.setPassword(password);
        configInfo.setTableName(tableName);
        configInfo.setPackageUrl(tableName);
        configInfo.setExportUrl("../../../" + tableName);
        return configInfo;
    }

    /**
     * 连接数据库
     *
     * @param configInfo 配置信息
     * @return DatabaseMetaData
     */
    private Connection connectDB(ConfigInfo configInfo) {
        try {
            Class.forName(configInfo.getDriver());
            Connection connection = DriverManager
                    .getConnection(configInfo.getDbUrl(), configInfo.getUsername(), configInfo.getPassword());
            if (connection == null) throw new NotFoundException("Not found this db");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @return 返回用于获取数据库表信息的DatabaseMetaData
     */
    private DatabaseMetaData getDatabaseMetaData(ConfigInfo configInfo, Connection connection){
        if (connection == null) throw new NullPointerException("connection is null");
        try{
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet set = metaData.getTables(null, null,
                    configInfo.getTableName(), null);
            if (!set.next()) throw new NotFoundException("Datebase table" + configInfo.getTableName() + "not exist");
            return metaData;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 关闭数据库连接
     */
    private void closeDB(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 解析数据库内容
     *
     * @return 使用TableDefinition存储表信息
     */
    public TableDefinition doResolve(ConfigInfo configInfo){
        TableDefinition tableDefinition = new TableDefinition();
        // 连接数据库
        Connection connection = this.connectDB(configInfo);
        // 获取表数据
        String tableName = configInfo.getTableName();
        DatabaseMetaData metaData = getDatabaseMetaData(configInfo, connection);
        this.resolveTableInfo(tableName, tableDefinition, metaData);
        this.resolveColumnsInfo(tableName, tableDefinition, metaData);
        // 设置生成代码的配置信息
        tableDefinition.setConfigInfo(configInfo);
        // 关闭数据库
        this.closeDB(connection);
        return tableDefinition;
    }


    public abstract void resolveTableInfo(String tableName, TableDefinition tableDefinition, DatabaseMetaData metaData);


    public abstract void resolveColumnsInfo(String tableName, TableDefinition tableDefinition, DatabaseMetaData metaData);

}
