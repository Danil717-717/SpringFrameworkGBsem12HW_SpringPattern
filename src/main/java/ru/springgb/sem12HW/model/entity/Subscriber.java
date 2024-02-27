package ru.springgb.sem12HW.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    private List<Task> tasks = new ArrayList<>();

    public void update(){
        System.out.println("Update Status");
    }

    public void addTask(Task task) {
        this.tasks.add(task);

    }

    public void removeTask(long taskId) {
        Task task = this.tasks.stream().filter(t -> t.getId() == taskId)
                .findFirst().orElse(null);
        if (task != null) {
            this.tasks.remove(task);

        }
    }

}
