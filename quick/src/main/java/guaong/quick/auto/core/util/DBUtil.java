package guaong.quick.auto.core.util;

import guaong.quick.auto.core.resovle.bean.GeneralJavaClass;

/**
 * @author guaong
 */
public class DBUtil {

    /**
     * mysql类型转java类型
     * 具体类型参考com.mysql.cj.MysqlType
     */
    public static String Mysql2Java(String type) {
        if ("TINYINT".equalsIgnoreCase(type)
                || "TINYINT_UNSIGNED".equalsIgnoreCase(type)
                || "SMALLINT".equalsIgnoreCase(type)
                || "SMALLINT_UNSIGNED".equalsIgnoreCase(type)
                || "INT".equalsIgnoreCase(type)
                || "INT_UNSIGNED".equalsIgnoreCase(type)
                || "MEDIUMINT".equalsIgnoreCase(type)
                || "MEDIUMINT_UNSIGNED".equalsIgnoreCase(type)) {

            return GeneralJavaClass.INTEGER;

        } else if ("VARCHAR".equalsIgnoreCase(type)
                || "JSON".equalsIgnoreCase(type)
                || "ENUM".equalsIgnoreCase(type)
                || "SET".equalsIgnoreCase(type)
                || "TINYTEXT".equalsIgnoreCase(type)
                || "MEDIUMTEXT".equalsIgnoreCase(type)
                || "LONGTEXT".equalsIgnoreCase(type)
                || "TEXT".equalsIgnoreCase(type)
                || "CHAR".equalsIgnoreCase(type)) {

            return GeneralJavaClass.STRING;

        } else if ("DATE".equalsIgnoreCase(type)
                || "TIME".equalsIgnoreCase(type)
                || "DATETIME".equalsIgnoreCase(type)
                || "TIMESTAMP".equalsIgnoreCase(type)
                || "YEAR".equalsIgnoreCase(type)) {

            return GeneralJavaClass.DATE;

        } else if ("FLOAT".equalsIgnoreCase(type)
                || "FLOAT_UNSIGNED".equalsIgnoreCase(type)) {

            return GeneralJavaClass.FLOAT;

        } else if ("DECIMAL".equalsIgnoreCase(type)
                || "DECIMAL_UNSIGNED".equalsIgnoreCase(type)) {

            return GeneralJavaClass.BIG_DECIMAL;

        } else if ("DOUBLE".equalsIgnoreCase(type)
                || "DOUBLE_UNSIGNED".equalsIgnoreCase(type)) {

            return GeneralJavaClass.DOUBLE;

        } else if ("BIGINT".equalsIgnoreCase(type)
                || "BIGINT_UNSIGNED".equalsIgnoreCase(type)) {

            return GeneralJavaClass.LONG;

        } else if ("BOOLEAN".equalsIgnoreCase(type)
                || "BIT".equalsIgnoreCase(type)) {

            return GeneralJavaClass.BOOLEAN;

        } else {
            return null;
        }
    }

}
