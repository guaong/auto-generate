package guaong.quick.core.create.translate.translator;

import guaong.quick.core.create.bean.ClassBean;
import guaong.quick.core.resovle.bean.TableDefinition;

/**
 * java translator
 *
 * @author guaong
 */
public interface JavaTranslator {

    ClassBean translate(TableDefinition tableDefinition);

//    String convert2CamelCase(String name);

}
