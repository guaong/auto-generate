package guaong.permission.role_permission.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import guaong.permission.role_permission.service.IRolePermissionService;
import guaong.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import guaong.permission.role_permission.entity.RolePermission;

@Slf4j
@RestController
@RequestMapping("/role_permission")
public class RolePermissionController {

	@Autowired
	private IRolePermissionService role_permissionService;

	@GetMapping(value = "/list")
	public Result<?> list( RolePermission role_permission, 
				@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, 
				@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, 
				HttpServletRequest req) {
		QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
		Page<RolePermission> page = new Page<RolePermission>(pageNo, pageSize);
		IPage<RolePermission> pageList = role_permissionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RolePermission role_permission) {
		role_permissionService.save(role_permission);
		return Result.OK("添加成功！");
	}

	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		role_permissionService.removeById(id);
		return Result.OK("删除成功!");
	}

	@PutMapping(value = "/update")
	public Result<?> edit(@RequestBody RolePermission role_permission) {
		role_permissionService.updateById(role_permission);
		return Result.OK("更新成功！");
	}

}