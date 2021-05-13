package guaong.quick.general.create;

import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.util.StringUtil;

public class MapperXmlCreator {


    public StringBuilder create(TableConfigInfo tableConfigInfo) {
        String namespace = tableConfigInfo.getPackageUrl() + ".mapper."
                + StringUtil.convert2JClassName(tableConfigInfo.getTableName()) + "Mapper";
        StringBuilder str = new StringBuilder();
        str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\"").append(namespace).append("\">");
        str.append("\n\n</mapper>");
        return str;
    }

}
