package ru.springgb.sem12HW.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.springgb.sem12HW.model.entity.Executor;
import ru.springgb.sem12HW.model.entity.Task;
import ru.springgb.sem12HW.repository.ExecutorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutorServiceImpl implements ExecutorService {

    private final ExecutorRepository repository;
    private final TaskService taskService;

    @Override
    public List<Executor> findAll() {
        return repository.findAll();
    }

    @Override
    public Executor findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reader not found"));
    }

    @Override
    public Executor save(Executor executor) {
        return repository.save(executor);
    }

    public Executor getExecutor(Long id) {
        return findAll().stream().filter(executor  -> executor.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Executor updateExecutor(Long id, Executor executor) {
        Executor executorStaraya = getExecutor(id);
        if (executorStaraya != null) {
            executorStaraya.setName(executor.getName());
        }
        return executorStaraya;
    }

    @Override
    public Executor apdateExecutor(Executor executor) {
        return repository.save(executor);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    @Override
    public Executor createTaskForExecutor(Long id, Task task) {
        Task taskNew = taskService.save(task);
        Executor executorNew = getExecutor(id);
        executorNew.addTask(taskNew);
        return repository.save(executorNew);

    }

    @Override
    public void removingTaskFromExecutor(Long executorId, Long taskId) {
        Executor executor = getExecutor(executorId);
        executor.removeTask(taskId);
        save(executor);
    }

    @Override
    public Executor assignTask(Long id, Long taskId) {
        Executor executor = getExecutor(id);
        Task task = taskService.getTaskById(taskId);
        executor.addTask(task);
        taskService.save(task);
        return repository.save(executor);
    }
}
