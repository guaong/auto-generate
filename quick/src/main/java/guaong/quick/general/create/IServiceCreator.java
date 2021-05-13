package guaong.quick.general.create;

import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.create.bean.ClassBean;
import guaong.quick.core.create.bean.FieldBean;
import guaong.quick.core.create.creator.InterfaceCreator;
import guaong.quick.core.create.creator.JavaCreator;
import guaong.quick.core.resovle.bean.TableDefinition;
import guaong.quick.core.util.StringUtil;

import java.util.*;

public class IServiceCreator extends InterfaceCreator {

    public IServiceCreator(TableDefinition tableDefinition) {
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
        TableConfigInfo tableConfigInfo = tableDefinition.getConfigInfo();
        String entityName = StringUtil.convert2JClassName(tableDefinition.getTableName());

        classBean.setPackageUrl(tableConfigInfo.getPackageUrl() + ".service");
        classBean.setClassName("I" + entityName + "Service");
        classBean.setClassType(JavaCreator.TYPE_INTERFACE);

        // 类的注解，继承，实现所需要的import
        Set<String> classImportSet = new HashSet<>();
        classImportSet.add("com.baomidou.mybatisplus.extension.service.IService");
        classImportSet.add(tableConfigInfo.getPackageUrl() + ".entity." + entityName);
        classBean.setImportSet(classImportSet);

        // 类继承
        String classExtends = "IService<" + entityName + ">";
        classBean.setExtend(classExtends);

        return classBean;
    }
}
