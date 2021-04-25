package guaong.quick.auto.core.create.bean;

import lombok.Data;

import java.util.Set;

/**
 * 根据TableDefinition的内容生成类中的方法的信息
 *
 * @author guaong
 * @see ClassBean 包含在其中
 */
@Data
public class MethodBean {

    Set<String> importSet;

}
