package guaong.quick.core.create.translate.translator;

import guaong.quick.core.resovle.bean.TableConfigInfo;
import guaong.quick.core.create.bean.ClassBean;
import guaong.quick.core.resovle.bean.TableDefinition;

/**
 * xml translator
 *
 * @author guaong
 */
public interface XMLTranslator {

    ClassBean translate(TableDefinition tableDefinition, TableConfigInfo tableConfigInfo);

    String convert2CamelCase(String name);

}
