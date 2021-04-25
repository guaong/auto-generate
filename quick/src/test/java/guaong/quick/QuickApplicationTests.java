package guaong.quick;

import guaong.quick.auto.core.resovle.bean.TableDefinition;
import guaong.quick.auto.core.resovle.resolver.MySQLTableResolver;
import guaong.quick.auto.core.resovle.resolver.TableResolver;
import guaong.quick.auto.general.AutoGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
class QuickApplicationTests {

    @Autowired
    MySQLTableResolver resolver;

    @Test
    void contextLoads() {
        TableDefinition beanDefinition = resolver.resolve("a", "D:/test");
        AutoGeneration proxy = new AutoGeneration(beanDefinition);
        proxy.todo();

    }


}
