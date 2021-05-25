package guaong.permission.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import guaong.permission.user.entity.User;
import guaong.permission.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import guaong.permission.user.mapper.UserMapper;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private UserMapper userMapper;

}