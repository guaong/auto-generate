package guaong.quick.auto.core.create.creator;

import guaong.quick.auto.core.create.bean.FieldBean;
import guaong.quick.auto.core.resovle.bean.TableDefinition;

import java.util.List;

/**
 * java class creator
 *
 * @author guaong
 */
public abstract class ClassCreator extends JavaCreator {


    public ClassCreator(TableDefinition tableDefinition) {
        super(tableDefinition);
    }


    @Override
    public StringBuilder addField(List<FieldBean> fields) {
        StringBuilder str = new StringBuilder();
        if (fields != null) {
            for (FieldBean field : fields) {
                // 注解
                List<String> list = field.getAnnotationList();
                if (list != null) {
                    for (String s : list) {
                        str.append(this.indentation(1))
                                .append(s)
                                .append(this.newlines(1));
                    }
                }
                str.append(this.indentation(1))
                        .append(field.getAccessModifier())
                        .append(" ")
                        .append(field.getClazz())
                        .append(" ")
                        .append(field.getName())
                        .append(";")
                        .append(this.newlines(2));
            }
        }
        return str;
    }

}
