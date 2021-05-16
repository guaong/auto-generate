package guaong.quick;

import guaong.quick.core.resovle.SingleDBConfig;
import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.resovle.resolver.MySQLResolver;
import guaong.quick.core.general.AutoGeneration;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuickApplicationTests {

    @Autowired
    MySQLResolver resolver;

    @Autowired
    private SingleDBConfig config;

    @Test
    void contextLoads() {
        // D:/Develop/Project/AutoGenerate/permission/src/main/java/guaong/permission
        // TableDefinition beanDefinition = resolver.resolve("user", "D:/Develop/Project/AutoGenerate/permission/src/main/java/guaong/permission");
        TableConfigInfo tableConfigInfo = new TableConfigInfo();
        tableConfigInfo.setExportUrl("D:/test/role");
        tableConfigInfo.setPackageUrl("guaong.permission.role");
        tableConfigInfo.setDbUrl(config.getUrl());
        tableConfigInfo.setDriver(config.getDriver());
        tableConfigInfo.setPassword(config.getPassword());
        tableConfigInfo.setUsername(config.getUsername());
        tableConfigInfo.setTableName("role");
//        TableDefinition beanDefinition = resolver.resolveTable(tableConfigInfo);
//        AutoGeneration proxy = new AutoGeneration(beanDefinition);
//        proxy.todo();
        resolver.resolve().forEach(System.out::println);

    }


}
