package ru.springgb.sem12HW.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Task task = new Task();

    public void update(){
        System.out.println("Update Status");
    }

    public void subscribeTask(Task ta){
        task = ta;
    }
}
