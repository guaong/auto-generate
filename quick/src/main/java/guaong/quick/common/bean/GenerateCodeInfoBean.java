package guaong.quick.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GenerateCodeInfoBean implements Serializable {

    private List<String> tableNames;

    private String exportUrl;

    private String packageUrl;

}
