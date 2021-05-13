package guaong.quick.core.resovle.resolver;

import guaong.quick.core.resovle.bean.ColumnDefinition;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.util.DBUtil;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * MySQL table resolver
 * 使用：实例化一个MySQLTableResolver 调用resolver.resolve();
 *
 * @author guaong
 * @version 1.0
 */
@Component
public class MySQLResolver extends DBResolver {

    @Override
    public void resolveTableInfo(String tableName, TableDefinition tableDefinition, DatabaseMetaData metaData) {
        try {
            // 获取table相关信息
            // tableSet.getString()中的参数名称参见com.mysql.cj.jdbc.DatabaseMetaDate.createTablesFields()
            ResultSet tableSet = metaData.getTables(null, null, tableName, null);
            while (tableSet.next()) {
                tableDefinition.setTableName(tableSet.getString("TABLE_NAME"));
                tableDefinition.setDescription(tableSet.getString("REMARKS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void resolveColumnsInfo(String tableName, TableDefinition tableDefinition, DatabaseMetaData metaData) {
        Map<String, ColumnDefinition> attrDefinitionMap = new LinkedHashMap<>();
        try{
            // 获取主键
            String pkName = getPkName(tableName, metaData);

            // 获取table中每个column的相关信息
            // columnSet.getString()中的参数名称参见com.mysql.cj.jdbc.DatabaseMetaDate.createColumnsFields()
            ResultSet columnSet = metaData.getColumns(null, null, tableName, null);
            while (columnSet.next()) {
                ColumnDefinition columnDefinition = new ColumnDefinition();
                // 列名
                columnDefinition.setColumnName(columnSet.getString("COLUMN_NAME"));
                // 注释
                columnDefinition.setDescription(columnSet.getString("REMARKS"));
                // 类型
                columnDefinition.setType(DBUtil.Mysql2Java(columnSet.getString("TYPE_NAME")));
                // 主键
                if (pkName.equalsIgnoreCase(columnDefinition.getColumnName())) { // 是主键
                    columnDefinition.setPrimaryKey(true);
                }
                attrDefinitionMap.put(columnDefinition.getColumnName(), columnDefinition);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        tableDefinition.setColumnMap(attrDefinitionMap);
    }

    @Override
    public List<String> resolveDBInfo(DatabaseMetaData metaData) {
        try {
            List<String> list = new ArrayList<>();
            ResultSet set = metaData.getTables(null, null, null, new String[] { "TABLE" });
            while (set.next()){
                list.add(set.getString("TABLE_NAME"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private String getPkName(String tableName, DatabaseMetaData metaData){
        String pkName = "";
        try{
            // pkSet.getString()中的参数名称参见com.mysql.cj.jdbc.DatabaseMetaDate.getPrimaryKeysFields()
            ResultSet pkSet = metaData.getPrimaryKeys(null, null, tableName);
            while (pkSet.next()) {
                pkName = pkSet.getString("COLUMN_NAME");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pkName;
    }


}
