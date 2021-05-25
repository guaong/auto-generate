package guaong.permission.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import guaong.permission.role.entity.Role;
import org.springframework.stereotype.Service;
import guaong.permission.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import guaong.permission.role.mapper.RoleMapper;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

}