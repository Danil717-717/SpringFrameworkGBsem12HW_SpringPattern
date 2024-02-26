package ru.springgb.sem12HW.model.factory;

import ru.springgb.sem12HW.model.entity.ITask;
import ru.springgb.sem12HW.model.entity.Task;

public interface TaskFactory {
    ITask createTask(Task.TaskType type);
}
