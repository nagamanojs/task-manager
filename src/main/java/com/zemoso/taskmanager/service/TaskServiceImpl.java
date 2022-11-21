package com.zemoso.taskmanager.service;

import com.zemoso.taskmanager.dao.TaskDAO;
import com.zemoso.taskmanager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskDAO taskDAO;
    @Override
    public void createTask(Task task) {

    }

    @Override
    public void updateTask(int id, Task task) {

    }

    @Override
    public void deleteTask(int id) {

    }

    @Override
    public void markDone(int id) {
         Optional<Task> task=taskDAO.findById(id);
         Task tempTask = null;
         if(task.isPresent()){
             tempTask=task.get();
         }
         tempTask.setCompleted(true);
         taskDAO.save(tempTask);



    }

    @Override
    public void markUnDone(int id) {
        Optional<Task> task=taskDAO.findById(id);
        Task tempTask = null;
        if(task.isPresent()){
            tempTask=task.get();
        }
        tempTask.setCompleted(false);
        taskDAO.save(tempTask);



    }




}
