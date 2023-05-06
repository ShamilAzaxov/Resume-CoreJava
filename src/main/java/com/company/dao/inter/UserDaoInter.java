package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.List;

public interface UserDaoInter {

public List<User> getAllUsers();

public User getById(int id);

    public boolean updateUser(User user);

    public boolean addUser(User u);

    public boolean deleteUser(int id);
}
