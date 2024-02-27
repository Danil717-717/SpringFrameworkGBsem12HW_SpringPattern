package ru.springgb.sem12HW.service;

import org.springframework.stereotype.Service;
import ru.springgb.sem12HW.model.entity.Task;


import java.util.List;

@Service
public class NotificationService {

    public void notifyCreatedTask(Task task) {
        System.out.println("A new task has been created: " + task.getDescription());
    }

    public void notifyListTask(List<Task> list) {
        System.out.println("The list Tasks: " + list);
    }


    public void notifyStutusUpdate(Task.Status status) {
        System.out.println("Update status task " + status);
    }


}
