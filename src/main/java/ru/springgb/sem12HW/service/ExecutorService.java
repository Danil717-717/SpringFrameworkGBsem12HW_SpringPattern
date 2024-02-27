package ru.springgb.sem12HW.service;

import org.springframework.stereotype.Service;
import ru.springgb.sem12HW.model.entity.Executor;
import ru.springgb.sem12HW.model.entity.Task;

import java.util.List;

@Service
public interface ExecutorService {
    List<Executor> findAll();

    Executor findById(Long id);
    Executor save(Executor executor);

    Executor updateExecutor(Long id, Executor executor);
    Executor apdateExecutor(Executor executor);

    void deleteById(Long id);

    Executor createTaskForExecutor(Long id, Task task);

    void removingTaskFromExecutor(Long executorId, Long taskId);

    Executor assignTask(Long id, Long taskId);

}
