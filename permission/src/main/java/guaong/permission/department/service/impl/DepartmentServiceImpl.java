package guaong.permission.department.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import guaong.permission.department.service.IDepartmentService;
import org.springframework.stereotype.Service;
import guaong.permission.department.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import guaong.permission.department.entity.Department;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

}