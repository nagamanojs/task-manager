package com.zemoso.taskmanager.dao;

import com.zemoso.taskmanager.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<Users,String> {

}
