package com.taiji.dao.manage.impl;

import com.taiji.dao.manage.UserDao;
import com.taiji.entity.manage.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Sleeb on 2017/4/25.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public UserEntity getUser(Long userId) {
        return hibernateTemplate.get(UserEntity.class,userId);
    }
}
