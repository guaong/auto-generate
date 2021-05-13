package guaong.quick.core.util;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * resource文件夹中的文件内容解析
 */
@Deprecated
public class ResourceUtil {

    /**
     * 解析配置文件application.yml
     */
    public static String getProperty(String key){
        Properties pros = new Properties();
        InputStreamReader isr = new InputStreamReader(
                PropertiesUtil.class.getResourceAsStream("/application.yml")
                , StandardCharsets.UTF_8);
        try {
            pros.load(isr);
            isr.close();
            return pros.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
