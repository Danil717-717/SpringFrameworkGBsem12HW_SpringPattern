package ru.springgb.sem12HW.service;

import org.springframework.stereotype.Service;
import ru.springgb.sem12HW.model.entity.Executor;
import ru.springgb.sem12HW.model.entity.Subscriber;
import ru.springgb.sem12HW.model.entity.Task;

import java.util.List;

@Service
public interface TaskService {

    Task createTask(Task task);

    Task save(Task task);

    List<Task> getAllTasks(String keyword);

    List<Task> getTasks();

    Task getTaskById(Long id);
    List<Task> getTaskStatus(Task.Status status);

    Task updateTask(Long id, Task task);

    Task apdateTask(Task task);
    void deleteById(Long id);

    Task createExecutorForTask(Long id, Executor executor);


    Task assignExecutor(Long id, Long executorId);

    List<Task> getTasksExecutor(Long id);

    List<Executor> getExecutorsTask(Long id);

    List<Subscriber> getSubscriberTask(Long id);

    Executor save(Executor executor);


    void removingExecutorFromTask(Long taskId,Long executorId);

    void removingSubscriberFromTask(Long taskId, Long subscriberId);

}
