package top.wade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import top.wade.entity.User;
import top.wade.exception.NotFoundException;
import top.wade.mapper.UserMapper;
import top.wade.service.UserService;
import top.wade.util.HashUtils;
import top.wade.util.JwtUtils;

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

    @Override
    public Boolean changeAccount(User user, String jwt) {
        String username = JwtUtils.getTokenBody(jwt).getSubject();
        user.setPassword(HashUtils.getBC(user.getPassword()));
        if (userMapper.updateUserByUsername(username, user) != 1) {
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    @Override
    public User findUserById(Long id) {
        User user = userMapper.findById(id);
        if (user == null) {
            throw new NotFoundException("用户不存在");
        }
        return user;
    }
}
