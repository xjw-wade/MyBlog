package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.wade.entity.User;
import top.wade.mapper.UserMapper;
import top.wade.service.UserService;
import top.wade.util.HashUtils;

/**
 * @Author xjw
 * @Date 2023/7/7 21:05
 * @Description: 用户业务层接口实现类
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!HashUtils.matchBC(password, user.getPassword())) {
            throw new UsernameNotFoundException("密码错误");
        }
        return user;
    }
}
