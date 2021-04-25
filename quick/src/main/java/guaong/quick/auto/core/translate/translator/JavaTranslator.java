package guaong.quick.auto.core.translate.translator;

import guaong.quick.auto.core.create.bean.ClassBean;
import guaong.quick.auto.core.resovle.bean.TableDefinition;

/**
 * java translator
 *
 * @author guaong
 */
public interface JavaTranslator {

    ClassBean translate(TableDefinition tableDefinition);

//    String convert2CamelCase(String name);

}
