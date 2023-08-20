package top.wade.service;

import top.wade.entity.User;


/**
 * @Author xjw
 * @Date 2023/7/7 21:05
 * @Description:
 */
public interface UserService {
    User findUserByUsernameAndPassword(String username, String password);

    Boolean changeAccount(User user, String jwt);

    User findUserById(Long id);
}
