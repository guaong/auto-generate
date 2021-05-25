package guaong.permission.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import guaong.permission.permission.bean.PermissionTree;
import guaong.permission.permission.entity.Permission;
import org.springframework.stereotype.Service;
import guaong.permission.permission.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import guaong.permission.permission.service.IPermissionService;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<PermissionTree> getPermissionList() {
		return permissionMapper.getPermissionList();
	}
}