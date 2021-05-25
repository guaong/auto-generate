package guaong.permission.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import guaong.common.Result;
import lombok.extern.slf4j.Slf4j;
import guaong.permission.user.entity.User;
import javax.servlet.http.HttpServletRequest;
import guaong.permission.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

@Slf4j
@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;


	@GetMapping(value = "/list")
	public Result<?> list(User user,
						  @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
						  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
						  HttpServletRequest req) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		Page<User> page = new Page<User>(pageNo, pageSize);
		IPage<User> pageList = userService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody User user) {
		userService.save(user);
		return Result.OK("添加成功！");
	}

	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		userService.removeById(id);
		return Result.OK("删除成功!");
	}

	@PutMapping(value = "/update")
	public Result<?> edit(@RequestBody User user) {
		userService.updateById(user);
		return Result.OK("更新成功！");
	}

}