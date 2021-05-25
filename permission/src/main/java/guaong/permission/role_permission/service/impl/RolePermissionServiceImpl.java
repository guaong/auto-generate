package guaong.permission.role_permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import guaong.permission.role_permission.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import guaong.permission.role_permission.mapper.RolePermissionMapper;
import guaong.permission.role_permission.entity.RolePermission;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

	@Autowired
	private RolePermissionMapper role_permissionMapper;

}