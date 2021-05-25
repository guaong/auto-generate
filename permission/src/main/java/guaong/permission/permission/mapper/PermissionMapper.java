package guaong.permission.permission.mapper;

import guaong.permission.permission.bean.PermissionTree;
import guaong.permission.permission.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionTree> getPermissionList();

}