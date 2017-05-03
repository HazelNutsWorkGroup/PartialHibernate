package com.taiji.service.manage.impl;

import com.taiji.dao.manage.UserDao;
import com.taiji.domain.manage.UserDomain;
import com.taiji.entity.manage.UserEntity;
import com.taiji.service.manage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sleeb on 2017/4/25.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public UserDomain getUser(Long userId) {
        UserEntity userEntity = userDao.getUser(userId);

        UserDomain userDomain =convertEntityToDomain(userEntity);
        return userDomain;
    }

    @Override
    public UserDomain convertEntityToDomain(UserEntity userEntity) {
        if(userEntity==null)
            return null;

        UserDomain userDomain=new UserDomain();
        userDomain.setUserId(userEntity.getUserId());
        userDomain.setUserName(userEntity.getUserName());
        userDomain.setUserCode(userEntity.getUserCode());
        userDomain.setPassword(userEntity.getPassword());

        return userDomain;
    }

    @Override
    public UserEntity convertDomainToEntity(UserDomain userDomain) {
        if(userDomain==null)
            return null;

        UserEntity userEntity=new UserEntity();
        userEntity.setUserId(userDomain.getUserId());
        userEntity.setUserName(userDomain.getUserName());
        userEntity.setUserCode(userDomain.getUserCode());
        userEntity.setPassword(userDomain.getPassword());

        return userEntity;
    }
}
