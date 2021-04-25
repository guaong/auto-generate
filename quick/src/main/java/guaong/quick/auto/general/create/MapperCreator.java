package guaong.quick.auto.general.create;

import guaong.quick.auto.core.resovle.bean.ConfigInfo;
import guaong.quick.auto.core.create.bean.ClassBean;
import guaong.quick.auto.core.create.bean.FieldBean;
import guaong.quick.auto.core.create.creator.InterfaceCreator;
import guaong.quick.auto.core.create.creator.JavaCreator;
import guaong.quick.auto.core.resovle.bean.TableDefinition;
import guaong.quick.auto.core.util.StringUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapperCreator extends InterfaceCreator {

    public MapperCreator(TableDefinition tableDefinition) {
        super(tableDefinition);
    }

    @Override
    public StringBuilder addField(List<FieldBean> fields) {
        return null;
    }

    @Override
    public StringBuilder addMethods(ClassBean classBean) {
        return null;
    }

    @Override
    public ClassBean translate(TableDefinition tableDefinition) {
        ClassBean classBean = super.translate(tableDefinition);
        ConfigInfo configInfo = tableDefinition.getConfigInfo();
        String entityName = StringUtil.convert2JClassName(tableDefinition.getTableName());

        classBean.setPackageUrl(configInfo.getPackageUrl() + ".mapper");
        classBean.setClassName(entityName + "Mapper");
        classBean.setClassType(JavaCreator.TYPE_INTERFACE);

        // 类的注解，继承，实现所需要的import
        Set<String> classImportSet = new HashSet<>(
                Collections.singletonList("com.baomidou.mybatisplus.core.mapper.BaseMapper"));
        classImportSet.add(configInfo.getPackageUrl() + ".entity." + entityName);
        classBean.setImportSet(classImportSet);

        // 类继承
        String classExtends = "BaseMapper<" + entityName + ">";
        classBean.setExtend(classExtends);

        return classBean;
    }
}
