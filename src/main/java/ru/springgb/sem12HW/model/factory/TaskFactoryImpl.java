package ru.springgb.sem12HW.model.factory;

import org.springframework.stereotype.Component;
import ru.springgb.sem12HW.model.entity.ITask;
import ru.springgb.sem12HW.model.entity.Task;

import static ru.springgb.sem12HW.model.entity.Task.Status.*;

@Component
public class TaskFactoryImpl implements TaskFactory {

    @Override
    public ITask createTask(Task.TaskType type) {

        switch (type) {
            case NORMAL_EXECUTION:
                return Task.builder().description("NORMAL_EXECUTION").status(NORMAL_EXECUTION).build();

            case URGENT_IMPLEMENTATION:
                return Task.builder().description("URGENT_IMPLEMENTATION").status(URGENT_IMPLEMENTATION).build();

            default:
                return new Task();

        }
    }
}
