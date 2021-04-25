package guaong.quick.auto.general.create;

import guaong.quick.auto.core.resovle.bean.ConfigInfo;
import guaong.quick.auto.core.util.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperXmlCreator {


    public StringBuilder create(ConfigInfo configInfo) {
        String namespace = configInfo.getPackageUrl() + ".mapper."
                + StringUtil.convert2JClassName(configInfo.getTableName()) + "Mapper";
        StringBuilder str = new StringBuilder();
        str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\"").append(namespace).append("\">");
        str.append("\n\n</mapper>");
        return str;
    }

}
