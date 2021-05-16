package guaong.quick.core.general.create;

import guaong.quick.core.create.bean.ClassBean;
import guaong.quick.core.create.bean.FieldBean;
import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.resovle.bean.GeneralJavaClass;
import guaong.quick.core.create.creator.ClassCreator;
import guaong.quick.core.create.creator.JavaCreator;
import guaong.quick.core.resovle.bean.ColumnDefinition;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.util.StringUtil;

import java.util.*;

/**
 * @author guaong
 * 创建entity
 */
public class EntityCreator extends ClassCreator {

    private final String[] classImports = {
            "com.baomidou.mybatisplus.annotation.*",
            "java.io.Serializable",
            "lombok.Data", "lombok.EqualsAndHashCode", "lombok.experimental.Accessors"};

    private final String[] classAnnotations = {
            "@Data", "@EqualsAndHashCode(callSuper = false)", "@Accessors(chain = true)"};

    private final String[] classImplement = {"Serializable"};

    private final String[] attrImports = {
            "com.fasterxml.jackson.annotation.JsonFormat",
            "org.springframework.format.annotation.DateTimeFormat"};


    public EntityCreator(TableDefinition tableDefinition) {
        super(tableDefinition);
    }

    @Override
    public ClassBean translate(TableDefinition tableDefinition) {
        ClassBean classBean = super.translate(tableDefinition);
        TableConfigInfo tableConfigInfo = tableDefinition.getConfigInfo();
        classBean.setPackageUrl(tableConfigInfo.getPackageUrl() + ".entity");
        classBean.setClassName(StringUtil.convert2JClassName(tableDefinition.getTableName()));
        classBean.setClassType(JavaCreator.TYPE_CLASS);

        // 类的注解，继承，实现所需要的import
        Set<String> classImportSet = new HashSet<>(Arrays.asList(classImports));
        classBean.setImportSet(classImportSet);
        // 类上的注解
        List<String> classAnnotationList = new ArrayList<>(Arrays.asList(classAnnotations));
        classAnnotationList.add("@TableName(\"" + tableDefinition.getTableName() + "\")");
        classBean.setAnnotationList(classAnnotationList);
        // 类实现
        List<String> classImplementList = new ArrayList<>(Arrays.asList(classImplement));
        classBean.setImplementList(classImplementList);

        // 属性
        Map<String, ColumnDefinition> attrMap = tableDefinition.getColumnMap();
        if (attrMap != null){
            List<FieldBean> fieldBeanList = new ArrayList<>();
            attrMap.keySet().forEach(s -> {
                ColumnDefinition columnDefinition = attrMap.get(s);
                FieldBean fieldBean = new FieldBean();
                // 属性名
                fieldBean.setName(columnDefinition.getColumnName());
                // 属性import
                Set<String> attrImportSet = new HashSet<>(Arrays.asList(attrImports));
                fieldBean.setImportSet(attrImportSet);
                // 属性注解
                List<String> attrAnnotationList = new ArrayList<>();
                if (columnDefinition.getType().equals(GeneralJavaClass.DATE)){
                    attrAnnotationList.add("@JsonFormat(timezone = \"GMT+8\", pattern = \"yyyy-MM-dd HH:mm:ss\")");
                    attrAnnotationList.add("@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
                }
                if(columnDefinition.isPrimaryKey()){
                    attrAnnotationList.add("@TableId(type = IdType.AUTO)");
                }
                fieldBean.setAnnotationList(attrAnnotationList);
                // 属性的类
                fieldBean.setClazz(columnDefinition.getType());
                fieldBean.setAccessModifier("private");
                fieldBeanList.add(fieldBean);
            });
            classBean.setFieldList(fieldBeanList);
        }
        return classBean;
    }


    @Override
    public StringBuilder addMethods(ClassBean classBean) {
        return null;
    }

}
