package guaong.permission.role_permission.entity;

import lombok.experimental.Accessors;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("role_permission")
public class RolePermission implements Serializable {

	@TableId(type = IdType.AUTO)
	private Integer id;

	private Integer role_id;

	private Integer permission_id;

}