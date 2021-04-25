package guaong.quick.auto.core.translate.translator;

import guaong.quick.auto.core.resovle.bean.ConfigInfo;
import guaong.quick.auto.core.create.bean.ClassBean;
import guaong.quick.auto.core.resovle.bean.TableDefinition;

/**
 * xml translator
 *
 * @author guaong
 */
public interface XMLTranslator {

    ClassBean translate(TableDefinition tableDefinition, ConfigInfo configInfo);

    String convert2CamelCase(String name);

}
