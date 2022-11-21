package com.zemoso.taskmanager.controller;

import com.zemoso.taskmanager.entity.Task;
import com.zemoso.taskmanager.service.TaskService;
import com.zemoso.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;

    @RequestMapping("home")
    public String getAllTasks(Model theModel, Principal principal){
        String username= principal.getName();
        List<Task> tasks =userService.findAllTasks(username);
        System.out.println(tasks);
        username = username.substring(0, 1).toUpperCase() + username.substring(1, username.length());
        theModel.addAttribute("username",username);
        theModel.addAttribute("allTasks",tasks);
        return "home";
    }
    @GetMapping("task/mark/{taskId}")
    public String markTaskAsDone(@PathVariable("taskId") int taskId){
        taskService.markDone(taskId);
        return "redirect:/home";
    }
    @GetMapping("task/unmark/{taskId}")
    public String markTaskAsUnDone(@PathVariable("taskId") int taskId){
        taskService.markUnDone(taskId);
        return "redirect:/home";
    }

}
