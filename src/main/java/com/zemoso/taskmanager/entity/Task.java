package com.zemoso.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String date;
    @Column(name="is_completed")
    private boolean completed;
    @Column(name="creator_name")
    private String creatorName;
    @ManyToOne()
    @JoinColumn(name="owner_username")
    private Users owner;

    public String toString(){
        return "["+id+","+name+","+description+","+date+","+completed+","+creatorName+"]";
    }
}
