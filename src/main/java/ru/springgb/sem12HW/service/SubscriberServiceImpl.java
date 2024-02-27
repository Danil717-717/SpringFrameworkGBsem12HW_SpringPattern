package ru.springgb.sem12HW.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.springgb.sem12HW.model.entity.Executor;
import ru.springgb.sem12HW.model.entity.Subscriber;
import ru.springgb.sem12HW.repository.SubscriberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService{

    private final SubscriberRepository subscriberRepository;
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
}
