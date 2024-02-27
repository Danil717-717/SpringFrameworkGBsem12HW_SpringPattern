package ru.springgb.sem12HW.controller.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem12HW.model.entity.Executor;
import ru.springgb.sem12HW.model.entity.Subscriber;
import ru.springgb.sem12HW.model.entity.Task;
import ru.springgb.sem12HW.model.factory.TaskFactoryImpl;
import ru.springgb.sem12HW.service.ExecutorService;
import ru.springgb.sem12HW.service.SubscriberService;
import ru.springgb.sem12HW.service.TaskService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestController {

    private final TaskService taskService;
    private final ExecutorService executorService;
    private final SubscriberService subscriberService;
    private final TaskFactoryImpl taskFactory;


    /**
     * Tasks
     *
     */
    @GetMapping("/tasks")
    public List<Task> getAllTask(String keyword) {
        return taskService.getAllTasks(keyword);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }


    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PostMapping("/tasks/{type}")
    public Task createTask(@RequestBody Task task,@PathVariable Task.TaskType type) {
        task = (Task) taskFactory.createTask(type);
        return taskService.createTask(task);
    }


    /**
     * Executors
     * @param id
     * @param executor
     */

    @PutMapping("/tasks/{id}/executors")
    public Task createExecutorForTask(@PathVariable Long id,@RequestBody Executor executor) {
        return taskService.createExecutorForTask(id,executor);

    }

    @PutMapping("/tasks/{id}/executors/{executorsid}")
    public Task addExecInTask(@PathVariable Long id,@PathVariable Long executorsid) {
        return taskService.assignExecutor(id,executorsid);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    /**
     * TaskExecutor
     */
    @GetMapping("/tasks/{id}/executor")
    public List<Executor> getExecutorsByTask(@PathVariable Long id) {
        return taskService.getExecutorsTask(id);
    }


    @DeleteMapping("/tasks/{id}/executors/{executorsId}")
    public void deleteExecutorFromTask(@PathVariable Long id,@PathVariable Long executorsId) {
        taskService.removingExecutorFromTask(id,executorsId);
    }

    /**
     * TaskSubscriber
     */
    @GetMapping("/tasks/{id}/subscribers")
    public List<Subscriber> getSubscriberByTask(@PathVariable Long id) {
        return taskService.getSubscriberTask(id);
    }

    @DeleteMapping("/tasks/{id}/subscribers/{subscribersId}")
    public void deleteSubscribersFromTask(@PathVariable Long id,@PathVariable Long subscribersId) {
        taskService.removingSubscriberFromTask(id,subscribersId);
    }


    /**
     * Executor
     */
    @PostMapping("/executors")
    public Executor createExecutor(@RequestBody Executor executor) {
        return executorService.save(executor);
    }


    @GetMapping("/executors")
    public List<Executor> getAllExecutor() {
        return executorService.findAll();
    }


    @GetMapping("/executors/{id}")
    public Executor getExecutorById(@PathVariable Long id) {
        return executorService.findById(id);
    }


    @PutMapping("/executors/{id}")
    public Executor updateExecutor(@PathVariable Long id, @RequestBody Executor executor) {
        return executorService.updateExecutor(id, executor);
    }

    @PutMapping("/executors/{id}/tasks/{tasksId}")
    public Executor addTaskInExecutor(@PathVariable Long id,@PathVariable Long tasksId) {
        return executorService.assignTask(id,tasksId);
    }


    @GetMapping("/executor/{id}/tasks")
    public List<Task> getTasksPoExecutors(@PathVariable Long id) {
        return taskService.getTasksExecutor(id);
    }

    @PutMapping("/executors/{id}/tasks")
    public Executor createTaskForExecutor(@PathVariable Long id,@RequestBody Task task) {
        return executorService.createTaskForExecutor(id,task);

    }


    @DeleteMapping("/executors/{id}")
    public void deleteExecutor(@PathVariable Long id) {
        executorService.deleteById(id);
    }


    @DeleteMapping("/executors/{id}/tasks/{taskId}")
    public void deleteTaskFromExecutor(@PathVariable Long id, @PathVariable Long taskId) {
        executorService.removingTaskFromExecutor(id, taskId);
    }

    /**
     * Subscriber
     */

    @GetMapping("/subscribers")
    public List<Subscriber> getAllSubscriber() {
        return subscriberService.findAll();
    }

    @GetMapping("/subscribers/{id}")
    public Subscriber getSubscriberById(@PathVariable Long id) {
        return subscriberService.findById(id);
    }

    @PostMapping("/subscribers")
    public Subscriber createSubscriber(@RequestBody Subscriber subscriber) {
        return subscriberService.save(subscriber);
    }

    @GetMapping("/subscribers/{id}/tasks")
    public List<Task> getTasksSubscribers(@PathVariable Long id) {
        return subscriberService.getTasksSubscriber(id);
    }


    @PutMapping("/subscribers/{id}")
    public Subscriber updateSubscriber(@PathVariable Long id, @RequestBody Subscriber subscriber) {
        return subscriberService.updateSubscriber(id, subscriber);
    }


    @DeleteMapping("/subscribers/{id}")
    public void deleteSubscriber(@PathVariable Long id) {
        subscriberService.deleteById(id);
    }


    @PutMapping("/subscribers/{id}/tasks/{taskid}")
    public Subscriber addTaskInSubscriber(@PathVariable Long id,@PathVariable Long taskid) {
        return subscriberService.assignTask(id,taskid);
    }

    @DeleteMapping("/subscribers/{id}/tasks/{taskId}")
    public void deleteTaskFromSubscriber(@PathVariable Long id, @PathVariable Long taskId) {
        subscriberService.removingTaskFromSubscriber(id, taskId);
    }



}

