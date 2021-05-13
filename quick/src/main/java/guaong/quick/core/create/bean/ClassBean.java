package guaong.quick.core.create.bean;

import guaong.quick.core.resovle.bean.TableDefinition;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 根据TableDefinition的内容生成类信息
 *
 * @author guaong
 */
@Data
public class ClassBean {

    /**
     * 类名
     */
    private String className;

    /**
     * package
     */
    private String packageUrl;

    /**
     * 类上面所用到的包的import
     * 不建议包含方法和属性所用到的包的import
     */
    private Set<String> importSet;

    /**
     * implements
     */
    private List<String> implementList;

    /**
     * extends
     */
    private String extend;

    /**
     * 类上的注解
     */
    private List<String> annotationList;

    /**
     * 方法列表
     */
    private List<MethodBean> methodList;

    /**
     * 属性列表
     */
    private List<FieldBean> fieldList;

    /**
     * class的类型
     *
     * @see guaong.quick.core.create.creator.JavaCreator#TYPE_CLASS
     * @see guaong.quick.core.create.creator.JavaCreator#TYPE_INTERFACE
     * @see guaong.quick.core.create.creator.JavaCreator#TYPE_ENUM
     * @see guaong.quick.core.create.creator.JavaCreator#TYPE_ANNOTATION
     */
    private int classType;

    /**
     * 对应TableName
     * @see TableDefinition#getTableName()
     */
    private String defaultName;

}
