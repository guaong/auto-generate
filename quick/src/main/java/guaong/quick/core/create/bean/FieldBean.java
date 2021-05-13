package guaong.quick.core.create.bean;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 根据TableDefinition的内容生成类中的属性的信息
 *
 * @author guaong
 * @see ClassBean 包含在其中
 */
@Data
public class FieldBean {

    /**
     * 属性所属的class
     */
    private String clazz;

    /**
     * 属性名
     */
    private String name;

    /**
     * 访问修饰符
     */
    private String accessModifier;

    /**
     * 注解
     */
    private List<String> annotationList;

    /**
     * 属性所需要的import
     */
    private Set<String> importSet;

}

