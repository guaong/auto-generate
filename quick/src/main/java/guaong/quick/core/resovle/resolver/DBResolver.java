package guaong.quick.core.resovle.resolver;

import guaong.quick.core.exception.NotFoundException;
import guaong.quick.core.resovle.SingleDBConfig;
import guaong.quick.core.resovle.bean.DBConfigInfo;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.resovle.bean.TableInfo;
import guaong.quick.core.util.StringUtil;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.List;

/**
 * <h5>数据库表内容解析器</h5>
 * 用于解析单数据源数据
 *
 * @author guaong
 * @version 1.0 2021/3
 * @see MySQLResolver 子类,用于解析MySQL数据库表信息
 */
public abstract class DBResolver implements IResolver{

    //配置文件中配置的单数据源配置信息
    @Autowired
    private SingleDBConfig config;

    /**
     * 根据配置文件中的单数据源的信息,解析数据库表中的信息
     *
     * @param tableName 数据库表名
     * @return 生成代码相关信息
     */
    public TableDefinition resolveTable(String tableName) {
        return this.resolveTable(tableName, null);
    }

    /**
     * 根据配置文件中的单数据源的信息,解析数据库表中的信息,路径手动配置
     *
     * @param tableName 数据库表名
     * @param exportUrl 导出文件路径 例如:"D:/quick"
     * @return 生成代码相关信息
     */
    public TableDefinition resolveTable(String tableName, String exportUrl) {
        TableConfigInfo tableConfigInfo = checkAndConfig(tableName);
        if (StringUtil.hasText(exportUrl)) {
            tableConfigInfo.setExportUrl(exportUrl + "/" + tableName);
        }
        return this.resolveTable(tableConfigInfo);
    }


    /**
     * 根据手动配置的单数据库配置信息,解析数据库表中的信息
     *
     * @return 生成代码相关信息
     */
    public TableDefinition resolveTable(TableConfigInfo tableConfigInfo) {
        return this.doTableResolve(tableConfigInfo);
    }

    /**
     *
     * @return 获取数据库所有表名
     */
    public List<TableInfo> resolve(){
        return this.resolve(checkAndConfig());
    }

    /**
     *
     * @return 获取数据库所有表名
     */
    public List<TableInfo> resolve(DBConfigInfo dbConfigInfo){
        return this.doResolve(dbConfigInfo);
    }


    private TableConfigInfo checkAndConfig(String tableName) {
        TableConfigInfo tableConfigInfo = new TableConfigInfo();
        tableConfigInfo = (TableConfigInfo) checkAndConfig();
        tableConfigInfo.setTableName(tableName);
        tableConfigInfo.setPackageUrl(tableName);
        tableConfigInfo.setExportUrl("../../../" + tableName);
        return tableConfigInfo;
    }

    private DBConfigInfo checkAndConfig(){
        DBConfigInfo dbConfigInfo = new DBConfigInfo();
        String driver = config.getDriver();
        if (driver == null) throw new NotFoundException("Not found spring.datasource.driver-class-name");
        String url = config.getUrl();
        if (url == null) throw new NotFoundException("Not found spring.datasource.url");
        String username = config.getUsername();
        if (username == null) throw new NotFoundException("Not found spring.datasource.username");
        String password = config.getPassword();
        if (password == null) throw new NotFoundException("Not found spring.datasource.password");
        dbConfigInfo.setDriver(driver);
        dbConfigInfo.setDbUrl(url);
        dbConfigInfo.setUsername(username);
        dbConfigInfo.setPassword(password);
        return dbConfigInfo;
    }


    /**
     * 连接数据库
     *
     * @param dbConfigInfo 配置信息
     * @return DatabaseMetaData
     */
    private Connection connectDB(DBConfigInfo dbConfigInfo) {
        try {
            Class.forName(dbConfigInfo.getDriver());
            Connection connection = DriverManager
                    .getConnection(dbConfigInfo.getDbUrl(), dbConfigInfo.getUsername(), dbConfigInfo.getPassword());
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
    private DatabaseMetaData getDatabaseMetaData(TableConfigInfo tableConfigInfo, Connection connection) {
        if (connection == null) throw new NullPointerException("connection is null");
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet set = metaData.getTables(null, null,
                    tableConfigInfo.getTableName(), null);
            if (!set.next()) throw new NotFoundException("Datebase table" + tableConfigInfo.getTableName() + "not exist");
            return metaData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private PreparedStatement getPreparedStatement(TableConfigInfo tableConfigInfo, Connection connection){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(tableConfigInfo.getTableName());
        try {

            return connection.prepareStatement(sql.toString());
        } catch (SQLException e) {
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
    public TableDefinition doTableResolve(TableConfigInfo tableConfigInfo) {
        TableDefinition tableDefinition = new TableDefinition();
        // 连接数据库
        Connection connection = this.connectDB(tableConfigInfo);
        // 获取表数据
        String tableName = tableConfigInfo.getTableName();
        DatabaseMetaData metaData = getDatabaseMetaData(tableConfigInfo, connection);
        this.resolveTableInfo(tableName, tableDefinition, metaData);
        this.resolveColumnsInfo(tableName, tableDefinition, metaData);
//        PreparedStatement preparedStatement = getPreparedStatement(configInfo, connection);
//        this.resolveColumnsInfo(tableName, tableDefinition, preparedStatement, metaData);
        // 设置生成代码的配置信息
        tableDefinition.setConfigInfo(tableConfigInfo);
        // 关闭数据库
        this.closeDB(connection);
        return tableDefinition;
    }

    public List<TableInfo> doResolve(DBConfigInfo dbConfigInfo){
        List<TableInfo> list = null;
        // 连接数据库
        Connection connection = this.connectDB(dbConfigInfo);
        if (connection == null) throw new NullPointerException("connection is null");
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            list = resolveDBInfo(metaData);
            // 关闭数据库
            this.closeDB(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            // 关闭数据库
            this.closeDB(connection);
        }
        return list;
    }


    public abstract void resolveTableInfo(String tableName, TableDefinition tableDefinition, DatabaseMetaData metaData);

    public abstract void resolveColumnsInfo(String tableName, TableDefinition tableDefinition, DatabaseMetaData metaData);

    public abstract List<TableInfo> resolveDBInfo(DatabaseMetaData metaData);
}
