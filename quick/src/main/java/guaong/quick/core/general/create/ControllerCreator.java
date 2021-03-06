package guaong.quick.core.general.create;

import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.create.bean.ClassBean;
import guaong.quick.core.create.bean.FieldBean;
import guaong.quick.core.create.bean.MethodBean;
import guaong.quick.core.create.creator.ClassCreator;
import guaong.quick.core.create.creator.JavaCreator;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.util.StringUtil;

import java.util.*;

public class ControllerCreator  extends ClassCreator {

    private final String[] classImports = {
            "lombok.extern.slf4j.Slf4j",
            "guaong.common.Result",
            "org.springframework.web.bind.annotation.*"};

    private final String[] classAnnotations = {"@Slf4j","@RestController"};

    private final String[] attrImports = {
            "org.springframework.beans.factory.annotation.Autowired"};


    public ControllerCreator(TableDefinition tableDefinition) {
        super(tableDefinition);
    }


    @Override
    public StringBuilder addMethods(ClassBean classBean) {
        StringBuilder str = new StringBuilder();
        String defaultName = classBean.getDefaultName();
        String entityName = StringUtil.convert2JClassName(defaultName);
        String serviceName = defaultName + "Service";
        // list()
        str.append(this.indentation(1)).append("@GetMapping(value = \"/list\")")
                .append(this.newlines(1));
        str.append(this.indentation(1)).append("public Result<?> list( ")
                .append(entityName).append(" ")
                .append(defaultName)
                .append(", \n\t\t\t\t@RequestParam(name = \"pageNo\", defaultValue = \"1\") Integer pageNo, \n\t\t\t\t@RequestParam(name = \"pageSize\", defaultValue = \"10\") Integer pageSize, \n\t\t\t\tHttpServletRequest req) {")
                .append(this.newlines(1));
//        str.append(this.indentation(2)).append("QueryWrapper<").append(entityName)
//                .append("> queryWrapper = QueryGenerator.initQueryWrapper(")
//                .append(defaultName).append(", req.getParameterMap());").append(this.newlines(1));
        str.append(this.indentation(2)).append("QueryWrapper<").append(entityName)
                .append("> queryWrapper = new QueryWrapper<>();")
                .append(this.newlines(1));
        str.append(this.indentation(2)).append("Page<").append(entityName).append("> page = new Page<")
                .append(entityName).append(">(pageNo, pageSize);").append(this.newlines(1));
        str.append(this.indentation(2)).append("IPage<").append(entityName).append("> pageList = ")
                .append(serviceName).append(".page(page, queryWrapper);").append(this.newlines(1));
        str.append(this.indentation(2)).append("return Result.OK(pageList);").append(newlines(1));
        str.append(this.indentation(1)).append("}");

        str.append(this.newlines(2));

        // add()
        str.append(this.indentation(1)).append("@PostMapping(value = \"/add\")").append(this.newlines(1));
        str.append(this.indentation(1)).append("public Result<?> add(@RequestBody ")
                .append(entityName).append(" ").append(defaultName).append(") {")
                .append(this.newlines(1));
        str.append(this.indentation(2)).append(serviceName).append(".save(")
                .append(defaultName).append(");").append(this.newlines(1));
        str.append(this.indentation(2)).append("return Result.OK(\"???????????????\");").append(this.newlines(1));
        str.append(this.indentation(1)).append("}");

        str.append(this.newlines(2));

        // delete()
        str.append(this.indentation(1)).append("@DeleteMapping(value = \"/delete\")").append(this.newlines(1));
        str.append(this.indentation(1)).append("public Result<?> delete(@RequestParam(name = \"id\", required = true) String id) {")
                .append(this.newlines(1));
        str.append(this.indentation(2)).append(serviceName).append(".removeById(id);").append(this.newlines(1));
        str.append(this.indentation(2)).append("return Result.OK(\"????????????!\");").append(this.newlines(1));
        str.append(this.indentation(1)).append("}");

        str.append(this.newlines(2));

        // update()
        str.append(this.indentation(1)).append("@PutMapping(value = \"/update\")").append(this.newlines(1));
        str.append(this.indentation(1)).append("public Result<?> edit(@RequestBody ")
                .append(entityName).append(" ").append(defaultName).append(") {")
                .append(this.newlines(1));
        str.append(this.indentation(2)).append(serviceName).append(".updateById(").append(defaultName)
                .append(");").append(this.newlines(1));
        str.append(this.indentation(2)).append("return Result.OK(\"???????????????\");").append(this.newlines(1));
        str.append(this.indentation(1)).append("}");

        str.append(this.newlines(2));

        return str;
    }

    @Override
    public ClassBean translate(TableDefinition tableDefinition) {
        ClassBean classBean = super.translate(tableDefinition);
        TableConfigInfo tableConfigInfo = tableDefinition.getConfigInfo();
        String entityName = StringUtil.convert2JClassName(tableDefinition.getTableName());
        String controllerName = StringUtil.convert2JClassName(tableDefinition.getTableName()) + "Controller";
        String serviceName = "I" + StringUtil.convert2JClassName(tableDefinition.getTableName()) + "Service";

        classBean.setPackageUrl(tableConfigInfo.getPackageUrl() + ".controller");
        classBean.setClassName(controllerName);
        classBean.setClassType(JavaCreator.TYPE_CLASS);

        // ??????????????????????????????????????????import
        Set<String> classImportSet = new HashSet<>(Arrays.asList(classImports));
        classBean.setImportSet(classImportSet);
        // ???????????????
        List<String> classAnnotationList = new ArrayList<>(Arrays.asList(classAnnotations));
        classAnnotationList.add("@RequestMapping(\"/" + this.clipPackageUrl(tableConfigInfo.getPackageUrl()) + "\")");
        classBean.setAnnotationList(classAnnotationList);

        // ??????
        List<FieldBean> fieldBeanList = new ArrayList<>();
        FieldBean fieldBean = new FieldBean();
        // ?????????
        fieldBean.setName(tableDefinition.getTableName() + "Service");
        // ??????import
        Set<String> attrImportSet = new HashSet<>(Collections.singletonList(
                tableConfigInfo.getPackageUrl() + ".service." + serviceName));
        attrImportSet.addAll(Arrays.asList(attrImports));
        fieldBean.setImportSet(attrImportSet);
        // ????????????
        List<String> attrAnnotationList = new ArrayList<>(Collections.singletonList("@Autowired"));
        fieldBean.setAnnotationList(attrAnnotationList);
        // ????????????
        fieldBean.setClazz(serviceName);
        fieldBean.setAccessModifier("private");
        fieldBeanList.add(fieldBean);
        classBean.setFieldList(fieldBeanList);

        // ??????
        List<MethodBean> methodBeanList = new ArrayList<>();
        // list()
        MethodBean listBean = new MethodBean();
        String[] listImports = {"com.baomidou.mybatisplus.core.conditions.query.QueryWrapper",
                "com.baomidou.mybatisplus.core.metadata.IPage",
                "com.baomidou.mybatisplus.extension.plugins.pagination.Page",
                "javax.servlet.http.HttpServletRequest"};
        Set<String> listSet = new HashSet<>(Collections.singletonList(tableConfigInfo.getPackageUrl() + ".entity." + entityName));
        listSet.addAll(Arrays.asList(listImports));
        listBean.setImportSet(listSet);
        methodBeanList.add(listBean);
        // add()
        // delete()
        // update
        classBean.setMethodList(methodBeanList);
        return classBean;
    }
}
