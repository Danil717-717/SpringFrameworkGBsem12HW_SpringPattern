package ru.springgb.sem12HW.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.springgb.sem12HW.model.entity.Executor;
import ru.springgb.sem12HW.model.entity.Subscriber;
import ru.springgb.sem12HW.model.entity.Task;
import ru.springgb.sem12HW.repository.ExecutorRepository;
import ru.springgb.sem12HW.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ExecutorRepository repository;
    private final NotificationService notificationService;


    @Override
    public Task createTask(Task task) {
        notificationService.notifyCreatedTask(task);
        return taskRepository.save(task);
    }

    @Override
    public Task createExecutorForTask(Long id, Executor executor) {
        Executor executorNew = save(executor);
        Task taskNew = getTaskById(id);
        taskNew.addExecutor(executorNew);
        return taskRepository.save(taskNew);

    }



    @Override
    public Task save(Task task) {
        notificationService.notifyCreatedTask(task);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks(String keyword) {
        notificationService.notifyListTask(taskRepository.findAll());
        if (keyword != null)
            return taskRepository.findAll(keyword);
        return taskRepository.findAll();
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task getTask(Long id) {
        return taskRepository.findAll().stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);

    }

    //&&&&&&&&
    @Override
    public List<Task> getTaskStatus(Task.Status status) {
        notificationService.notifyStutusUpdate(status);
        List<Task> tasks = taskRepository.findAll().stream().filter(task -> task.getStatus().equals(status)).toList();
        return tasks;
    }


    @Override
    public Task updateTask(Long id, Task task) {

        Task taskStaraya = getTask(id);
        if (taskStaraya != null) {
            taskStaraya.setDescription(task.getDescription());
            taskStaraya.setStatus(task.getStatus());
            notificationService.notifyStutusUpdate(task.getStatus());
        }
        return taskStaraya;
    }

    public Task apdateTask(Task task) {
        notificationService.notifyStutusUpdate(task.getStatus());
        return taskRepository.save(task);
    }


    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task assignExecutor(Long id, Long executorId) {
        Task task = getTask(id);
        Executor executor = getExecutor(executorId);
        task.addExecutor(executor);
        return taskRepository.save(task);
    }


    public List<Executor> findAllExecutor() {
        return repository.findAll();
    }

    public Executor getExecutor(Long id) {
        return findAllExecutor().stream().filter(executor -> executor.getId().equals(id)).findFirst().orElse(null);
    }

    public Executor findByIdExecutor(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reader not found"));
    }

    @Override
    public List<Task> getTasksExecutor(Long id) {
        Executor executor = findByIdExecutor(id);
        return executor.getTasks();
    }

    @Override
    public List<Executor> getExecutorsTask(Long id) {
        return getTaskById(id).getExecutors();
    }

    @Override
    public List<Subscriber> getSubscriberTask(Long id) {
        return getTaskById(id).getSubs();
    }

    @Override
    public Executor save(Executor executor) {
        return repository.save(executor);
    }



    @Override
    public void removingExecutorFromTask(Long taskId, Long executorId) {
        Task task = getTaskById(taskId);
        task.removeExecutor(executorId);
        save(task);
    }

    @Override
    public void removingSubscriberFromTask(Long taskId, Long subscriberId) {
        Task task = getTaskById(taskId);
        task.delSubscribe(subscriberId);
        save(task);
    }


}
