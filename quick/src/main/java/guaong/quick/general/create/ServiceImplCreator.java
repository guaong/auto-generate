package guaong.quick.general.create;

import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.create.bean.ClassBean;
import guaong.quick.core.create.bean.FieldBean;
import guaong.quick.core.create.creator.ClassCreator;
import guaong.quick.core.create.creator.JavaCreator;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.util.StringUtil;

import java.util.*;

public class ServiceImplCreator extends ClassCreator {

    private final String[] classImports = {
            "org.springframework.stereotype.Service",
            "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl"};


    public ServiceImplCreator(TableDefinition tableDefinition) {
        super(tableDefinition);
    }

    @Override
    public StringBuilder addMethods(ClassBean classBean) {
        return null;
    }

    @Override
    public ClassBean translate(TableDefinition tableDefinition) {
        ClassBean classBean = super.translate(tableDefinition);
        TableConfigInfo tableConfigInfo = tableDefinition.getConfigInfo();
        String entityName = StringUtil.convert2JClassName(tableDefinition.getTableName());
        String mapperName = StringUtil.convert2JClassName(tableDefinition.getTableName()) + "Mapper";
        String serviceName = "I" + StringUtil.convert2JClassName(tableDefinition.getTableName()) + "Service";

        // 类名
        classBean.setClassName(entityName + "ServiceImpl");
        classBean.setPackageUrl(tableConfigInfo.getPackageUrl() + ".service.impl");
        classBean.setClassType(JavaCreator.TYPE_CLASS);

        // 类的注解，继承，实现所需要的import
        Set<String> classImportSet = new HashSet<>(Arrays.asList(classImports));
        classImportSet.add(tableConfigInfo.getPackageUrl() + ".entity." + entityName);
        classImportSet.add(tableConfigInfo.getPackageUrl() + ".mapper." + mapperName);
        classImportSet.add(tableConfigInfo.getPackageUrl() + ".service." + serviceName);
        classBean.setImportSet(classImportSet);
        // 类上的注解
        List<String> classAnnotationList = new ArrayList<>(Collections.singletonList("@Service"));
        classBean.setAnnotationList(classAnnotationList);
        // 类实现
        List<String> classImplementList = new ArrayList<>(Collections.singletonList(serviceName));
        classBean.setImplementList(classImplementList);
        // 类继承
        String classExtends = "ServiceImpl<" + mapperName + ", " + entityName + ">";
        classBean.setExtend(classExtends);

        // 属性
        List<FieldBean> fieldBeanList = new ArrayList<>();
        FieldBean fieldBean = new FieldBean();
        // 属性名
        fieldBean.setName(tableDefinition.getTableName() + "Mapper");
        // 属性import
        Set<String> attrImportSet = new HashSet<>(
                Collections.singletonList("org.springframework.beans.factory.annotation.Autowired"));
        fieldBean.setImportSet(attrImportSet);
        // 属性注解
        List<String> attrAnnotationList = new ArrayList<>(Collections.singletonList("@Autowired"));
        fieldBean.setAnnotationList(attrAnnotationList);
        // 属性的类
        fieldBean.setClazz(mapperName);
        fieldBean.setAccessModifier("private");
        fieldBeanList.add(fieldBean);
        classBean.setFieldList(fieldBeanList);

        return classBean;
    }
}
