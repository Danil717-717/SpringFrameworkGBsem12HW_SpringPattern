package ru.springgb.sem12HW.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.springgb.sem12HW.model.entity.Subscriber;
import ru.springgb.sem12HW.model.entity.Task;
import ru.springgb.sem12HW.repository.SubscriberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService{

    private final SubscriberRepository subscriberRepository;
    private final TaskService taskService;

    @Override
    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public Subscriber findById(Long id) {
        return subscriberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reader not found"));
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    public Subscriber getSubscriber(Long id) {
        return findAll().stream().filter(subscriber  -> subscriber.getId().equals(id)).findFirst().orElse(null);
    }


    @Override
    public Subscriber updateSubscriber(Long id, Subscriber subscriber) {
        Subscriber subOld = getSubscriber(id);
        if (subOld != null) {
            subOld.setName(subscriber.getName());
        }
        return subOld;
    }

    @Override
    public Subscriber apdateSubscriber(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public void deleteById(Long id) {
        subscriberRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksSubscriber(Long id) {
        Subscriber subscriber = findById(id);
        return subscriber.getTasks();
    }
    @Override
    public Subscriber createTaskForSubscriber(Long id, Task task) {
        Task taskNew = taskService.save(task);
        Subscriber subscriberNew = findById(id);
        subscriberNew.addTask(taskNew);
        return subscriberRepository.save(subscriberNew);
    }


    public List<Task> getListTask(){
        return taskService.getTasks();
    }

    @Override
    public Subscriber assignTask(Long id, Long taskId) {
        Subscriber subscriber = getSubscriber(id);
        Task task = taskService.getTaskById(taskId);
        subscriber.addTask(task);
        return subscriberRepository.save(subscriber);
    }

    @Override
    public void removingTaskFromSubscriber(Long id, Long taskId) {
        Subscriber subscriber = findById(id);
        subscriber.removeTask(taskId);
        save(subscriber);
    }

}
