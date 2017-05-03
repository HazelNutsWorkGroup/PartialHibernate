package com.taiji.dao.manage;

import com.taiji.entity.manage.UserEntity;

/**
 * Created by Sleeb on 2017/4/25.
 */
public interface UserDao {

    UserEntity getUser(Long userId);

}
