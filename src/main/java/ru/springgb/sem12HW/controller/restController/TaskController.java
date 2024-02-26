package ru.springgb.sem12HW.controller.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.springgb.sem12HW.model.entity.Executor;
import ru.springgb.sem12HW.model.entity.Task;
import ru.springgb.sem12HW.model.factory.TaskFactory;
import ru.springgb.sem12HW.model.factory.TaskFactoryImpl;
import ru.springgb.sem12HW.service.ExecutorService;
import ru.springgb.sem12HW.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;
    private final ExecutorService executorService;

    private final TaskFactoryImpl taskFactory;



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
        return taskService.assignTask(id,tasksId);
    }


    @GetMapping("/executor/{id}/tasks")
    public List<Task> getTasksPoExecutors(@PathVariable Long id) {
        return taskService.getTasksExecutor(id);
    }

    @GetMapping("/tasks/{id}/executor")
    public List<Executor> getExecutorsByTask(@PathVariable Long id) {
        return taskService.getExecutorsTask(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }


    @DeleteMapping("/executors/{id}")
    public void deleteExecutor(@PathVariable Long id) {
        executorService.deleteById(id);
    }

    @DeleteMapping("/tasks/{id}/executors/{executorsId}")
    public void deleteExecutorFromTask(@PathVariable Long id,@PathVariable Long executorsId) {
        taskService.removingExecutorFromTask(id,executorsId);
    }


    @DeleteMapping("/executors/{id}/tasks/{taskId}")
    public void deleteTaskFromExecutor(@PathVariable Long id, @PathVariable Long taskId) {
        taskService.removingTaskFromExecutor(id, taskId);
    }


}

