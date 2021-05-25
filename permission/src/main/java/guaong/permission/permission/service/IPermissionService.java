package guaong.permission.permission.service;

import guaong.permission.permission.bean.PermissionTree;
import guaong.permission.permission.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IPermissionService extends IService<Permission> {

    List<PermissionTree> getPermissionList();


}