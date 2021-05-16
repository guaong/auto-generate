package guaong.quick.common.controller;

import guaong.quick.common.bean.GenerateCodeInfoBean;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.resovle.bean.TableInfo;
import guaong.quick.core.resovle.resolver.MySQLResolver;
import guaong.quick.core.general.AutoGeneration;
import guaong.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/quick")
public class QuickController {

    @Autowired
    MySQLResolver resolver;


//    /**
//     * 代码生成
//     * @param exportUrl 导出路径
//     * @param packageUrl
//     * @param tableName
//     * @return
//     */
//    @PostMapping("/generate")
//    public Result<?> generate(@RequestParam("exportUrl") String exportUrl,
//                              @RequestParam("packageUrl") String packageUrl,
//                              @RequestParam("tableName") String tableName){
//        TableDefinition beanDefinition = resolver.resolveTable(tableName, exportUrl, packageUrl);
//        AutoGeneration proxy = new AutoGeneration(beanDefinition);
//        proxy.todo();
//        return Result.OK("生成成功");
//    }


    /**
     * 批量代码生成
     * @return
     */
    @PostMapping("/batchGenerate")
    public Result<?> batchGenerate(@RequestBody GenerateCodeInfoBean generateCodeInfoBean){
        List<String> tableNames = generateCodeInfoBean.getTableNames();
        String exportUrl = generateCodeInfoBean.getExportUrl();
        String packageUrl = generateCodeInfoBean.getPackageUrl();
        if (tableNames == null || tableNames.size() == 0) return Result.ERROR("请选择表名!");
        for (String tableName : tableNames){
            TableDefinition beanDefinition = resolver.resolveTable(tableName, exportUrl, packageUrl);
            AutoGeneration proxy = new AutoGeneration(beanDefinition);
            proxy.todo();
        }
        return Result.OK("批量生成成功");
    }


    /**
     * 获取数据库表
     */
    @GetMapping("/getTables")
    public Result<?> getTables(@RequestParam(value = "search", defaultValue = "") String search){
        List<TableInfo> list = resolver.resolve();
        if (search != null && !"".equals(search)){
            return Result.OK(list.stream().filter(tableInfo -> tableInfo.getTableName().contains(search)));
        }
        return Result.OK(list);
    }


}
