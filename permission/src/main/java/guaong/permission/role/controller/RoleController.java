package guaong.permission.role.controller;

import guaong.permission.role.entity.Role;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import guaong.common.Result;
import guaong.permission.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@GetMapping(value = "/list")
	public Result<?> list( Role role, 
				@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, 
				@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, 
				HttpServletRequest req) {
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		Page<Role> page = new Page<Role>(pageNo, pageSize);
		IPage<Role> pageList = roleService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Role role) {
		roleService.save(role);
		return Result.OK("添加成功！");
	}

	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		roleService.removeById(id);
		return Result.OK("删除成功!");
	}

	@PutMapping(value = "/update")
	public Result<?> edit(@RequestBody Role role) {
		roleService.updateById(role);
		return Result.OK("更新成功！");
	}

}