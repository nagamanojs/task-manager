package com.zemoso.taskmanager.service;

import com.zemoso.taskmanager.entity.Task;
import com.zemoso.taskmanager.entity.Users;

import java.util.List;

public interface TaskService {
    void createTask(Task task);

    void updateTask(int id, Task task);

    void deleteTask(int  id);
    void markDone(int id);
    void markUnDone(int id);


}
