package guaong.permission.department.controller;

import guaong.permission.department.service.IDepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import guaong.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import guaong.permission.department.entity.Department;
import com.baomidou.mybatisplus.core.metadata.IPage;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	@GetMapping(value = "/list")
	public Result<?> list( Department department, 
				@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, 
				@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, 
				HttpServletRequest req) {
		QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
		Page<Department> page = new Page<Department>(pageNo, pageSize);
		IPage<Department> pageList = departmentService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Department department) {
		departmentService.save(department);
		return Result.OK("添加成功！");
	}

	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		departmentService.removeById(id);
		return Result.OK("删除成功!");
	}

	@PutMapping(value = "/update")
	public Result<?> edit(@RequestBody Department department) {
		departmentService.updateById(department);
		return Result.OK("更新成功！");
	}

}