package guaong.quick.core.resovle;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 单数据源配置信息
 *
 * @author guaong
 * @version 1.0
 */
@Component
@Data
public class SingleDBConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

}
