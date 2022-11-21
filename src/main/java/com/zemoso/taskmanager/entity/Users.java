package com.zemoso.taskmanager.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Id
    @Column(name="username")
    private String username;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="password")
    private String password;
    @Column(name="enabled")
    private short enabled;
    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Task> tasksOwned;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="authorities",
            joinColumns=@JoinColumn(name="username"),
            inverseJoinColumns = @JoinColumn(name="authority")
    )
    private List<Roles> roles;
    public  void addRole(Roles role){
        if(roles==null){
            roles=new ArrayList<>();
        }
        roles.add(role);


    }
    public void addTask(Task task){
        if(task==null){
            tasksOwned=new ArrayList<>();
        }
        tasksOwned.add(task);
    }
    public String toString(){
        return "["+userId+","+username+","+name+","+email+","+phone+","+password+"]";
    }


}
