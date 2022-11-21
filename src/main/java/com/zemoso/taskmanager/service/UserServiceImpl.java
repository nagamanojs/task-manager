package com.zemoso.taskmanager.service;

import com.zemoso.taskmanager.dao.UserDAO;
import com.zemoso.taskmanager.entity.Task;
import com.zemoso.taskmanager.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
     UserDAO userDAO;

    @Override
    public Users createUser(Users user) {
        return userDAO.save(user);
    }

    @Override
    public Users changeRoleToAdmin(Users user) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public void deleteUser(Long id) {


    }

    @Override
    public List<Task> findAllTasks(String username) {
        Optional<Users> user=userDAO.findById(username);
        Users tempUser = null;
        if(user.isPresent()){
            tempUser=user.get();
        }
        System.out.println(tempUser.getTasksOwned());
         return tempUser.getTasksOwned();


    }

    @Override
    public Users  getUserByUsername(String username) {
        Optional<Users> user=userDAO.findById(username);
        System.out.println(user);
        if(user.isPresent())
            return user.get();
//        }
        return null;
    }
}
