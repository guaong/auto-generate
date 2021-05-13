package guaong.quick.controller;

import guaong.quick.core.resovle.SingleDBConfig;
import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.resovle.resolver.MySQLResolver;
import guaong.quick.general.AutoGeneration;
import guaong.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/quick")
public class QuickController {

    @Autowired
    MySQLResolver resolver;

    @Autowired
    private SingleDBConfig config;

    /**
     * 代码生成
     * @param exportUrl 导出路径
     * @param packageUrl
     * @param tableName
     * @return
     */
    @PostMapping("/generate")
    public Result<?> generate(String exportUrl, String packageUrl, String tableName){
        TableConfigInfo tableConfigInfo = new TableConfigInfo();
        tableConfigInfo.setExportUrl(exportUrl + "/" + tableName);
        tableConfigInfo.setPackageUrl(packageUrl + "/" + tableName);
        tableConfigInfo.setDbUrl(config.getUrl());
        tableConfigInfo.setDriver(config.getDriver());
        tableConfigInfo.setPassword(config.getPassword());
        tableConfigInfo.setUsername(config.getUsername());
        tableConfigInfo.setTableName(tableName);
        TableDefinition beanDefinition = resolver.resolveTable(tableConfigInfo);
        AutoGeneration proxy = new AutoGeneration(beanDefinition);
        proxy.todo();
        return Result.OK("生成成功");
    }


    /**
     * 批量代码生成
     * @return
     */
    @PostMapping("/batchGenerate")
    public Result<?> batchGenerate(){
        return Result.OK("批量生成成功");
    }


    /**
     * 获取数据库表
     */
    @CrossOrigin(originPatterns = "*")
    @GetMapping("/getTables")
    public Result<?> getTables(){
        return Result.OK(resolver.resolve());
    }


}
